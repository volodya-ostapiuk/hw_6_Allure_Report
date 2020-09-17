package com.epam.listener;

import com.epam.utils.providers.DriverProvider;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class AllureAttachment {

    /**
     * Screenshot attachments for Allure
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] takeScreenshotPNG() {
        return ((TakesScreenshot) DriverProvider.getInstance()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Text attachments for Allure
     */
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }
}
