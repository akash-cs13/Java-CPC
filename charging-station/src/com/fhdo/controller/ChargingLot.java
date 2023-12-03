package com.fhdo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fhdo.entities.Car;
import com.fhdo.entities.EnergySource;

public class ChargingLot {

	private List<EnergySource> energySource;

	private int lotId;
	private boolean isAvailable;
	private double remainingTime;

	public ChargingLot(int lotId) {
		this.energySource = new ArrayList<>();
		this.lotId = lotId;
		this.isAvailable = true;
		this.remainingTime = 0;
	}

	public void chargeCar(Car car, EnergySource energySource) {
		this.remainingTime = car.getchargingTime();
		this.isAvailable = false;
		Thread thread = new Thread(() -> {
			while (remainingTime > 0) {
				remainingTime--;
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.isAvailable = true;
			System.out.println("Charging Lot " + lotId + " finished charging for Car " + car.getBrand());
		});

		thread.start();
		System.out.println("Car " + car.getBrand() + " assigned to Charging Lot " + lotId);
		System.out.println("Charging Lot " + lotId + " started charging for " + car.getchargingTime() + " seconds");
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
