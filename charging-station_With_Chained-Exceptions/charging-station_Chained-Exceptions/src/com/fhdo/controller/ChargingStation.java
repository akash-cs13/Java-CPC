package com.fhdo.controller;

import java.io.IOException;
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

	public void startCharging(Car car, EnergySource energySource) throws UnvailableEnergySourceException, ChargingException {
	    try {
	    	
	    	UnvailableEnergySourceException e = new UnvailableEnergySourceException ("top level"); 
	    	e.initCause(new IOException ("IO cause"));
	    	throw e;
	    	
	       /* if (energySource.getAvailableEnergy() < car.getBatteryCapacity()) {
	            throw new UnvailableEnergySourceException("The available source of energy is not enough for charging", null);
	        } */
	        
	    } catch (UnvailableEnergySourceException e) {
	        System.out.println("Caught:  " + e);
	        System.out.println("Original cause: " +e.getCause());
	        throw new ChargingException("Error starting charging process", e);
	    }
	}


}
