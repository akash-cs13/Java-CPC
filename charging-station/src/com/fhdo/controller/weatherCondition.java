package com.fhdo.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.fhdo.entities.energy.energyType;
import com.fhdo.entities.weather.weatherType;

public class weatherCondition {
	private weatherType currentWeather;
	private energyManager energymanager;
	private energyType enerytype;
	private Logger logger;

	public weatherCondition(weatherType weather, energyManager energymanager, String day) {
		this.setCurrentWeather(weather);
		this.energymanager = energymanager;
		initLogger(day);
       
	}

	public weatherCondition(energyManager energymanager, String day) {
		this.energymanager = energymanager;
		initLogger(day);
	}
	
	private void initLogger(String day) {
        FileHandler fileHandler;
		try {
			fileHandler = new FileHandler("res/logs/day_"+day+"/WeatherLog.log");
	        fileHandler.setFormatter(new SimpleFormatter());
	        this.logger = Logger.getLogger(weatherCondition.class.getName());
	        this.logger.addHandler(fileHandler);
	        this.logger.setLevel(Level.INFO);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void weatherSimulation(weatherType weather) {
		Thread weatherSimulatedThread = new Thread(() -> {
			
			// Simulate the weather condition
			switch (weather) {
			case RAIN:
				System.out.print("weatherType.RAIN \n");
				this.logger.info("weatherType.RAIN");
				energymanager.disableSourceEnergy(energyType.SOLAR_PANEL);
				try {
					TimeUnit.SECONDS.sleep(50); // Check weather condition each 30 seconds just for debugging
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case SUN:
				System.out.print("weatherType.SUN \n");
				this.logger.info("weatherType.SUN");
				try {
					TimeUnit.SECONDS.sleep(50); // Check weather condition each 30 seconds just for debugging
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				energymanager.enableSourceEnergy(energyType.SOLAR_PANEL);
				break;
			default:
				energymanager.enableSourceEnergy(energyType.SOLAR_PANEL);
				energymanager.enableSourceEnergy(energyType.GRID_ELECTRICITY);
				energymanager.enableSourceEnergy(energyType.WIND_TURBINE);
			}

			
		});
		weatherSimulatedThread.start();
	}

	public weatherType getCurrentWeather() {
		return currentWeather;
	}

	public void setCurrentWeather(weatherType currentWeather) {
		this.currentWeather = currentWeather;
	}

}
