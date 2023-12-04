package com.fhdo;

import com.fhdo.controller.ChargingStationManager;
import com.fhdo.entities.*;

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

		// Create cars
		Car car1 = new Car("Tesla", 300.0, 2);
		Car car2 = new Car("Nissan Leaf", 250.0, 2);
		Car car3 = new Car("Toyota1", 450.0, 6);
		Car car4 = new Car("Toyota2", 500.0, 7);
		
		// Charge cars at the charging station
		chargingStationManager.addCarToChargingStation(car1);	
		chargingStationManager.addCarToChargingStation(car2);
		chargingStationManager.addCarToChargingStation(car3);
		chargingStationManager.addCarToChargingStation(car4);
		
		// Handle waiting list in a separate thread
		chargingStationManager.handleWaitingList();

	}
}
