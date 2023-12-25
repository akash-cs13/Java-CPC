package com.fhdo.entities.users;

import com.fhdo.controller.ChargingStationManager;
import com.fhdo.controller.LogFileManager;

public class User {
	private String name;
	private int id;
	private String username; 
	private String password; 
	private String role; // "User", "Priority", "Admin"
	
	public User(String name, int id, String username, String password, String role) {
		super();
		this.name = name;
		this.id = id;
		this.username = username;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwort) {
		this.password = passwort;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	