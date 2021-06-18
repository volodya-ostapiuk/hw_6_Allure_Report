package com.epam.pages.gmail;

import com.epam.decorator.elements.Button;
import com.epam.decorator.elements.Link;
import com.epam.decorator.elements.TextField;
import com.epam.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

public class GmailMessageFormPage extends BasePage {
    @FindBy(id = "to")
    private TextField toField;

    @FindBy(id = "cc_sw")
    private Link ccAdditionLink;

    @FindBy(css = "#cc_block #cc")
    private TextField ccField;

    @FindBy(id = "bcc_sw")
    private Link bccAdditionLink;

    @FindBy(css = "#bcc_block #bcc")
    private TextField bccField;

    @FindBy(name = "subject")
    private TextField topicFiled;

    @FindBy(id = "text")
    private TextField letterTextFiled;

    @FindBy(css = ".send_container input[name='send']")
    private Button sendButton;

    @FindBy(css = ".send_container input[name='save_in_drafts']")
    private Button saveAsDraftAndCloseButton;

    public void enterReceiverEmail(String email) {
        toField.sendKeys(email);
    }

    public void displayCcField() {
        ccAdditionLink.click();
    }

    public void enterCcEmail(String email) {
        ccField.sendKeys(email);
    }

    public void displayBccField() {
        bccAdditionLink.click();
    }

    public void enterBccEmail(String email) {
        bccField.sendKeys(email);
    }

    public void enterTopic(String topic) {
        topicFiled.sendKeys(topic);
    }

    public void enterLetterText(String text) {
        letterTextFiled.sendKeys(text);
    }

    public String getEmailTextOfFilledToField() {
        return toField.getText();
    }

    public String getEmailTextOfFilledCcField() {
        return ccField.getText();
    }

    public String getFilledTopicFieldAttribute() {
        return Optional.ofNullable(topicFiled.getAttribute("value"))
                .orElse(webDriver.findElement(By.cssSelector(".subj input[type=text]")).getAttribute("value"));
    }

    public String getFilledLetterTextFieldText() {
        return letterTextFiled.getText();
    }

    public void sendLetter() {
        sendButton.click();
    }

    public void saveLetterAsDraftAndClose() {
        saveAsDraftAndCloseButton.click();
    }
}