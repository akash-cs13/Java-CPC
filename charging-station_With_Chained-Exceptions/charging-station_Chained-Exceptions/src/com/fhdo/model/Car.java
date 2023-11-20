package com.fhdo.model;

public class Car {
	private String brand;

	private double batteryCapacity;
	

	public Car(String brand, double batteryCapacity) {
		this.brand = brand;
		this.batteryCapacity = batteryCapacity;
	}
	
	public double getBatteryCapacity() {
		return batteryCapacity;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void charge(EnergySource energySource, double chargingTime) {
		//TBD
	}
}
