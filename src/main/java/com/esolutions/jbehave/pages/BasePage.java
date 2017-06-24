package com.esolutions.jbehave.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.ThreadLocalRandom;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by Alejandro_DelCarlo on 15/11/2016.
 */
public abstract class BasePage extends WebDriverPage {

    private String url = "http://localhost:8080/";

    public BasePage(WebDriverProvider driverProvider) {
        super(driverProvider);
        if (System.getProperty("url") != null) url = System.getProperty("url");
    }

    public String leerIp() {
        return url;
    }

    public void waitForElementToBeClickable(By element, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
        wait.until(elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(By element, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
        wait.until(visibilityOfElementLocated(element));
    }

    public void waitForElementNotVisible(By element, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
        wait.until(not(visibilityOfElementLocated(element)));
    }

    public void waitForTextToBePresentInElement(By element, String text, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
        wait.until(textToBePresentInElementLocated(element, text));
    }

    public void waitForTextNotToBePresentInElement(By element, String text, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
        wait.until(not(textToBePresentInElementLocated(element, text)));
    }

    public void waitUntilUrlContains(String path, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
        wait.until(urlContains(path));
    }

    public void waitForElementsToBeVisible(By elements, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
        wait.until(visibilityOfAllElementsLocatedBy(elements));
    }

    public void waitForTextToBePresentInElementValue(By element, String value, long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
        wait.until(textToBePresentInElementValue(element, value));
    }

    public int randomIntInRange(int begin, int end) {
        return ThreadLocalRandom.current().nextInt(begin, end);
    }
}