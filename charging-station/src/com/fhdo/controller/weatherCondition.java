package com.fhdo.controller;

import java.util.concurrent.TimeUnit;

import com.fhdo.entities.*;
import com.fhdo.entities.energy.energyType;
import com.fhdo.entities.weather.weatherType;

public class weatherCondition {
	private weatherType currentWeather;
	private energyManager energymanager;
	private energyType enerytype;

	public weatherCondition(weatherType weather, energyManager energymanager) {
		this.setCurrentWeather(weather);
		this.energymanager = energymanager;
	}

	public weatherCondition(energyManager energymanager) {
		this.energymanager = energymanager;
	}

	public void weatherSimulation(weatherType weather) {
		Thread weatherSimulatedThread = new Thread(() -> {
			while (true) {
				// Simulate the weather condition
				switch (weather) {
				case RAIN:
					energymanager.disableSourceEnergy(energyType.SOLAR_PANEL);
					try {
						TimeUnit.SECONDS.sleep(30); // Check weather condition each 30 seconds just for debugging
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case SUN:
					System.out.print("weatherType.SUN \n");
					try {
						TimeUnit.SECONDS.sleep(30); // Check weather condition each 30 seconds just for debugging
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
