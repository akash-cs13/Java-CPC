package com.fhdo.model;

public class SolarPanel implements EnergySource {
	private double availableEnergy;

	public SolarPanel() {
		this.availableEnergy = 1000;
	}

	public double getAvailableEnergy() {
		return availableEnergy;
	}
	
	public void setAvailableEnergy(double availableEnergy) {
		this.availableEnergy = availableEnergy;
	}
}
