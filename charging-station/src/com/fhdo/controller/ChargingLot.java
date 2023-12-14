package com.fhdo.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import com.fhdo.entities.cars.Car;
import com.fhdo.errors.InsufficientEnergyException;
import com.fhdo.logger.BasicLogger;

public class ChargingLot {

	private int lotId;
	private boolean isAvailable;
	private double remainingChargeTime;
	private BasicLogger logger;
	private final Lock lock = new ReentrantLock(); // thread saftey mechanism

	public ChargingLot(int lotId) {
		this.lotId = lotId;
		this.isAvailable = true;
		this.remainingChargeTime = 0;
		String cwd = System.getProperty("user.dir");
		this.logger = new BasicLogger(ChargingLot.class, cwd+"\\charging-station\\res\\logs\\ChargingStation\\ChargingLot("+this.lotId+").log");
	}
	
	public int getID() {
		return this.lotId;
	}
	
	
	public void chargeCar(Car car, energyManager energyManager) {
		//System.out.println("Car " + car.getBrand() + " assigned to Charging Lot " + lotId);
		logger.info("Car " + car.getBrand() + " assigned to Charging Lot " + lotId);
		//System.out.println("Charging Lot " + lotId + " started charging");
		logger.info("Charging Lot " + lotId + " started charging");

		this.isAvailable = false;
		
		lock.lock();
		try{
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

						}
						//System.out.println("Charging Lot " + lotId + " will be fininished in " + this.remainingChargeTime + " seconds");
						logger.info("Charging Lot " + lotId + " will be fininished in " + this.remainingChargeTime + " seconds");

					} else {
						// Out of energy
						logger.warning("Insufficient Energy in Charging Lot " + lotId + " to charge Car " + car.getBrand());
						throw new InsufficientEnergyException();
					}				
			}
			this.isAvailable = true;
			//System.out.println("Charging Lot " + lotId + " finished charging for Car " + car.getBrand());
			logger.info("Charging Lot " + lotId + " finished charging for Car " + car.getBrand() + " with license: " + car.getId() + " in " + Double.toString(this.remainingChargeTime));
			});
			thread.start();

		} finally {
            lock.unlock();
        }
		
	}

	public boolean getisAvailable() {
		return this.isAvailable;
	}

	public double getremainingChargeTime() {
		return this.remainingChargeTime;
	}

}
