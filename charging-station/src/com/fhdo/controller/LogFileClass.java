/* NO NEED TO USE THIS. 
 * This is just some testing. */

package com.fhdo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class LogFileClass {

	private Date date;
	private String logFileType; // Daily, Station, EnergySource, System
	private Logger LOGGER; // final Logger LOGGER = Logger.getLogger(this.logFileName+this.date);

	LogFileClass(Date date, String logFileType, Logger LOGGER) {
		this.date = date;
		this.logFileType = logFileType;
		this.LOGGER = LOGGER;
	}

	LogFileClass() {

	}

	public void init() {

		try {
			int fileSizeLimit = 10 * 1024 * 1024; // 10 MB
			int fileCount = 5;

			// Log files for each day
			if (this.logFileType == "Daily") {
				FileHandler dailyFileHandler = new FileHandler(this.date + ".log", fileSizeLimit, fileCount, true);
				this.LOGGER.addHandler(dailyFileHandler);
				dailyFileHandler.setFormatter(new SimpleFormatter());
				dailyFileHandler.setLevel(Level.ALL);
			}

			// Log file for the system as a whole
			if (logFileType == "System") {
				FileHandler systemFileHandler = new FileHandler("system.log", fileSizeLimit, fileCount, true);
				this.LOGGER.addHandler(systemFileHandler);
				systemFileHandler.setFormatter(new SimpleFormatter());
				systemFileHandler.setLevel(Level.ALL);
			}

			// Log files for each charging station
			if (logFileType == "Station") {
				FileHandler chargingStationFileHandler = new FileHandler("charging-station.log", fileSizeLimit,
						fileCount, true);
				this.LOGGER.addHandler(chargingStationFileHandler);
				chargingStationFileHandler.setFormatter(new SimpleFormatter());
				chargingStationFileHandler.setLevel(Level.ALL);
			}

			// Log files for each energy source
			if (logFileType == "EnergySource") {
				FileHandler energySourceFileHandler = new FileHandler("energy-source.log", fileSizeLimit, fileCount,
						true);
				this.LOGGER.addHandler(energySourceFileHandler);
				energySourceFileHandler.setFormatter(new SimpleFormatter());
				energySourceFileHandler.setLevel(Level.ALL);
			}

		} catch (IOException e) {
			this.LOGGER.log(Level.WARNING, "Exception::", e);
		}
		
		LOGGER.info("This is an informational message");
        LOGGER.warning("This is a warning message");
        LOGGER.severe("This is a severe error message");
	}
}