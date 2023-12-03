package com.fhdo.entities;

public class Time {
	private double currentTime;
	
	public Time() {
		this.currentTime = 0;
	}

	public double getcurrentTime() {
		return currentTime;
	}

	public void startTimer(double condition) {
		while(currentTime < condition) {			
			this.currentTime++;
			 System.out.println(currentTime);
		}
	}
}
