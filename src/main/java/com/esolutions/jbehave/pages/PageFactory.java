package com.esolutions.jbehave.pages;

import org.jbehave.web.selenium.WebDriverProvider;

/**
 * Created by Emanuel on 04/02/2016.
 */
public class PageFactory {
    private final WebDriverProvider webDriverProvider;

    public PageFactory(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    public TouchQPlusPage newTouchQPlus() {
        return new TouchQPlusPage(webDriverProvider);
    }
}