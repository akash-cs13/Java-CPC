package com.fhdo.test.cases;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fhdo.controller.LogFileManager;
import com.fhdo.entities.users.User;

public class LogFileManagerLotTest {
	
	@DisplayName("Test check for Log File Manager Lot")
	@Test
	public void testLogFileDay() {
		LogFileManager logFileManager = new LogFileManager("res/logs/day_1/");
		User user1 = new User("John Doe",  123, "username", "123a", "Admin");
		List<String> matchingFilesByLotID;

		matchingFilesByLotID = logFileManager.getLogFilesByDate(user1, "ChargingLot_1");
		assertEquals(6, matchingFilesByLotID.size());
		
	}
}