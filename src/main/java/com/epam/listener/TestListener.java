package com.epam.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getThrowable().getMessage());
        AllureAttachment.takeScreenshotPNG();
        AllureAttachment.saveTextLog(result.getMethod().getConstructorOrMethod().getName()
                + " test failed and screenshot is taken.");
    }

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Entering onStart method " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Entering onFinish method " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Entering onTestStart method " +  result.getName());
        logger.info(getTestMethodName(result) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Entering onTestSuccess method " +  result.getName());
        logger.info(getTestMethodName(result) + " test is successful.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Entering onTestSkipped method " +  result.getName());
        logger.info(getTestMethodName(result) + " test is skipping.");
    }
}
