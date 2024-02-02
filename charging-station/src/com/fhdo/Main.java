package com.fhdo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private static final int MAX_ATTEMPTS = 10;

	//recursive function to simulate for not assigned cars; 
	public static void simulation(List<Car> cars, ChargingStationManager[] managers, int attempt) {
	    if (cars.isEmpty() || attempt > MAX_ATTEMPTS) {
	        return; 
	    }

	    List<Car> notAssigned = new ArrayList<>();
	    Random random = new Random();

	   for (Car car : cars) {
	        int index = random.nextInt(managers.length);
	        if (!managers[index].addCarToChargingStation(car)) {
	            notAssigned.add(car);
	        }
	    }

	    for (ChargingStationManager chargingstation : managers) {
	        chargingstation.handleWaitingList();
	    }

	    //for (Car car : notAssigned) {
	        //System.out.println("Attempt " + attempt + ": Car " + car.getBrand() + " id " + car.getId() + " needs to be reassigned");
	    //}

	    // Wait for 5 seconds before the next attempt
	    try {
	        Thread.sleep(5000);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        return;
	    }

	    simulation(notAssigned, managers, attempt + 1);
	}

	public static void main(String[] args) throws Exception {
		String day = "5";
		ChargingStationManager[] managers = new ChargingStationManager[2];

		managers[0] = new ChargingStationManager("001", 3, day);
		managers[1] = new ChargingStationManager("002", 2, day);
		LogFileManager logFileManager = new LogFileManager("res/logs/day_" + day + "/");

		// Create energy sources
		energySources solarPanel = new SolarPanel(10250.0);
		energySources windTurbine = new WindTurbine(200.0);
		energySources gridElectricity = new GridElectricity(800.0);

		// Add energy sources to the charging station
		managers[0].addenergySources(solarPanel);
		managers[0].addenergySources(windTurbine);
		managers[0].addenergySources(gridElectricity);
		managers[0].weatherSimulation(weatherType.SUN);
		managers[0].handleTotalEnergy();

		managers[1].addenergySources(solarPanel);
		managers[1].weatherSimulation(weatherType.SUN);
		managers[1].handleTotalEnergy();

		// Create cars
		CarReader reader = new CarReader();
		List<Car> cars = reader.readFile("\\res\\input\\Cars20.txt");

		simulation(cars, managers,1);
		
		// User Management - Will be integrated when implement the capstone project
		// User user1 = new User("name", 123, "username", "123a", "Admin");
		// UserManager user = new UserManager();
		// user.addUser(user1);

		// managers[0].getLogFileForUsersByDate(user1, logFileManager, 1);
		// managers[0].getLogFileForUsersByLotID(user1, logFileManager, 2);
	}
}
