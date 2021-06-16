package com.epam.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        logger.log(Level.ERROR, () -> "Test failed: " + result.getThrowable().getMessage());
        AllureAttachment.takeScreenshotPNG();
        AllureAttachment.saveTextLog(result.getMethod().getConstructorOrMethod().getName()
                + " test failed and screenshot is taken.");
    }

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext context) {
        logger.log(Level.INFO, () -> "Entering onStart method " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.log(Level.INFO, () -> "Entering onFinish method " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.log(Level.INFO, () -> "Entering onTestStart method " +  result.getName());
        logger.log(Level.INFO, () -> getTestMethodName(result) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.log(Level.INFO, () -> "Entering onTestSuccess method " +  result.getName());
        logger.log(Level.INFO, () -> getTestMethodName(result) + " test is successful.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.log(Level.INFO, () -> "Entering onTestSkipped method " +  result.getName());
        logger.log(Level.INFO, () -> getTestMethodName(result) + " test is skipping.");
    }
}
