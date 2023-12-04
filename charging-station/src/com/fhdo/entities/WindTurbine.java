package com.fhdo.entities;

public class WindTurbine implements EnergySource {
	private double availableEnergy;
	
	public WindTurbine() {
		this.availableEnergy = 500;
	}
	
	public WindTurbine(double Energy) {
		this.availableEnergy = Energy;
	}
	
	public double getAvailableEnergy() {
		return availableEnergy;
	}
	
	public void setAvailableEnergy(double availableEnergy) {
		this.availableEnergy = availableEnergy;
	}
	
	public String getEnergyType() {
		return "Wind Energy";
	}
}
