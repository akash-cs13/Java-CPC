package com.fhdo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.fhdo.entities.cars.Car;
import com.fhdo.entities.cars.WaitingCar;
import com.fhdo.entities.energy.energySources;
import com.fhdo.entities.weather.weatherType;

public class ChargingStationManager {
	private List<ChargingLot> chargingLots;
	private List<WaitingCar> waitingList;
	private List<energySources> energySources;
	private boolean isAssigned;
	private energyManager energyManager;
	private weatherCondition weathercondition;
	private Date date;
	
	/* Quang test */
	
	private Logger LOGGER = Logger.getLogger("Logger_Station");
	private FileHandler chargingStationFileHandler;
	private String logFolderPath = "logs/LogFileStation/";
	
	
	public void InitLogger() {

		try {
			int fileSizeLimit = 10 * 1024 * 1024; // 10 MB
			int fileCount = 5;

			// Log files for each day
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			String formattedDate = dateFormat.format(date);
			chargingStationFileHandler = new FileHandler(logFolderPath + "charging-station"+ formattedDate +".log", fileSizeLimit, fileCount, true);
			this.LOGGER.addHandler(chargingStationFileHandler);
			chargingStationFileHandler.setFormatter(new SimpleFormatter());
			chargingStationFileHandler.setLevel(Level.ALL);
			

			// Log files for each energy source
			
		} catch (IOException e) {
			this.LOGGER.log(Level.WARNING, "Exception::", e);
		}
		
		LOGGER.info("This is an informational message");
        LOGGER.warning("This is a warning message");
        LOGGER.severe("This is a severe error message");
	}
	
	public ChargingStationManager(int numLots) {
		this.energySources = new ArrayList<>();
		chargingLots = new ArrayList<>();
		this.waitingList = new ArrayList<>();
		
		for (int i = 0; i < numLots; i++) {
			ChargingLot chargingLot = new ChargingLot(i + 1);
			chargingLots.add(chargingLot);
			chargingLot.InitLogger();
		}
		
		energyManager = new energyManager(this.energySources);
		weathercondition = new weatherCondition(energyManager);
	}
	
	public void ChargingStationInit() {
		energyManager.calculateTotalEnergy();
		weathercondition.weatherSimulation(weatherType.SUN);
	}

	public void handleWaitingList() {
		Thread thread = new Thread(() -> {
			while (!waitingList.isEmpty()) {
				try {
					for (ChargingLot chargingLot : chargingLots) {
						if (chargingLot.getisAvailable()) {
							WaitingCar waitingCarPop = waitingList.remove(0);
							//chargingLot.chargeCar(waitingCarPop.getCar(), waitingCarPop.getenergySources());
							chargingLot.chargeCar(waitingCarPop.getCar(), energyManager);
							break;
						}
					}
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.LOGGER.info(e.getMessage());
				}
			}
		});

		thread.start();
	}
	
	public void handleRechargingEnergy() {
		Thread handleRechargingEnergyThread = new Thread ( ()-> {
			while(true) {
				if (this.weathercondition.getCurrentWeather() == weatherType.RAIN) {
					
				}
			}
			
		});
		
		handleRechargingEnergyThread.start();
	}
	

	public void addCarToChargingStation(Car car) {
		isAssigned = false;

		// Find the free charging lot
		for (ChargingLot chargingLot : chargingLots) {
			if (chargingLot.getisAvailable()) {
				chargingLot.chargeCar(car, energyManager);
				this.isAssigned = true;
				this.LOGGER.info("Car:  " + " with ID: " + car.getId() + " charge in lot: " + chargingLot.getID());
				break;
			}
		}
		
		/* If all of the charging lots is currently used. The waiting time will be checked*/
		if (!isAssigned) {
			for (ChargingLot chargingLot : chargingLots) {
				if (chargingLot.getremainingChargeTime() < 15) {
					WaitingCar waitingCar = new WaitingCar(car);
					waitingList.add(waitingCar);
					System.out.println("Car " + car.getBrand()+ " with ID: " + car.getId() + " added to the waiting list");
					this.LOGGER.info("Car " + car.getBrand() + " with ID: " + car.getId() + " added to the waiting list");
					break;
				}
				// TBD -> will be implemented when we use multiple stations
				System.out.println("Car " + car.getBrand()+ " with ID: " + car.getId() + " need to go another station.");
				this.LOGGER.info("Car " + car.getBrand()+ " with ID: " + car.getId() + " added to the waiting list");
			}
		}
	}

	public void addenergySources(energySources energySources) {
		this.energySources.add(energySources);
	}

}
