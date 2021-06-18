package com.epam.pages.gmail;

import com.epam.decorator.elements.Button;
import com.epam.decorator.elements.Link;
import com.epam.pages.BasePage;
import com.epam.utils.Wait;
import com.epam.utils.providers.DriverWaitProvider;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class GmailHomePage extends BasePage {
    @FindBy(css = ".make_message a")
    private Button composeButton;

    @FindBy(css = ".list_underlined li:nth-child(3) a")
    private Link draftsFolder;

    @FindBy(css = ".list_underlined li a")
    private List<Link> allFolders;

    @FindBy(className = "ho_logo")
    private Link mailLogo;

    @FindBy(xpath = "//*[@class='row new'][1]//span[@class='frm']")
    private Link lastDraftMessage;

    @FindBy(css = ".block_confirmation .content.clear")
    private WebElement sentMessageBlock;

    @FindBy(className = "sn_menu_title")
    private WebElement menuTitle;

    public WebElement getMenuTitle() {
        return menuTitle;
    }

    public void waitOnMailLogoToBeClickable() {
        Wait.waitOnElementToBeClickable(mailLogo.getElement());
    }

    public void waitOnSentMessageLinkToBeVisible() {
        Wait.waitOnElementToBeVisible(sentMessageBlock);
    }

    public void clickComposeButton() {
        composeButton.click();
    }

    public void clickDraftsFolder() {
        DriverWaitProvider.getInstance().ignoring(StaleElementReferenceException.class).until(driver -> {
            draftsFolder.click();
            return true;
        });
    }

    public void clickLastDraftMessage() {
        lastDraftMessage.click();
    }

    public boolean isSentMessageDisplayed() {
        return sentMessageBlock.isDisplayed();
    }

    public String getSentMessageBlockText() {
        return sentMessageBlock.getText();
    }

    private Optional<Link> getFolderByName(String folderName) {
        return allFolders
                .stream()
                .filter(element -> element.getText().trim().contains(folderName))
                .findFirst();
    }

    public void clickFolderByName(String folderName) {
        Optional<Link> linkElement = getFolderByName(folderName);
        linkElement.ifPresent(Link::click);
    }
}
