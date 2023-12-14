package com.fhdo.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
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
            FileHandler fileHandler = new FileHandler(this.fileName);

            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger = Logger.getLogger(sourceClass.getName());

            logger.addHandler(fileHandler);

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
    
}
