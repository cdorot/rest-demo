package com.digiplug.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.NotFoundException;

import org.junit.Test;

public class UserClientIT {

	private UserClient userClient;

	public UserClientIT() {
		super();

		this.userClient = new UserClient();
	}

	@Test
	public void getUser() {
		Long userId = 1l;

		User user = this.userClient.getUser(userId);

		assertNotNull(user);
		assertEquals(userId, user.getId());
	}

	@Test(expected = NotFoundException.class)
	public void getUnknownUser() {
		this.userClient.getUser(43l);
	}

	@Test
	public void postUser() {
		User user = new User();

		user.setFirstname("matthieu");
		user.setLastname("chedid");

		user = this.userClient.postUser(user);

		assertNotNull(user.getId());
	}

}
