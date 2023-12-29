package com.fhdo.test.cases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fhdo.entities.users.User;

public class UserTest {
	
	@DisplayName("Test check for User")
	@Test
	public void testUser() {
		User user1 = new User("John Doe",  123, "username", "123a", "Admin");
		assertEquals("Admin", user1.getRole());
	}
}
