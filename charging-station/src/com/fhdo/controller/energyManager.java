package com.fhdo.controller;

import java.util.ArrayList;
import java.util.List;
import com.fhdo.entities.*;
import com.fhdo.entities.energy.EnergyStatus;
import com.fhdo.entities.energy.energySources;
import com.fhdo.entities.energy.energyType;

public class energyManager {
	private double totalEnergy;
	private List<energySources> energySources;

	energyManager(List<energySources> energySources) {
		this.energySources = energySources;
	}

	public double getTotalEnergy() {
		return this.totalEnergy;
	}

	public void decrementTotalEnergy(double energyUnit) {
		this.totalEnergy -= energyUnit;
	}

	public void calculateTotalEnergy() {
		for (energySources energysource : this.energySources) {
			this.totalEnergy += energysource.getAvailableEnergy();
//			System.out.println("totalEnergy " + totalEnergy + "\n");
		}
	}

	public void disableSourceEnergy(energyType energytype) {
		for (energySources energysource : this.energySources) {
			if (energytype == energysource.getEnergyType() ) {
				energysource.setEnergyStatus(EnergyStatus.UNUSEABLE);
			}
		}
	}
	
	public void enableSourceEnergy(energyType energytype) {
		for (energySources energysource : this.energySources) {
			if (energytype == energysource.getEnergyType() ) {
				energysource.setEnergyStatus(EnergyStatus.USEABLE);
			}
		}
	}

	public void rechargeEnergy(energyType energytype) {
		for (energySources energysource : this.energySources) {
			if(energytype == energysource.getEnergyType()) {
				// energysource.rechargeByOneUnit();
			}
		}
	}

};