package com.fhdo.entities;

public class SolarPanel implements EnergySource {
	private double availableEnergy;

	public SolarPanel() {
		this.availableEnergy = 1000;
	}
	public SolarPanel(double Energy) {
		this.availableEnergy = Energy;
	}

	public double getAvailableEnergy() {
		return availableEnergy;
	}
	
	public void setAvailableEnergy(double availableEnergy) {
		this.availableEnergy = availableEnergy;
	}
	
	public String getEnergyType() {
		return "Solar Energy";
	}
}
