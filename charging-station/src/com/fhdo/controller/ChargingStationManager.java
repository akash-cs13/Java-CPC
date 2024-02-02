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
import com.fhdo.entities.users.Booking;
import com.fhdo.entities.users.Timeslot;
import com.fhdo.entities.users.User;
import com.fhdo.entities.weather.weatherType;

public class ChargingStationManager {
	private List<ChargingLot> chargingLots;
	private List<WaitingCar> waitingList;
	private List<Booking> bookings;
	private String id;

	private volatile List<energySources> energySources;
	private boolean isAssigned;
	private volatile energyManager energyManager;
	private weatherCondition weathercondition;
	private Date date;
	private String day;
	private LogFileManager logFileManager;

	private TimeManager timeManager;

	private Logger LOGGER = Logger.getLogger("Logger_Station");
	private FileHandler chargingStationFileHandler;
	private String logFolderPath = "logs/LogFileStation/";

	public ChargingStationManager(String id, int numLots, String day) {
		this.energySources = new ArrayList<>();
		chargingLots = new ArrayList<>();
		this.waitingList = new ArrayList<>();
		this.bookings = new ArrayList<>();
		this.day = day;
		this.id = id;

		for (int i = 0; i < numLots; i++) {
			ChargingLot chargingLot = new ChargingLot(i + 1, day, id);
			chargingLots.add(chargingLot);
		}

		this.energyManager = new energyManager(this.energySources);
		this.weathercondition = new weatherCondition(energyManager, this.day);
		this.logFileManager = new LogFileManager("res/logs/" + day + "/");
		// Initialize the TimeManager
		this.timeManager = new TimeManager();
	}

	public void handleWaitingList() {
		Thread thread = new Thread(() -> {
			while (!waitingList.isEmpty()) {
				try {
					for (ChargingLot chargingLot : chargingLots) {
						if (chargingLot.getisAvailable()) {
							WaitingCar waitingCarPop = waitingList.remove(0);
							// chargingLot.chargeCar(waitingCarPop.getCar(),
							// waitingCarPop.getenergySources());
							chargingLot.chargeCar(waitingCarPop.getCar(), energyManager, timeManager);
							break;
						}
					}
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.LOGGER.warning(e.getMessage());
				}
			}
		});

		thread.start();
	}

	public void bookTimeslot(User user, Car car, Timeslot timeslot) {

		if (user.getRole() == "Priority" || user.getRole() == "Admin") {
			System.out.println("Booking Time Slot");

			Booking booking = new Booking(user, car, timeslot);

			if (isTimeslotAvailable(timeslot)) {
				bookings.add(booking);
				System.out.println("Booking successful!");
			} else {
				System.out.println("The timeslot is already booked.");
			}
		} else {
			System.out.println("Not enough Authority");
		}
	}

	private boolean isTimeslotAvailable(Timeslot timeslot) {
		for (Booking booking : bookings) {
			// Check if the start time of the existing booking is before the end time of the given timeslot,
			// and if the end time of the existing booking is after the start time of the given timeslot.
			if (booking.getTimeslot().getStartTime().compareTo(timeslot.getEndTime()) < 0
					&& booking.getTimeslot().getEndTime().compareTo(timeslot.getStartTime()) > 0) {
				// Overlapping timeslot found, so it is not available
				return false;
			}
		}

		// No overlapping timeslot found, so it is available
		return true;
	}

	public boolean addCarToChargingStation(Car car) {
		isAssigned = false;

		// Find the free charging lot
		for (ChargingLot chargingLot : chargingLots) {
			if (chargingLot.getisAvailable()) {
				chargingLot.chargeCar(car, energyManager, timeManager);
				this.isAssigned = true;
				//this.LOGGER.info("Car with ID: " + car.getId() + " charge in lot: " + chargingLot.getID());
				break;
			}
		}

		/*
		 * If all of the charging lots is currently used. The waiting time will be
		 * checked
		 */
		if (!isAssigned) {
			for (ChargingLot chargingLot : chargingLots) {
				if (chargingLot.getremainingChargeTime() < 15) {
					WaitingCar waitingCar = new WaitingCar(car);
					waitingList.add(waitingCar);
					System.out.println(
							"Car " + car.getBrand() + " with ID: " + car.getId() + " added to the waiting list");
					this.LOGGER
							.info("Car " + car.getBrand() + " with ID: " + car.getId() + " added to the waiting list");
					isAssigned = true;
					break;
				}

			}
		}
		if (!isAssigned) {
			// implemented, but janky
			
			System.out.println("Car " + car.getBrand() + " with ID: " + car.getId() + " need to go another station.");
			this.LOGGER.info("Car " + car.getBrand() + " with ID: " + car.getId() + " need to go another station.");
			return false;
		}
		return true;
	}

	public void getLogFileForUsersByLotID(User user, LogFileManager logFileManager, int lotID) {
		List<String> matchingFilesByLotID;
		matchingFilesByLotID = logFileManager.getLogFilesByEquipment(user, "ChargingLot_" + lotID);
		System.out.println("Open Log File by Lot ID: ");
		for (String matchingFile : matchingFilesByLotID) {
			System.out.println("Open the file in: " + matchingFile);
		}
	}

	public void getLogFileForUsersByDate(User user, LogFileManager logFileManager, int day) {
		List<String> matchingFilesByDate;

		matchingFilesByDate = logFileManager.getLogFilesByDate(user, "Day_" + day);
		System.out.println("Open Log File by Date number:");
		for (String matchingFile : matchingFilesByDate) {
			System.out.println("Open the file in: " + matchingFile);
		}
	}

	public void weatherSimulation(weatherType weathertype) {
		weathercondition.weatherSimulation(weathertype);
	}

	public void addenergySources(energySources energySources) {
		this.energySources.add(energySources);
	}

	public void handleTotalEnergy() {
		Thread handleTotalEnergyThread = new Thread(() -> {
			energyManager.calculateTotalEnergy();
		});
		handleTotalEnergyThread.start();
	}

	public void InitLogger() {

		try {
			int fileSizeLimit = 10 * 1024 * 1024; // 10 MB
			int fileCount = 5;

			// Log files for each day
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			String formattedDate = dateFormat.format(date);
			chargingStationFileHandler = new FileHandler(logFolderPath + "charging-station" + formattedDate + ".log",
					fileSizeLimit, fileCount, true);
			this.LOGGER.addHandler(chargingStationFileHandler);
			chargingStationFileHandler.setFormatter(new SimpleFormatter());
			chargingStationFileHandler.setLevel(Level.ALL);

			// Log files for each energy source

		} catch (IOException e) {
			this.LOGGER.log(Level.WARNING, "Exception::", e);
		}

		//LOGGER.info("This is an informational message");
		//LOGGER.warning("This is a warning message");
		//LOGGER.severe("This is a severe error message");
	}

}
