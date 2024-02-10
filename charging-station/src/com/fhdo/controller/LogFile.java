package com.fhdo.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

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

class ChargingStation {
    // Functionality related to the charging station

    public void logAction(String action, LogFile logFile) {
        // Log charging station actions to a file
    	
    	 
    	
        try(FileWriter writer = new FileWriter("C:\\Users\\anguiga\\Desktop\\C_Station\\archive\\charging_log.txt", true)){ 
            writer.write( action + "\n");
            writer.close();
            
            
            
             boolean append = true;
            FileHandler handler = new FileHandler("default.log", append);
     
            Logger logger = Logger.getLogger("www.fh-dortmund.de");
            logger.addHandler(handler);             
            logger.severe("severe message");  
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class EnergyManagementSystem {
    // Functionality related to the energy management system
	
	 public void logEnerManage(String manage, LogFile logFile) {
		 

	        try(FileWriter renewal = new FileWriter("C:\\Users\\anguiga\\Desktop\\C_Station\\archive\\energing_log.txt", true)){ 
	        	renewal.write( manage + "\n");
	        	renewal.close();
	            
	            
	            
	             boolean append = true;
	            FileHandler handler = new FileHandler("default.log", append);
	     
	            Logger logger = Logger.getLogger("www.fh-dortmund.de");
	            logger.addHandler(handler);             
	            logger.severe("severe message");  
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }		 
		 
	 }  	
}



class SystemFunctionality {
	// Functionality related to the functionalities of the system
	
	public void logSysFunc(String func, LogFile logFile) {
		 
		
        try(FileWriter choose = new FileWriter("C:\\Users\\anguiga\\Desktop\\C_Station\\archive\\system_log.txt", true)){ 
        	choose.write( func + "\n");
        	choose.close();
            
                        
             boolean append = true;
            FileHandler handler = new FileHandler("default.log", append);
     
            Logger logger = Logger.getLogger("www.fh-dortmund.de");
            logger.addHandler(handler);             
            logger.severe("severe message");  
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	 
	 
 }
		
}

class Dummy {
	
	public void logDummy(String func, LogFile logFile) {
		
		
		try(FileWriter empty = new FileWriter(logFile.fileName, true)) {
			
		} catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	
	
}




