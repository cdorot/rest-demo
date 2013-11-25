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

	@Test(expected = NotFoundException.class)
	public void deleteUser() {
		Long userId = 1l;

		this.userClient.deleteUser(userId);
		this.userClient.getUser(userId);
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

	@Test
	public void putUser() {
		String firstname = "homer";
		Long userId = 2l;

		User user = this.userClient.getUser(userId);

		user.setFirstname(firstname);

		user = this.userClient.putUser(user);

		assertEquals(firstname, user.getFirstname());
	}

}
