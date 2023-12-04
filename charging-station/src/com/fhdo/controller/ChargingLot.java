package com.fhdo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fhdo.entities.Car;
import com.fhdo.entities.EnergySource;
import com.fhdo.errors.InsufficientEnergyException;

public class ChargingLot {

	//private List<EnergySource> energySource;

	private int lotId;
	private boolean isAvailable;
	private double remainingTime;

	public ChargingLot(int lotId) {
		//this.energySource = new ArrayList<>();
		this.lotId = lotId;
		this.isAvailable = true;
		this.remainingTime = 0;
	}

	public void chargeCar(Car car, List<EnergySource> energySources) {
		this.remainingTime = car.getchargingTime();
		this.isAvailable = false;
		Thread thread = new Thread(() -> {
			for(EnergySource energySource: energySources) {
				if(this.isAvailable == false) {
					if(car.getBatteryCapacity()<=energySource.getAvailableEnergy()) {
						while (remainingTime > 0) {
							remainingTime--;
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						//Draining energy from source to charge the car
						energySource.setAvailableEnergy(energySource.getAvailableEnergy()-car.getBatteryCapacity());
						
						this.isAvailable = true;
						System.out.println("Charging Lot " + lotId + " finished charging the Car " + car.getBrand() + " with " + energySource.getEnergyType());
					} else{
						//Partially charge the car
						car.setBatteryCapacity(car.getBatteryCapacity()-energySource.getAvailableEnergy());
						//System.out.println("Charging Lot " + lotId + " partially charged the Car " + car.getBrand() + " with " + energySource.getEnergyType());
					}
				}
			}
			if(this.isAvailable == false) {
				this.isAvailable = true;
				//Not enough energy
				throw new InsufficientEnergyException();
			}
			
		});
		System.out.println("Car " + car.getBrand() + " assigned to Charging Lot " + lotId);
		System.out.println("Charging Lot " + lotId + " started charging for " + car.getchargingTime() + " seconds");
		thread.start();

	}

	public boolean getisAvailable() {
		return this.isAvailable;
	}

	public double getremainingTime() {
		return this.remainingTime;
	}

	public void shutdown() {
		System.out.println("Charging Lot " + lotId + " shutting down");
	}
}
