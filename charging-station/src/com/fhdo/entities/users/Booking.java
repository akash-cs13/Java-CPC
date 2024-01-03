package com.fhdo.entities.users;

import com.fhdo.entities.cars.Car;
import com.fhdo.entities.users.User;

public class Booking {
    private User user;
    private Car car;
    private Timeslot timeslot;

    public Booking(User user, Car car, Timeslot timeslot) {
        this.user = user;
        this.car = car;
        this.timeslot = timeslot;
    }

	public Timeslot getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(Timeslot timeslot) {
		this.timeslot = timeslot;
	}

    
}