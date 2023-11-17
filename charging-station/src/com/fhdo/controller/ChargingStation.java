package com.fhdo.controller;

import java.util.ArrayList;
import java.util.List;

import com.fhdo.model.Car;
import com.fhdo.model.EnergySource;

public class ChargingStation {
	private List<EnergySource> energySource;

	public ChargingStation() {
		this.energySource = new ArrayList<>();
	}

	public void addEnergySource(EnergySource energySource) {
		this.energySource.add(energySource);
	}

	public void startCharging(Car car, EnergySource energySource) throws Exception {
		try {
			if (energySource.getAvailableEnergy() < car.getBatteryCapacity()) {
				throw new UnvailableEnergySourceException("The availabe source of energy is not enough for charging");
			}
			System.out.print("Start charging on " + car.getBrand() + "\n");
		} catch (Exception e) {
			System.out.print("Sorry! There is a problem with ..." + car.getBrand() + "\n");
			throw e;
		}

	}

}
