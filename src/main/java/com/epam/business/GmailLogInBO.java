package com.epam.business;

import com.epam.pages.gmail.GmailHomePage;
import com.epam.pages.gmail.GmailLoginPage;
import com.epam.utils.Printable;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GmailLogInBO {
    private GmailLoginPage loginPage;
    private GmailHomePage homePage;
    private static final Logger logger = LogManager.getLogger(GmailLogInBO.class);
    private static final Printable printable = string -> logger.log(Level.INFO, () -> string);

    public GmailLogInBO() {
        loginPage = new GmailLoginPage();
        homePage = new GmailHomePage();
    }

    @Step("Authorise user with email: {0}, password: {1}, in {method} step")
    public void logIn(String userEmail, String userPassword) {
        printable.log("Authorising user with email: " + userEmail);
        loginPage.enterEmail(userEmail);
        printable.log("Authorising user with password: " + userPassword);
        loginPage.enterPassword(userPassword);
    }

    @Step("Verify user with email: {0} is logged in successfully, in {method} step")
    public boolean verifyLogIn(String userEmail) {
        printable.log("Verifying user login by getting page title of home page with mail logo");
        homePage.waitOnMailLogoToBeClickable();
        return homePage.getMenuTitle().getText().contains(userEmail);
    }
}
