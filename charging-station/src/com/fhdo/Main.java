package com.fhdo;

import com.fhdo.controller.ChargingStationManager;
import com.fhdo.entities.*;

public class Main {

	public static void main(String[] args) throws Exception {

		ChargingStationManager chargingStationManager = new ChargingStationManager(3);
		
		
		// Create energy sources
		EnergySource solarPanel = new SolarPanel();
		EnergySource windTurbine = new WindTurbine();
		EnergySource gridElectricity = new GridElectricity();

		// Add energy sources to the charging station
		chargingStationManager.addEnergySource(solarPanel);
		chargingStationManager.addEnergySource(windTurbine);
		chargingStationManager.addEnergySource(gridElectricity);

		// Create cars
		Car car1 = new Car("Tesla", 300.0, 2);
		Car car2 = new Car("Nissan Leaf", 250.0, 2);
		Car car3 = new Car("Toyota", 250.0, 6);
		Car car4 = new Car("Toyota", 250.0, 7);
		
		// Charge cars at the charging station
		chargingStationManager.addCarToChargingStation(car1, solarPanel);	
		chargingStationManager.addCarToChargingStation(car2, windTurbine);
		chargingStationManager.addCarToChargingStation(car3, gridElectricity);
		chargingStationManager.addCarToChargingStation(car4, windTurbine);
		
		// Handle waiting list in a separate thread
		chargingStationManager.handleWaitingList();

	}
}
