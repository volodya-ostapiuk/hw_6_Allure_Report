package com.epam.pages.gmail;

import com.epam.decorator.elements.Button;
import com.epam.decorator.elements.TextField;
import com.epam.pages.BasePage;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends BasePage {
    @FindBy(name = "login")
    private TextField emailInput;

    @FindBy(className = "VfPpkd-RLmnJb")
    private Button nextButton;

    @FindBy(name = "pass")
    private TextField passwordInput;

    @FindBy(className = "VfPpkd-RLmnJb")
    private Button submitButton;

    public void enterEmail(String email) {
        emailInput.typeAndEnter(email);
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void enterPassword(String password) {
        passwordInput.typeAndEnter(password);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }
}
