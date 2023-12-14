package com.fhdo.logger;

import java.io.File;
import java.io.IOException;

class LogFile {
    String fileName;
    private String directoryPath;

    public LogFile(String fileName, String directoryPath) {
        this.fileName = fileName;
        this.directoryPath = directoryPath;
    }

    // Create a new log file
    public void createLogFile() {
        try {
            File file = new File(directoryPath, fileName);
            if (file.createNewFile()) {
                System.out.println("Log file created: " + file.getAbsolutePath());
            } else {
                System.out.println("Log file already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Move log file to a new directory
    public void moveLogFile(String newDirectoryPath) {
        try {
            File file = new File(directoryPath, fileName);
            File newDir = new File(newDirectoryPath);

            if (file.renameTo(new File(newDir, fileName))) {
                System.out.println("Log file moved to: " + newDir.getAbsolutePath());
            } else {
                System.out.println("Failed to move the log file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     

    // Archive the log file
    public void archiveLogFile() {
        try {
            File file = new File(directoryPath, fileName);
            File archiveDir = new File(directoryPath, "archive");

            if (!archiveDir.exists()) {
                archiveDir.mkdir();
            }

            File archivedFile = new File(archiveDir, fileName);

            if (file.renameTo(archivedFile)) {
                System.out.println("Log file archived to: " + archiveDir.getAbsolutePath());
            } else {
                System.out.println("Failed to archive the log file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
 // Delete the log file
    public void deleteLogFile() {
        try {
            File file = new File(directoryPath, fileName);
            if (file.delete()) {
                System.out.println("Log file deleted.");
                System.out.println(" ");
            } else {
                System.out.println("Failed to delete the log file.");
                System.out.println(" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}

