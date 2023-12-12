package com.fhdo;

import java.util.List;

import com.fhdo.controller.CarReader;
import com.fhdo.controller.ChargingStation;
import com.fhdo.controller.MultiExceptionHandling;
import com.fhdo.controller.ProjectMetadata;
import com.fhdo.model.*;
import java.lang.annotation.*;


@ProjectMetadata(
	projectName = "Capstone Project Team 13",
	version = "1.0",
	description = "Project for Compact Java Course", 
	developer = {"Nhat Quang Nguyen", "Nhat Lam Nguyen", "Hermann Anguiga", "Akash Cuntur Shrinivasmurthy"}
)


public class Main {
	
	public static void main(String[] args) throws Exception {
		ChargingStation chargingStation = new ChargingStation();
	
	    // Create energy sources
	    EnergySource solarPanel = new SolarPanel();
	    EnergySource windTurbine = new WindTurbine();
	    EnergySource gridElectricity = new GridElectricity();
	
	    // Add energy sources to the charging station
	    chargingStation.addEnergySource(solarPanel);
	    chargingStation.addEnergySource(windTurbine);
	    chargingStation.addEnergySource(gridElectricity);
	
	    //Read from file - Resource Handling
	    CarReader reader = new CarReader();
	    List<Car> cars = reader.readFile("\\res\\Cars.txt");
	    
	    // Create cars
	    Car car1 = cars.get(0);
	    Car car2 = cars.get(1);
	
	    // Charge cars at the charging station
	    chargingStation.startCharging(car1, solarPanel);
	    chargingStation.startCharging(car2, windTurbine);

		//Multiple Exception Example

		MultiExceptionHandling multi = new MultiExceptionHandling();
		multi.multiException();


          // creation of an empty log file which triggers other log files creation
    	LogFile dumLog = new LogFile ("empty_file.txt", "C:\\Users\\anguiga\\Desktop\\C_Station");
    	dumLog.createLogFile();
    	dumLog.moveLogFile("C:\\Users\\anguiga\\Desktop\\C_Station");
    	dumLog.archiveLogFile();
    	dumLog.deleteLogFile();
    	
    	
    	
    	LogFile sysLog = new LogFile ("system_log.txt", "C:\\Users\\anguiga\\Desktop\\C_Station");
    	
    	// system simulation
    	sysLog.createLogFile();
    	
    	SystemFunctionality system = new SystemFunctionality ();
    	system.logSysFunc("1- Schedule EV chargers availability", sysLog);
    	system.logSysFunc("2- Track energy costs and usage", sysLog);
    	system.logSysFunc("3- Automate invoicing, payment, and collection for charging sessions", sysLog);
    	
    	sysLog.moveLogFile("C:\\Users\\anguiga\\Desktop\\C_Station");
    	sysLog.archiveLogFile();
    	sysLog.deleteLogFile();
    	
    	
        LogFile chargingLog = new LogFile("charging_log.txt", "C:\\Users\\anguiga\\Desktop\\C_Station");

        // Simulating actions
        chargingLog.createLogFile();

        ChargingStation station = new ChargingStation();
        station.logAction("1- Charging started", chargingLog);
        station.logAction("2- Charging completed", chargingLog);
        

        chargingLog.moveLogFile("C:\\Users\\anguiga\\Desktop\\C_Station");
        chargingLog.archiveLogFile();
        chargingLog.deleteLogFile();
        
        //simulation energy type usage
        LogFile energingLog = new LogFile("energing_log.txt", "C:\\Users\\anguiga\\Desktop\\C_Station");

        energingLog.createLogFile();
        
        EnergyManagementSystem type1 = new EnergyManagementSystem ();    
        type1.logEnerManage("1- You are currently using energy generated from the Sun", energingLog);
        type1.logEnerManage("2- You are done using energy generated from the Sun", energingLog);
        
        
        
        EnergyManagementSystem type2 = new EnergyManagementSystem ();
        type2.logEnerManage("3- You are now using Energy generated from Wind", energingLog);
        type2.logEnerManage("4- You are done using energy generated from Wind", energingLog);
        
       energingLog.moveLogFile("C:\\Users\\anguiga\\Desktop\\C_Station");
       energingLog.archiveLogFile();
       energingLog.deleteLogFile();

	

	}
}
