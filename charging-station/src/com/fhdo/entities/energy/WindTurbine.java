package com.fhdo.entities.energy;

public class WindTurbine implements energySources {
	private double availableEnergy;
	private EnergyStatus energystatus;
	private energyType energytype;
	
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
