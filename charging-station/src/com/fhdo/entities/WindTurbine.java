package com.fhdo.entities;

public class WindTurbine implements EnergySource {
	private double availableEnergy;
	
	public WindTurbine() {
		this.availableEnergy = 500;
	}
	
	public double getAvailableEnergy() {
		return availableEnergy;
	}
	
	public void setAvailableEnergy(double availableEnergy) {
		this.availableEnergy = availableEnergy;
	}
}
