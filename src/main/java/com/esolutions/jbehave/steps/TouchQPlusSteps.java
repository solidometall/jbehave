package com.esolutions.jbehave.steps;

import com.esolutions.jbehave.pages.PageFactory;
import com.esolutions.jbehave.pages.TouchQPlusPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

/**
 * Created by Emanuel on 11/03/2016.
 */
public class TouchQPlusSteps extends Steps {
    private TouchQPlusPage touchQPlus;

    public TouchQPlusSteps(PageFactory pageFactory){
        touchQPlus = pageFactory.newTouchQPlus();
    }

    @Given("el touch del CAC $cac")
    public void accederTouch(@Named("cac") String codigo) {
        touchQPlus.accederTouch(codigo);
    }

    @Then("verifico pantalla principal")
    public void verificoBotonera(){
        touchQPlus.verificarBotonera();
    }

    @When("selecciono opcion no cliente en el touch")
    public void seleccionarOpcionNoCliente() throws InterruptedException {
        touchQPlus.seleccionarOpcionNoCliente();
    }

    @When("selecciono servicio en el touch")
    public void seleccionarServicio() {
        // validamos que se muestre el mensaje de seleccion de servicios y que existan dos procesos o mas
        String texto = touchQPlus.getTextoTituloServicios();
        Assert.assertTrue("El título de servicios no coincide.", texto.contentEquals("Seleccione el motivo de su visita"));
        touchQPlus.seleccionarServicio();
    }

    @When("selecciono proceso en el touch")
    public void seleccionarProceso() {
        touchQPlus.seleccionarProceso();
    }

    @Then("se genera un turno de atencion en el touch")
    public void validarNuevoTurno() {
        // evaluamos el numero del ticket, el cual debe coincidir con la expresion regular: letras + espacio + numeros
        Assert.assertTrue("El numero del ticket no coincide con el formato: letra + espacio + numeros",
                touchQPlus.getNuevoTurno().matches("[A-Z]\\s([0-9]+)"));
    }

    @When("ingreso numero de telefono Cliente no autenticado")
    public void ingresoNumeroTelNoAutenticado() {
        for (int i = 0; i < 7; i++) {
            touchQPlus.clickNumero(1);
        }
    }

    @When("presiono el boton continuar")
    public void presionarBotonContinuar() {
        touchQPlus.clickContinuar();
    }
}
