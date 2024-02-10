package com.fhdo;

import java.text.SimpleDateFormat;
import java.util.List;

import com.fhdo.controller.CarReader;
import com.fhdo.controller.ChargingStationManager;
import com.fhdo.controller.LogFileManager;
import com.fhdo.controller.UserManager;
import com.fhdo.entities.*;
import com.fhdo.entities.cars.Car;
import com.fhdo.entities.energy.GridElectricity;
import com.fhdo.entities.energy.SolarPanel;
import com.fhdo.entities.energy.WindTurbine;
import com.fhdo.entities.energy.energySources;
import com.fhdo.entities.users.User;
import com.fhdo.entities.weather.weatherType;
import com.fhdo.metadata.ProjectMetadata;

@ProjectMetadata(projectName = "Capstone Project Team 13", version = "4.0", description = "Project for Compact Java Course", developer = {
		"Nhat Quang Nguyen", "Nhat Lam Nguyen", "Hermann Anguiga", "Akash Cuntur Shrinivasmurthy" })

public class Main {

	public static void main(String[] args) throws Exception {

		ChargingStationManager chargingStationManager = new ChargingStationManager(3, "2");
		LogFileManager logFileManager = new LogFileManager("res/logs/day_1/");

		// Create energy sources
		energySources solarPanel = new SolarPanel(250.0);
		energySources windTurbine = new WindTurbine(200.0);
		energySources gridElectricity = new GridElectricity(800.0);

		// Add energy sources to the charging station
		chargingStationManager.addenergySources(solarPanel);
		chargingStationManager.addenergySources(windTurbine);
		chargingStationManager.addenergySources(gridElectricity);

		chargingStationManager.weatherSimulation(weatherType.SUN);
		chargingStationManager.handleTotalEnergy();

		// Create cars
		CarReader reader = new CarReader();
		List<Car> cars = reader.readFile("\\res\\input\\Cars.txt");

		// Charge cars at the charging station
		for (Car car : cars) {
			chargingStationManager.addCarToChargingStation(car);
		}

		// Handle waiting list in a separate thread
		chargingStationManager.handleWaitingList();

		// User Management - Will be integrated when implement the capstone project
		User user1 = new User("name", 123, "username", "123a", "Admin");
		UserManager user = new UserManager();
		user.addUser(user1);

		chargingStationManager.getLogFileForUsersByDate(user1, logFileManager, 1);
		chargingStationManager.getLogFileForUsersByLotID(user1, logFileManager, 2);
	}
}
