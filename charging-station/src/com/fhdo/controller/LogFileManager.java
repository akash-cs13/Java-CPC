package com.fhdo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.fhdo.entities.users.User;

public class LogFileManager {
	private String logDirectory;

	public LogFileManager(String logDirectory) {
		this.logDirectory = logDirectory;

	}

	public List<String> getLogFilesByEquipment(User user, String equipmentName) {
		// Check authorities
		// Check priority

		List<String> matchingFiles = new ArrayList<>();
		if (user.getRole() == "Admin") {
			File logDir = new File(logDirectory);

			if (logDir.isDirectory()) {
				File[] files = logDir.listFiles();

				if (files != null) {
					for (File file : files) {
						String fileName = file.getName();
						if (fileName.contains(equipmentName)) {
							matchingFiles.add(file.getAbsolutePath());
						}
					}
				}
			}

		} else {
			System.out.println("Only Admin can access this file.");
		}

		return matchingFiles;
	}

	public List<String> getLogFilesByDate(User user, String day) {

		List<String> matchingFiles = new ArrayList<>();
		if (user.getRole() == "Admin") {
			
			File logDir = new File(logDirectory);
			if (logDir.isDirectory()) {
				File[] files = logDir.listFiles();

				if (files != null) {
					for (File file : files) {
						String fileName = file.getName();
						if (fileName.contains(day)) {
							matchingFiles.add(file.getAbsolutePath());
						}
					}
				}
			}

		} else {
			System.out.println("Only Admin can access this file.");
		}
		return matchingFiles;
	}
}
