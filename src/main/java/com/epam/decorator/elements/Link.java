package com.epam.decorator.elements;

import com.epam.decorator.BaseElement;
import com.epam.utils.Wait;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Link extends BaseElement {
    private static final Logger logger = LogManager.getLogger(Link.class);

    public Link(WebElement webElement) {
        super(webElement);
    }

    public void click() {
        logger.log(Level.INFO, () -> "Clicking on link with location: " + webElement.getLocation());
        Wait.waitOnElementToBeClickable(webElement);
        webElement.click();
    }

    public boolean isDisplayed() {
        logger.log(Level.INFO, () -> "Checking is link element displayed");
        return webElement.isDisplayed();
    }
}
