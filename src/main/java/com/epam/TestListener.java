package com.epam;

import com.epam.utils.Constants;
import com.epam.utils.providers.DriverProvider;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private static Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            logger.error("Test failed: " + result.getThrowable().getMessage());
            String failureScreenshotName = getScreenshotName(result);
            File screenshotFolder = new File(String.format(Constants.SCREENSHOT_PATH, failureScreenshotName));
            FileUtils.copyFile(takeScreenshotPNG(), screenshotFolder);
            saveTextLog(result.getMethod().getConstructorOrMethod().getName() + " failed and screenshot is taken.");
        } catch (IOException exception) {
            logger.error("Failed with saving screenshot: " + exception.getMessage());
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss_z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    private String getScreenshotName(ITestResult result) {
        return result.getThrowable().getClass().getSimpleName() + getCurrentDate()
                + "_Thread" + Thread.currentThread().getId();
    }

    //Screenshot attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public File takeScreenshotPNG() {
        return ((TakesScreenshot) DriverProvider.getInstance()).getScreenshotAs(OutputType.FILE);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog (String message) {
        return message;
    }
}
