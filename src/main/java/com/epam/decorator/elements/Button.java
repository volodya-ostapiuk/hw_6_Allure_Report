package com.epam.decorator.elements;

import com.epam.decorator.BaseElement;
import com.epam.utils.Wait;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Button extends BaseElement {
    private static final Logger logger = LogManager.getLogger(Button.class);

    public Button(WebElement webElement) {
        super(webElement);
    }

    public void click() {
        logger.log(Level.INFO, () -> "Clicking on button with location: " + webElement.getLocation());
        Wait.waitOnElementToBeClickable(webElement);
        webElement.click();
    }
}
