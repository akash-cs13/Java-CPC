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

	}
}
