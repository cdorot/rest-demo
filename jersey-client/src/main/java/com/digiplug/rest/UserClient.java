package com.digiplug.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

public class UserClient {

	static final String USER_RESOURCE_ENDPOINT = "/jersey-service-demo/webapi/users";

	public User getUser(Long userId) {
		Client client = ClientBuilder.newClient().register(MoxyJsonFeature.class);
		User user = null;

		try {
			WebTarget userResource = client.target("http://localhost:8080").path(USER_RESOURCE_ENDPOINT)
					.path(userId.toString());

			user = userResource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(User.class);
		} finally {
			client.close();
		}

		return user;
	}

	public User postUser(User user) {
		Client client = ClientBuilder.newClient().register(MoxyJsonFeature.class);

		try {
			WebTarget userResource = client.target("http://localhost:8080").path(USER_RESOURCE_ENDPOINT);

			user = userResource.request(MediaType.APPLICATION_JSON).post(
					Entity.entity(user, MediaType.APPLICATION_JSON), User.class);
		} finally {
			client.close();
		}

		return user;
	}

	public Response deleteUser(Long userId) {
		Client client = ClientBuilder.newClient().register(MoxyJsonFeature.class);

		try {
			WebTarget userResource = client.target("http://localhost:8080").path(USER_RESOURCE_ENDPOINT)
					.path(userId.toString());

			userResource.request().delete();
		} finally {
			client.close();
		}

		return null;
	}

}
