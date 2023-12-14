package com.fhdo.controller;

import java.io.IOException;
import java.util.ArrayList;
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
	private Logger LOGGER = Logger.getLogger("Logger_ChargeLot");
	private FileHandler chargingLotFileHandler;
	private String logFolderPath = "logs/LogFileChargeLot/";
	
	
	public ChargingLot(int lotId) {
		this.lotId = lotId;
		this.isAvailable = true;
		this.remainingChargeTime = 0;
	}
	
	public int getID() {
		return this.lotId;
	}
	
	public void InitLogger() {

		try {
			int fileSizeLimit = 10 * 1024 * 1024; // 10 MB
			int fileCount = 5;

			// Log files for each day
			
			chargingLotFileHandler = new FileHandler(logFolderPath+"charging-log" + Integer.toString(this.lotId)+".log", fileSizeLimit, fileCount, true);
			this.LOGGER.addHandler(chargingLotFileHandler);
			chargingLotFileHandler.setFormatter(new SimpleFormatter());
			chargingLotFileHandler.setLevel(Level.ALL);
			

			// Log files for each energy source
			
		} catch (IOException e) {
			this.LOGGER.log(Level.WARNING, "Exception::", e);
		}
	}
	public void chargeCar(Car car, energyManager energyManager) {
		this.isAvailable = false;
		Thread thread = new Thread(() -> {
			while (car.getbatteryFullCapacity() - car.getBatteryCurrentCapacity() > 0) {
					if (energyManager.getTotalEnergy() > 0) {
						this.remainingChargeTime = car.getbatteryFullCapacity() - car.getBatteryCurrentCapacity();
						energyManager.decrementTotalEnergy(10);
						car.increaseBatteryCurrentCapacity(10);
						try {
						TimeUnit.SECONDS.sleep(1); // Suppose one unit of energy needs 10 second to charge
						} catch (InterruptedException e) {
							e.printStackTrace();
							this.LOGGER.warning(e.getMessage());
						}
					} else
					{
						// Out of energy
						throw new InsufficientEnergyException();
					}				
			}
			this.isAvailable = true;
			System.out.println("Charging Lot " + lotId + " finished charging for Car " + car.getBrand() + " with license: " + car.getId() + " in " + Double.toString(this.remainingChargeTime));
			this.LOGGER.info("Charging Lot " + lotId + " finished charging for Car " + car.getBrand() + " with license: " + car.getId() + " in " + Double.toString(this.remainingChargeTime));
		});
		System.out.println("Car " + car.getBrand() + " assigned to Charging Lot " + lotId);
		this.LOGGER.info("Car " + car.getBrand() + " assigned to Charging Lot " + lotId);
		System.out.println("Charging Lot " + lotId + " started charging for " + car.getchargingTime() + " seconds");
		this.LOGGER.info("Charging Lot " + lotId + " started charging for " + car.getchargingTime() + " seconds");
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
		this.LOGGER.info("Charging Lot " + lotId + " shutting down");
	}
}
