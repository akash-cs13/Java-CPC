package com.fhdo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fhdo.entities.cars.Car;
import com.fhdo.entities.energy.energySources;
import com.fhdo.errors.InsufficientEnergyException;
import java.util.logging.*;

public class ChargingLot {

	private int lotId;
	private boolean isAvailable;
	private double remainingChargeTime;
	private String logFolderPath;
	private String day;
	private String stationId;
	
	
	public ChargingLot(int lotId, String day, String stationId) {
		this.lotId = lotId;
		this.isAvailable = true;
		this.remainingChargeTime = 0;
		this.day = day;
		this.stationId = stationId;
		this.logFolderPath = "res/logs/day_"+day+"/Station_"+stationId+"/";
		createDirectory(this.logFolderPath);

	}
	
	private static void createDirectory(String directoryPath) {
		Path path = Paths.get(directoryPath);
		
		// Create the directory if it doesn't exist
		if (Files.notExists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getID() {
		return this.lotId;
	}
	
	public void chargeCar(Car car, energyManager energyManager, TimeManager timeManager) {
		this.isAvailable = false;
		Logger LOGGER = Logger.getLogger(ChargingLot.class.getName() + Integer.toString(lotId));
		System.out.println("Car " + car.getBrand() + " with license "+car.getId()+" assigned to Charging Station "+this.stationId+" Charging Lot " + lotId);
		LOGGER.info("Car " + car.getBrand() + " with license "+car.getId()+"  assigned to  Charging Station "+this.stationId+" Charging Lot " + lotId);

		System.out.println("Charging Station "+this.stationId+" Charging Lot " + lotId + " started charging Car "+car.getBrand()+" with license "+car.getId());
		LOGGER.info("Charging Station "+this.stationId+" Charging Lot " + lotId + " started charging Car "+car.getBrand()+" with license "+car.getId());
		
		// Record the start time of the charging process
        Date startTime = timeManager.getCurrentTime();
		
		try {
			int fileSizeLimit = 10 * 1024 * 1024; // 10 MB
			int fileCount = 5;	
			FileHandler chargingLotFileHandler = new FileHandler(logFolderPath + "Day_" + day +"ChargingLot_" + Integer.toString(this.lotId)+".log", fileSizeLimit, fileCount, true);
			LOGGER.addHandler(chargingLotFileHandler);
			chargingLotFileHandler.setFormatter(new SimpleFormatter());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Thread thread = new Thread(() -> {
			try {
			while (car.getbatteryFullCapacity() - car.getBatteryCurrentCapacity() > 0) {
					if (energyManager.getTotalEnergy() > 0) {
						this.remainingChargeTime = car.getbatteryFullCapacity() - car.getBatteryCurrentCapacity();
						/* The corresponding energy is transfered from station capacity to car */
						energyManager.decrementTotalEnergy(10);
						car.increaseBatteryCurrentCapacity(10);
						try {
						TimeUnit.SECONDS.sleep(1); // Suppose 10 unit of energy needs 1 second to charge
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt(); // Restore the interrupted status
	                        LOGGER.warning("Charging interrupted: " + e.getMessage());
	                        break;
						}
						System.out.println("Charging Station "+this.stationId+" Charging Lot " + lotId + " will be fininished charging car "+car.getBrand()+" with licence: "+car.getId()+" in " + this.remainingChargeTime + " seconds");
							LOGGER.info("Charging Station "+this.stationId+" Charging Lot " + lotId + " will be fininished charging car "+car.getBrand()+" with licence: "+car.getId()+" in " + this.remainingChargeTime + " seconds");
						
						
					} else
					{
						// Out of energy
						throw new InsufficientEnergyException();
					}				
			}
			}
			catch (InsufficientEnergyException e){
				LOGGER.warning("Insufficient energy exception for car "+car.getBrand()+" with licence " + car.getId() + ", ErrorMessage: " + e.getMessage());
				e.printStackTrace();
			}
			this.isAvailable = true;
			System.out.println("Charging Station "+this.stationId+" Charging Lot " + lotId + " finished charging for Car " + car.getBrand() + " with license: " + car.getId() + " in " + Double.toString(this.remainingChargeTime)+ " seconds");
			LOGGER.info("Charging Station "+this.stationId+" Charging Lot " + lotId + " finished charging for Car " + car.getBrand() + " with license: " + car.getId() + " in " + Double.toString(this.remainingChargeTime)+ " seconds");
			
			// Calculate the charging duration for further implementation
	        Date endTime = timeManager.getCurrentTime();
	        long chargingDurationMillis = endTime.getTime() - startTime.getTime();
	        long chargingDurationSeconds = chargingDurationMillis / 1000;
		});
		thread.start();
	}

	public boolean getisAvailable() {
		return this.isAvailable;
	}

	public double getremainingChargeTime() {
		return this.remainingChargeTime;
	}

}
