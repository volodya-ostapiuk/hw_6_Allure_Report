package com.epam.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintProvider {

    private static final Logger LOGGER = LogManager.getLogger(PrintProvider.class);

    private PrintProvider() {
        throw new IllegalStateException("Utility PrintProvider class");
    }

    public static void logOutput(Object obj, Logger logger) {
        if (obj instanceof String str) {
            logger.log(Level.INFO, () -> str);
        }
    }

    public static void log(Object obj) {
        if (obj instanceof String str) {
            LOGGER.log(Level.INFO, () -> str);
        }
    }
}
