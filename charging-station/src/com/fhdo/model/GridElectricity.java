package com.fhdo.model;

public class GridElectricity implements EnergySource {
	private double availableEnergy;

	public GridElectricity() {
		this.availableEnergy = 800;
	}
	
	public double getAvailableEnergy() {
		return availableEnergy;
	}
	
	public void setAvailableEnergy(double availableEnergy) {
		this.availableEnergy = availableEnergy;
	}
}
