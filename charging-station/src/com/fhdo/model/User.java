package com.fhdo.model;

import com.fhdo.controller.ChargingStation;

public class User {
	private String name;
	private int id;
	private String username; 
	private String passwort; 
	private String role; // "User", "Priority", "Admin"
	
	public User(String name, int id, String username, String passwort, String role) {
		super();
		this.name = name;
		this.id = id;
		this.username = username;
		this.passwort = passwort;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public void bookTimeSlot (ChargingStation chargingStation) {
		if (this.role == "Priority" || this.role == "Admin") {
			System.out.println("Booking Time Slot");
		}
		else {
			System.out.println("Not enough Authority");
		}
	}
	
	public void prioritzedQueue (ChargingStation chargingStation) {
		if (this.role == "Admin") {
			System.out.println("Charging in Admin Slots");
		}
		else {
			System.out.println("Not enough Authority");
		}
		
	}
	
	public void changeRole (User user) {
		if (this.role == "Admin") {
			if (user.getRole() == "User") {
				user.setRole("Priority");
			}
			if (user.getRole() == "Priority") {
				user.setRole("User");
			}
			if (user.getRole() == "Admin") {
				System.out.println("This is your Coworker. Not enough Authority.");
			}
		}
		else {
			System.out.println("Not enough Authority");
		}
	}

	
}

	
