package com.fhdo.test.cases;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fhdo.entities.users.User;
import com.fhdo.controller.UserManager;

public class UserManagerTest {
	
	@DisplayName("Test check for User Manager")
	@Test
	public void testUserManager() {
		User user1 = new User("John Doe",  123, "username1", "123ab", "Admin");
		User user2 = new User("France Doe",  234, "username2", "123ac", "User");
		UserManager user = new UserManager();
		user.addUser(user1);
		user.addUser(user2);
		assertEquals(2, user.getUserList().size());
	}
}
