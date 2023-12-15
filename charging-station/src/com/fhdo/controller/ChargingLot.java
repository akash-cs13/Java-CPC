package com.fhdo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fhdo.entities.cars.Car;
import com.fhdo.entities.energy.energySources;
import com.fhdo.errors.InsufficientEnergyException;
import java.util.logging.*;

public class ChargingLot {

	private int lotId;
	private boolean isAvailable;
	private double remainingChargeTime;
	private String logFolderPath = "logs/LogFileChargeLot/";
	
	
	public ChargingLot(int lotId) {
		this.lotId = lotId;
		this.isAvailable = true;
		this.remainingChargeTime = 0;
	}
	
	public int getID() {
		return this.lotId;
	}
	public void chargeCar(Car car, energyManager energyManager) {
		this.isAvailable = false;
		Logger LOGGER = Logger.getLogger(ChargingLot.class.getName()+Integer.toString(lotId));
		try {
			int fileSizeLimit = 10 * 1024 * 1024; // 10 MB
			int fileCount = 5;	
			FileHandler chargingLotFileHandler = new FileHandler(logFolderPath+"charging-log" + Integer.toString(this.lotId)+".log", fileSizeLimit, fileCount, true);
			LOGGER.addHandler(chargingLotFileHandler);
			chargingLotFileHandler.setFormatter(new SimpleFormatter());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread thread = new Thread(() -> {
			while (car.getbatteryFullCapacity() - car.getBatteryCurrentCapacity() > 0) {
					if (energyManager.getTotalEnergy() > 0) {
						this.remainingChargeTime = car.getbatteryFullCapacity() - car.getBatteryCurrentCapacity();
						/* The corresponding energy is transfered from station capacity to car */
						energyManager.decrementTotalEnergy(10);
						car.increaseBatteryCurrentCapacity(10);
						try {
						TimeUnit.SECONDS.sleep(1); // Suppose 10 unit of energy needs 1 second to charge
						} catch (InterruptedException e) {
							e.printStackTrace();
							LOGGER.warning(e.getMessage());
						}
						System.out.println("Charging Lot " + lotId + " will be fininished in " + this.remainingChargeTime + " seconds");
							LOGGER.info("Charging Lot " + lotId + " will be fininished in " + this.remainingChargeTime + " seconds");
						
						
					} else
					{
						// Out of energy
						throw new InsufficientEnergyException();
					}				
			}
			this.isAvailable = true;
			System.out.println("Charging Lot " + lotId + " finished charging for Car " + car.getBrand() + " with license: " + car.getId() + " in " + Double.toString(this.remainingChargeTime));
			LOGGER.info("Charging Lot " + lotId + " finished charging for Car " + car.getBrand() + " with license: " + car.getId() + " in " + Double.toString(this.remainingChargeTime));
		});
		LOGGER.info("Car " + car.getBrand() + " assigned to Charging Lot " + lotId);
		LOGGER.info("Charging Lot " + lotId + " started charging");
		
		System.out.println("Car " + car.getBrand() + " assigned to Charging Lot " + lotId);
		System.out.println("Charging Lot " + lotId + " started charging");
		thread.start();
	}

	public boolean getisAvailable() {
		return this.isAvailable;
	}

	public double getremainingChargeTime() {
		return this.remainingChargeTime;
	}

	public void shutdown() {
		System.out.println("Charging Lot " + lotId + " shutting down");
		Logger LOGGER = Logger.getLogger(ChargingLot.class.getName()+Integer.toString(lotId));
		try {
			int fileSizeLimit = 10 * 1024 * 1024; // 10 MB
			int fileCount = 5;	
			FileHandler chargingLotFileHandler = new FileHandler(logFolderPath+"charging-log" + Integer.toString(this.lotId)+".log", fileSizeLimit, fileCount, true);
			LOGGER.addHandler(chargingLotFileHandler);
			chargingLotFileHandler.setFormatter(new SimpleFormatter());
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info("Charging Lot " + lotId + " shutting down");
	}
}
