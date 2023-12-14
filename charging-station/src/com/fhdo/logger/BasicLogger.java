package com.fhdo.logger;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class BasicLogger {

    String fileName;
    private Logger logger;
    //private String directoryPath;

    public BasicLogger(Class<?> sourceClass, String fileName){
        //this.directoryPath = System.getProperty("user.dir");
        this.fileName = fileName;
        try {
            if (logger != null) {
                logger.setUseParentHandlers(false);
                for (Handler handler : logger.getHandlers()) {
                    logger.removeHandler(handler);
                }
            }
            FileHandler fileHandler = new FileHandler(this.fileName);
            //Running into errors while using a custom formatter, logs are being repeated 4 times, dont know why
            //CustomFormatter formatter = new CustomFormatter();
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            //ConsoleHandler consoleHandler = new ConsoleHandler();
            //consoleHandler.setFormatter(formatter);

            logger = Logger.getLogger(sourceClass.getName());

            logger.addHandler(fileHandler);
            //logger.addHandler(consoleHandler);

            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warning(String message) {
        logger.warning(message);
    }

    public void severe(String message) {
        logger.severe(message);
    }

    // Custom formatter for log records
    private static class CustomFormatter extends Formatter {
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        @Override
        public String format(LogRecord record) {
            String formattedDate = dateFormat.format(new Date(record.getMillis()));
            String className = record.getSourceClassName();
            String methodName = record.getSourceMethodName();
            String level = record.getLevel().getName();
            String message = formatMessage(record);

            return String.format("%s - CLASS: %s %s - %s: %s%n",
                    formattedDate, className, methodName, level, message);
        }
    }
    
}