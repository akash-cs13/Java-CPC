package com.fhdo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fhdo.entities.cars.Car;
import com.fhdo.entities.energy.energySources;
import com.fhdo.errors.InsufficientEnergyException;

public class ChargingLot {

	private int lotId;
	private boolean isAvailable;
	private double remainingChargeTime;

	public ChargingLot(int lotId) {
		this.lotId = lotId;
		this.isAvailable = true;
		this.remainingChargeTime = 0;
	}

	public void chargeCar(Car car, energyManager energyManager) {
		this.isAvailable = false;
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
						System.out.println("Charging Lot " + lotId + " will be fininished in " + this.remainingChargeTime + " seconds");
					} else
					{
						// Out of energy
						throw new InsufficientEnergyException();
					}				
			}
			this.isAvailable = true;
			System.out.println("Charging Lot " + lotId + " finished charging for Car " + car.getBrand());
		});
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
	}
}
