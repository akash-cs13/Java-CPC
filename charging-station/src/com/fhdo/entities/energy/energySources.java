package com.fhdo.entities.energy;

public interface energySources {
	double getAvailableEnergy();
	public void setAvailableEnergy(double availableEnergy);
	public void decrementenergySourcesByOneUnit();
	public energyType getEnergyType();
	public void setEnergyStatus(EnergyStatus energystatus);
	public EnergyStatus getEnergyStatus();
	// TBD
}

