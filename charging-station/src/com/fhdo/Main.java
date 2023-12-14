package com.fhdo;

import java.util.List;

import com.fhdo.controller.CarReader;
import com.fhdo.controller.ChargingStationManager;
import com.fhdo.entities.*;
import com.fhdo.entities.cars.Car;
import com.fhdo.entities.energy.GridElectricity;
import com.fhdo.entities.energy.SolarPanel;
import com.fhdo.entities.energy.WindTurbine;
import com.fhdo.entities.energy.energySources;
import com.fhdo.entities.weather.weatherType;

public class Main {

	public static void main(String[] args) throws Exception {

		ChargingStationManager chargingStationManager = new ChargingStationManager(3);
		weatherType weathertype;
		
		
		// Create energy sources
		energySources solarPanel = new SolarPanel(25.0);
		energySources windTurbine = new WindTurbine(20.0);
		energySources gridElectricity = new GridElectricity(80.0);

		// Add energy sources to the charging station
		chargingStationManager.addenergySources(solarPanel);
		chargingStationManager.addenergySources(windTurbine);
		chargingStationManager.addenergySources(gridElectricity);

		chargingStationManager.weatherSimulation(weatherType.SUN);
		chargingStationManager.handleTotalEnergy();
		
		// Create cars
		CarReader reader = new CarReader();
	    List<Car> cars = reader.readFile("\\charging-station\\res\\input\\Cars.txt");
		
		// Charge cars at the charging station
		for (Car car : cars) {
			chargingStationManager.addCarToChargingStation(car);
		}
		
		// Handle waiting list in a separate thread
		chargingStationManager.handleWaitingList();
		
		

	}
}
