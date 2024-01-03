package com.fhdo.entities.users;

public class Timeslot {
    private String startTime;
    private String endTime;

    public Timeslot(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
    
}
