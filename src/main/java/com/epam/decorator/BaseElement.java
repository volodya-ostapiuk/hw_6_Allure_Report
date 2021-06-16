package com.epam.decorator;

import org.openqa.selenium.WebElement;

public abstract class BaseElement {
    protected WebElement webElement;

    protected BaseElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElement getElement() {
        return webElement;
    }
}
