package com.fhdo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.fhdo.model.Car;
import com.fhdo.model.EnergySource;
import com.fhdo.model.WaitingCar;

public class ChargingStationManager {
	private List<ChargingLot> chargingLots;
	private List<WaitingCar> waitingList;
	private List<EnergySource> energySource;
	private boolean isAssigned;

	public ChargingStationManager(int numLots) {
		this.energySource = new ArrayList<>();
		chargingLots = new ArrayList<>();
		this.waitingList = new ArrayList<>();

		for (int i = 0; i < numLots; i++) {
			ChargingLot chargingLot = new ChargingLot(i + 1);
			chargingLots.add(chargingLot);
		}
	}

	public void handleWaitingList() {
		Thread thread = new Thread(() -> {
			while (!waitingList.isEmpty()) {
				try {
					for (ChargingLot chargingLot : chargingLots) {
						if (chargingLot.getisAvailable()) {
							WaitingCar waitingCarPop = waitingList.remove(0);
							//chargingLot.chargeCar(waitingCarPop.getCar(), waitingCarPop.getEnergySource());
							chargingLot.chargeCar(waitingCarPop.getCar(), this.energySource);
							break;
						}
					}
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		});

		thread.start();
	}

	public void addCarToChargingStation(Car car) {
		isAssigned = false;

		for (ChargingLot chargingLot : chargingLots) {
			if (chargingLot.getisAvailable()) {
				chargingLot.chargeCar(car, this.energySource);
				this.isAssigned = true;
				break;
			}
		}

		if (!isAssigned) {
			for (ChargingLot chargingLot : chargingLots) {
				if (chargingLot.getremainingTime() < 15) {
					WaitingCar waitingCar = new WaitingCar(car);
					waitingList.add(waitingCar);
					System.out.println("Car " + car.getBrand() + " added to the waiting list");
					break;
				}
				// TBD -> will be implemented when we use multiple stations
				System.out.println("Car " + car.getBrand() + " need to go another station.");
			}
		}
	}

	public void addEnergySource(EnergySource energySource) {
		this.energySource.add(energySource);
	}

}
