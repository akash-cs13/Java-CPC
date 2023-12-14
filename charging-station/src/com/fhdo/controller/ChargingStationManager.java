package com.fhdo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.fhdo.entities.cars.Car;
import com.fhdo.entities.cars.WaitingCar;
import com.fhdo.entities.energy.SolarPanel;
import com.fhdo.entities.energy.energySources;
import com.fhdo.entities.weather.weatherType;

import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class ChargingStationManager {
	private List<ChargingLot> chargingLots;
	private List<WaitingCar> waitingList;
	private List<energySources> energySources;
	private boolean isAssigned;
	private energyManager energyManager;
	private weatherCondition weathercondition;
	
	/* Quang test */
	/*
	final Logger LOGGER = Logger.getLogger("Logger");
	private Date date = new Date();
	private LogFileClass logfileManager = new LogFileClass(date, "Station", LOGGER);
	*/

	public ChargingStationManager(int numLots) {
		this.energySources = new ArrayList<>();
		chargingLots = new ArrayList<>();
		this.waitingList = new ArrayList<>();
		
		for (int i = 0; i < numLots; i++) {
			ChargingLot chargingLot = new ChargingLot(i + 1);
			chargingLots.add(chargingLot);
		}
		
		energyManager = new energyManager(this.energySources);
		weathercondition = new weatherCondition(energyManager);
	}
	
	public void weatherSimulation(weatherType weathertype) {
		weathercondition.weatherSimulation(weathertype);
	}
	
	public void handleTotalEnergy() {
		Thread handleTotalEnergyThread = new Thread(() -> {
			energyManager.calculateTotalEnergy();
		});
		handleTotalEnergyThread.start();
	}

	public void handleWaitingList() {
		Thread thread = new Thread(() -> {
			while (!waitingList.isEmpty()) {
				try {
					for (ChargingLot chargingLot : chargingLots) {
						if (chargingLot.getisAvailable()) {
							WaitingCar waitingCarPop = waitingList.remove(0);
							//chargingLot.chargeCar(waitingCarPop.getCar(), waitingCarPop.getenergySources());
							chargingLot.chargeCar(waitingCarPop.getCar(), energyManager);
							break;
						}
					}
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		thread.start();
	}
	
	public void handleRechargingEnergy() {
		Thread handleRechargingEnergyThread = new Thread ( ()-> {
			while(true) {
				if (this.weathercondition.getCurrentWeather() == weatherType.RAIN) {
					
				}
			}
			
		});
		
		handleRechargingEnergyThread.start();
	}
	

	public void addCarToChargingStation(Car car) {
		isAssigned = false;

		// Find the free charging lot
		for (ChargingLot chargingLot : chargingLots) {
			if (chargingLot.getisAvailable()) {
				chargingLot.chargeCar(car, energyManager);
				this.isAssigned = true;
				break;
			}
		}
		
		/* If all of the charging lots is currently used. The waiting time will be checked*/
		if (!isAssigned) {
			for (ChargingLot chargingLot : chargingLots) {
				if (chargingLot.getremainingChargeTime() < 15) {
					WaitingCar waitingCar = new WaitingCar(car);
					waitingList.add(waitingCar);
					System.out.println("Car " + car.getBrand() + " added to the waiting list");
					isAssigned = true;
				}
			}
		}
		if (!isAssigned) {
			// TBD -> will be implemented when we use multiple stations
			System.out.println("Car " + car.getBrand() + " need to go another station.");
		}
	}

	public void addenergySources(energySources energySources) {
		this.energySources.add(energySources);
	}

}
