package com.fhdo.entities;

public class GridElectricity implements EnergySource {
	private double availableEnergy;

	public GridElectricity() {
		this.availableEnergy = 800;
	}
	
	public GridElectricity(double Energy) {
		this.availableEnergy = Energy;
	}
	
	public double getAvailableEnergy() {
		return availableEnergy;
	}
	
	public void setAvailableEnergy(double availableEnergy) {
		this.availableEnergy = availableEnergy;
	}
	
	public String getEnergyType() {
		return "Grid Energy";
	}
}
