package com.esolutions.jbehave.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Emanuel on 11/03/2016.
 */
public class TouchQPlusPage extends BasePage {
    public static final By DIV_BOTONERA = By.id("divBotonera");
    public static final By LEYENDA = By.id("leyenda");
    public static final By INGRESAR_NO_CLIENTE = By.id("ingresarNoCliente");
    public static final By TITULO_DESCRIPCION = By.id("titulo-Descripcion");
    public static final By PROCESOS = By.cssSelector("a[id^='proceso']");
    public static final By MODAL_ESPERA = By.id("modalEspera");
    public static final By NUMERO_TICKET = By.xpath("/html/body/div[1]/div[6]/span");
    public static final By TURNO_GENERADO = By.id("turnoGenerado");
    public static final By TITULO_DESCRIPCION_SERVICIOS = By.cssSelector("#divServiciosCac #div-titulo div");
    public static final By SERVICIOS = By.className("boton-proceso");
    public static final By BOTON_CONTINUAR = By.id("continuar");

    private List<WebElement> listaProcesos;
    private List<WebElement> listaServicios;

    public TouchQPlusPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void accederTouch(String codigo) {
        // a la IP le concatenamos la ruta al touch
        get(leerIp().concat("qPlus/touchApp.do?cac=").concat(codigo));

        // si no cargo la pagina, intentamos acceder 3 veces mas
        if (!getTitle().contains("Qplus - Touch"))
            for (int i = 0; i < 3; i++)
                get(leerIp().concat("qPlus/touchApp.do?cac=").concat(codigo));

        Assert.assertTrue("No se cargo correctamente el TOUCH.", getTitle().contains("Qplus - Touch"));
    }

    public void verificarBotonera() {
        Assert.assertNotNull("Botonera", findElement(DIV_BOTONERA));
        waitForElementToBeVisible(LEYENDA, 5);
        Assert.assertTrue("Leyenda", findElement(LEYENDA).getText().contentEquals("Ingreso por número de celular"));
    }

    public void seleccionarOpcionNoCliente() {
        waitForElementToBeClickable(INGRESAR_NO_CLIENTE, 2);
        /*
            se añade el metodo sleep ya que la carga de los procesos siempre tiene un delay
            y no es posible asociarla a algun evento; de esta forma, durante el sleep (antes de seleccionar
            la opcion de no cliente) se cargaran los procesos asignados
        */
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findElement(INGRESAR_NO_CLIENTE).click();
    }

    public String getTituloProcesos() {
        waitForElementToBeVisible(TITULO_DESCRIPCION, 5);
        return findElement(TITULO_DESCRIPCION).getText();
    }

    public void seleccionarProceso() {
        waitForElementsToBeVisible(PROCESOS, 10);
        listaProcesos = findElements(PROCESOS);
        Assert.assertTrue("No existen procesos para seleccionar",listaProcesos.size() > 0);
        // seleccionamos un proceso aleatorio
        listaProcesos.get(randomIntInRange(0, listaProcesos.size())).click();
    }

    public String getTextoTituloServicios() {
        waitForElementToBeVisible(TITULO_DESCRIPCION_SERVICIOS, 5);
        return findElement(TITULO_DESCRIPCION_SERVICIOS).getText();
    }

    public void seleccionarServicio() {
        waitForElementsToBeVisible(SERVICIOS, 5);
        // validamos que exitan dos servicios o mas
        listaServicios = findElements(SERVICIOS);
        Assert.assertTrue(listaServicios.size() >= 2);
        // seleccionamos un proceso aleatorio
        listaServicios.get(randomIntInRange(0, listaServicios.size())).click();
    }

    public String getNuevoTurno() {
        // validamos que se muestre el modal indicando que se esta generando el turno
        Assert.assertNotNull(findElement(MODAL_ESPERA));
        // esperamos a que se muestre el mensaje con el numero del ticket
        waitForElementToBeVisible(NUMERO_TICKET, 5000);
        return findElement(TURNO_GENERADO).getText();
    }

    public void clickNumero(int numero) {
        if (numero < 0 || numero > 9) throw new IllegalArgumentException();
        By boton_numero = By.cssSelector("input[value='" + numero + "']");
        findElement(boton_numero).click();
    }

    public void clickContinuar() {
        waitForElementToBeVisible(BOTON_CONTINUAR, 5);
        findElement(BOTON_CONTINUAR).click();
    }
}