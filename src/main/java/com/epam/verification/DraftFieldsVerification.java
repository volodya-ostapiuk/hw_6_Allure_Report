package com.epam.verification;

import com.epam.business.GmailLogInBO;
import com.epam.business.GmailMessageBO;
import com.epam.utils.Constants;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class DraftFieldsVerification implements Constants {

    private static final Logger logger = LogManager.getLogger(DraftFieldsVerification.class);

    public void verifyDraftFieldsAreSavedCorrectly(String userEmail, String userPassword) {
        logger.log(Level.INFO, () -> "Verify login.");
        var logInBO = new GmailLogInBO();
        logInBO.logIn(userEmail, userPassword);
        Assert.assertTrue(logInBO.verifyLogIn(userEmail), WRONG_LOGIN);
        logger.log(Level.INFO, () -> "Create draft message.");
        var messageBO = new GmailMessageBO();
        messageBO.createDraftMessage(TEST_MESSAGE);
        messageBO.goToDraftsFolderAndClickLastDraftMessage();
        logger.log(Level.INFO, () -> "Verify saved draft message fields.");
        var filledDraftMessage = messageBO.getDraftMessageEntity();
        Assert.assertEquals(filledDraftMessage.toString(), TEST_MESSAGE.toString(), WRONG_SAVED_DRAFT);
        logger.log(Level.INFO, () -> "Verify draft message sending.");
        messageBO.sendLastDraftMessage();
        Assert.assertTrue(messageBO.isDraftSent(), WRONG_DRAFT_SENT);
    }
}
