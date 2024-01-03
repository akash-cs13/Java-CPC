package com.fhdo.controller;

import java.util.Date;

public class TimeManager {
    private Date currentTime;

    public TimeManager() {
        // Initialize the current time to the system's default time
        currentTime = new Date();
    }

    public void updateTime() {
        // Update the current time to reflect the system's current time
        currentTime = new Date();
    }

    public Date getCurrentTime() {
        return currentTime;
    }
}
