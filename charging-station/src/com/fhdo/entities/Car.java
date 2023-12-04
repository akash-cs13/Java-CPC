package com.fhdo.entities;

public class Car {
	private String brand;
	private double chargingTime;  // in minutes
	private double batteryCapacity;
	

	public Car(String brand, double batteryCapacity, double chargingTime) {
		this.brand = brand;
		this.batteryCapacity = batteryCapacity;
		this.chargingTime = chargingTime;
	}
	
	public double getchargingTime() {
		return chargingTime;
	}
	
	public double getBatteryCapacity() {
		return batteryCapacity;
	}
	
	public void setBatteryCapacity(double capacity) {
		//Using this to partially charge the car
		this.batteryCapacity = capacity;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void charge(EnergySource energySource, double chargingTime) {
		//TBD
	}

	public int getRemainingTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
