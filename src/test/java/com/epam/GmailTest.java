package com.epam;

import com.epam.listener.TestListener;
import com.epam.utils.Constants;
import com.epam.utils.providers.DataObjectsProvider;
import com.epam.utils.providers.DriverProvider;
import com.epam.verification.DraftFieldsVerification;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.stream.Stream;

@Listeners({TestListener.class})
public class GmailTest implements Constants {

    private DraftFieldsVerification draftFieldsVerification;

    @BeforeMethod
    private void setUp() {
        draftFieldsVerification = new DraftFieldsVerification();
        DriverProvider.getInstance().get(BASE_URL);
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> usersLoginAndPassword() {
        return Stream.of(DataObjectsProvider.getUsers()).iterator();
    }

    /**
     * Enters email, enters password. Waits until new page will be opened and element will be clickable on it.
     * Checks does page title contains email. Creates new letter. Saves it as draft.
     * Checks if last draft letter contains needed emails, text and topic. Sends saved letter.
     */
    @Test(dataProvider = "usersLoginAndPassword")
    private void verifyDraftFieldsSaving(String userEmail, String userPassword) {
        draftFieldsVerification.verifyDraftFieldsAreSavedCorrectly(userEmail, userPassword);
    }

    @AfterMethod
    private void tearDown() {
        DriverProvider.quit();
    }
}
