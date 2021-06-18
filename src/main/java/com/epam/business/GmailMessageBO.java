package com.epam.business;

import com.epam.model.MessageEntity;
import com.epam.pages.gmail.GmailHomePage;
import com.epam.pages.gmail.GmailMessageFormPage;
import com.epam.utils.Constants;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GmailMessageBO {
    private GmailHomePage homePage;
    private GmailMessageFormPage messageFormPage;
    private static final Logger logger = LogManager.getLogger(GmailMessageBO.class);

    public GmailMessageBO() {
        homePage = new GmailHomePage();
        messageFormPage = new GmailMessageFormPage();
    }

    @Step("Create draft message with fields - receiver: {0.receiver}, cc: {0.cc}, bcc: {0.bcc}, " +
            "topic: {0.topic}, text: {0.letterText}, in {method} step")
    public void createDraftMessage(MessageEntity message) {
        logger.log(Level.INFO, () -> "Creating new message.");
        homePage.clickComposeButton();
        logger.log(Level.INFO, () -> "Filling new message fields.");
        fillDraftMessage(message);
        logger.log(Level.INFO, () -> "Saving and closing created message as draft.");
        messageFormPage.saveLetterAsDraftAndClose();
    }

    private void fillDraftMessage(MessageEntity message) {
        messageFormPage.enterReceiverEmail(message.getReceiver());
        messageFormPage.displayCcField();
        messageFormPage.displayBccField();
        messageFormPage.enterCcEmail(message.getCc());
        messageFormPage.enterBccEmail(message.getBcc());
        messageFormPage.enterTopic(message.getTopic());
        messageFormPage.enterLetterText(message.getLetterText());
    }

    @Step("Go to drafts folder and click on last draft message in {method} step")
    public void goToDraftsFolderAndClickLastDraftMessage() {
        logger.log(Level.INFO, () -> "Going to draft messages folder.");
        homePage.clickFolderByName(Constants.DRAFTS_FOLDER_NAME);
        logger.log(Level.INFO, () -> "Clicking on last saved draft message.");
        homePage.clickLastDraftMessage();
    }

    @Step("Get filled fields from draft message in {method} step")
    public MessageEntity getDraftMessageEntity() {
        logger.log(Level.INFO, () -> "Getting filled fields from draft message.");
        String filledToField = messageFormPage.getEmailTextOfFilledToField();
        String filledCcField = messageFormPage.getEmailTextOfFilledCcField();
        String filledTopicField = messageFormPage.getFilledTopicFieldAttribute();
        String filledTextField = messageFormPage.getFilledLetterTextFieldText();
        logger.log(Level.INFO, () -> "Creating new entity from filled draft message fields.");
        return new MessageEntity(filledTopicField, filledToField, filledCcField, filledTextField);
    }

    @Step("Send last draft message in {method} step")
    public void sendLastDraftMessage() {
        logger.log(Level.INFO, () -> "Sending draft message.");
        messageFormPage.sendLetter();
        logger.log(Level.INFO, () -> "Waiting on draft message sent link to be clickable.");
        homePage.waitOnSentMessageLinkToBeVisible();
    }

    @Step("Verify is draft message sent in {method} step")
    public boolean isDraftSent() {
        logger.log(Level.INFO, () -> "Checking is draft message sent by appearance of sent link.");
        return homePage.isSentMessageDisplayed() && homePage.getSentMessageBlockText().equals(Constants.SUCCESSFUL_MESSAGE_SENT);
    }
}
