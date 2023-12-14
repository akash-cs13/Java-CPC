package com.fhdo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.fhdo.entities.*;
import com.fhdo.entities.energy.EnergyStatus;
import com.fhdo.entities.energy.energySources;
import com.fhdo.entities.energy.energyType;

public class energyManager {
	private double totalEnergy;
	private List<energySources> energySources;
	private Logger LOGGER = Logger.getLogger("Logger_Energy");
	private FileHandler chargingEnergyFileHandler;
	private String logFolderPath = "logs/LogFileEnaergy/";

	energyManager(List<energySources> energySources) {
		this.energySources = energySources;
	}
	
	
	public void InitLogger() {

		try {
			int fileSizeLimit = 10 * 1024 * 1024; // 10 MB
			int fileCount = 5;

			// Log files for each day
			
			chargingEnergyFileHandler = new FileHandler(logFolderPath+"energy-log.log", fileSizeLimit, fileCount, true);
			this.LOGGER.addHandler(chargingEnergyFileHandler);
			chargingEnergyFileHandler.setFormatter(new SimpleFormatter());
			chargingEnergyFileHandler.setLevel(Level.ALL);
			

			// Log files for each energy source
			
		} catch (IOException e) {
			this.LOGGER.log(Level.WARNING, "Exception::", e);
		}
		
		while(true) {
			try {
			this.LOGGER.info("Energy Amount: " + Double.toString(this.totalEnergy));
			TimeUnit.SECONDS.sleep(1);
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.LOGGER.info(e.getMessage());
			}
		}	
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