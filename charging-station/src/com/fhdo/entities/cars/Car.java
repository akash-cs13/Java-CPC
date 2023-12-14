package com.fhdo.entities.cars;

import com.fhdo.entities.energy.energySources;

public class Car {
	private String brand;
	private double chargingTime;  // in minutes
	private double batteryFullCapacity;
	private double batteryCurrentCapacity;
	private String id; 
	

	public Car(String brand, double batteryFullCapacity, String id ) {
		this.brand = brand;
		this.batteryFullCapacity = batteryFullCapacity;
		this.batteryCurrentCapacity = 10;
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getchargingTime() {
		return chargingTime;
	}
	
	
	public double getbatteryFullCapacity() {
		return batteryFullCapacity;
	}
	
	public void increaseBatteryCurrentCapacity(double capacity){
		this.batteryCurrentCapacity += capacity;
	}
	
	public double getBatteryCurrentCapacity(){
		return batteryCurrentCapacity;
	}
	
	public void setbatteryFullCapacity(double capacity) {
		//Using this to partially charge the car
		this.batteryFullCapacity = capacity;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void charge(energySources energySources, double chargingTime) {
		//TBD
	}

	public int getremainingChargeTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
