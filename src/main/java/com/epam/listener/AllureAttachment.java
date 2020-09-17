package com.epam.listener;

import com.epam.utils.Constants;
import com.epam.utils.providers.DriverProvider;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllureAttachment {
    private static Logger logger = LogManager.getLogger(AllureAttachment.class);

    public static void provideAttachment(ITestResult result) {
        try {
            String failureScreenshotName = getScreenshotName();
            File screenshotFolder = new File(String.format(Constants.SCREENSHOT_PATH, failureScreenshotName));
            FileUtils.copyFile(new File(new String(takeScreenshotPNG(), StandardCharsets.UTF_8)), screenshotFolder);
            saveTextLog(result.getMethod().getConstructorOrMethod().getName() + " failed and screenshot is taken.");
        } catch (IOException exception) {
            logger.error("Failed with saving screenshot.");
        }
    }

    private static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss_z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    private static String getScreenshotName() {
        return "screenshot" + getCurrentDate() + "_Thread" + Thread.currentThread().getId() + ".png";
    }

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
