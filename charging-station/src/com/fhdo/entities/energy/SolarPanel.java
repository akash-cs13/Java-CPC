package com.fhdo.entities.energy;

public class SolarPanel implements energySources {
	private double availableEnergy;
	private EnergyStatus energystatus;
	private energyType energytype;
	
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
	
	public void decrementenergySourcesByOneUnit() {
		this.availableEnergy--;
	}
	
	public EnergyStatus getEnergyStatus() {
		return energystatus;
	}

	public void setEnergyStatus(EnergyStatus energystatus) {
		this.energystatus = energystatus;
	}
	
	public energyType getEnergyType() {
		return this.energytype;
	}
	
}
