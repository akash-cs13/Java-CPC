package com.fhdo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fhdo.entities.*;
import com.fhdo.entities.energy.energySources;
import com.fhdo.entities.users.User;

public class UserManager {
	private List<User> userList;
	private ChargingStationManager chargingStationManager;
	
	public UserManager() {
		this.userList = new ArrayList<User>();
	}
	
	public void addUser(User user) {
		this.userList.add(user);
	}
	
	public void removeUser(User user) {
		this.userList.remove(user);
	}

}
