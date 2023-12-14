package com.fhdo;

import java.util.List;

import com.fhdo.controller.CarReader;
import com.fhdo.controller.ChargingStationManager;
import com.fhdo.metadata.ProjectMetadata;
import com.fhdo.model.*;

@ProjectMetadata(
	projectName = "Capstone Project Team 13",
	version = "4.0",
	description = "Project for Compact Java Course", 
	developer = {"Nhat Quang Nguyen", "Nhat Lam Nguyen", "Hermann Anguiga", "Akash Cuntur Shrinivasmurthy"}
)

public class Main {

	public static void main(String[] args) throws Exception {

		ChargingStationManager chargingStationManager = new ChargingStationManager(3);
		
		
		// Create energy sources
		EnergySource solarPanel = new SolarPanel(250.0);
		EnergySource windTurbine = new WindTurbine(200.0);
		EnergySource gridElectricity = new GridElectricity();

		// Add energy sources to the charging station
		chargingStationManager.addEnergySource(solarPanel);
		chargingStationManager.addEnergySource(windTurbine);
		chargingStationManager.addEnergySource(gridElectricity);

		//Read from file - Resource Handling
		CarReader reader = new CarReader();
		// try : \\res\\inputs\\Cars.txt or \\charging-station\\res\\inupts\\Cars.txt
	    List<Car> cars = reader.readFile("\\charging-station\\res\\inupts\\Cars.txt");

		// Charge cars at the charging station
		for (Car car : cars) {
			chargingStationManager.addCarToChargingStation(car);
		}

		
		// Handle waiting list in a separate thread
		chargingStationManager.handleWaitingList();

	}
}
