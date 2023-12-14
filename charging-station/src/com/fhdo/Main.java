package com.fhdo;

import com.fhdo.controller.ChargingStationManager;
import com.fhdo.entities.*;
import com.fhdo.entities.cars.Car;
import com.fhdo.entities.energy.GridElectricity;
import com.fhdo.entities.energy.SolarPanel;
import com.fhdo.entities.energy.WindTurbine;
import com.fhdo.entities.energy.energySources;

public class Main {

	public static void main(String[] args) throws Exception {

		ChargingStationManager chargingStationManager = new ChargingStationManager(3);
		
		
		// Create energy sources
		energySources solarPanel = new SolarPanel(250.0);
		energySources windTurbine = new WindTurbine(200.0);
		energySources gridElectricity = new GridElectricity(800);

		// Add energy sources to the charging station
		chargingStationManager.addenergySources(solarPanel);
		chargingStationManager.addenergySources(windTurbine);
		chargingStationManager.addenergySources(gridElectricity);

		chargingStationManager.ChargingStationInit();
		
		// Create cars
		Car car1 = new Car("Tesla", 30, "DAX195");
		Car car2 = new Car("Nissan Leaf", 25.0, "DAG206");
		Car car3 = new Car("Toyota1", 45.0, "DA0908");
		Car car4 = new Car("Toyota2", 50.0, "DA3322");
		
		// Charge cars at the charging station
		chargingStationManager.addCarToChargingStation(car1);	
		chargingStationManager.addCarToChargingStation(car2);
		chargingStationManager.addCarToChargingStation(car3);
		chargingStationManager.addCarToChargingStation(car4);
		
		// Handle waiting list in a separate thread
		chargingStationManager.handleWaitingList();
		
		

	}
}
