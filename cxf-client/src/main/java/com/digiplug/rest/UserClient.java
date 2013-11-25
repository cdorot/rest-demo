package com.digiplug.rest;

import java.util.Collections;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class UserClient {

	static final String GET_USER_ENDPOINT = "/cxf-service/webapi/users/{userId}";

	static final String USER_RESOURCE_ENDPOINT = "/cxf-service/webapi/users";

	private String baseAddress;

	public UserClient(String baseAddress) {
		super();

		this.baseAddress = baseAddress;
	}

	public Response deleteUser(Long userId) {
		WebClient client = WebClient.create(this.baseAddress, Collections.singletonList(new JacksonJaxbJsonProvider()))
				.path(GET_USER_ENDPOINT, userId);

		Response response = client.delete();

		return response;
	}

	public User getUser(Long userId) {
		WebClient client = WebClient.create(this.baseAddress, Collections.singletonList(new JacksonJaxbJsonProvider()))
				.path(GET_USER_ENDPOINT, userId);

		User user = client.accept(MediaType.APPLICATION_JSON).get(User.class);

		return user;
	}

	public User postUser(User user) {
		WebClient client = WebClient.create(this.baseAddress, Collections.singletonList(new JacksonJaxbJsonProvider()))
				.path(USER_RESOURCE_ENDPOINT);

		Response response = client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(user);

		return response.readEntity(User.class);
	}

	public User putUser(User user) {
		WebClient client = WebClient.create(this.baseAddress, Collections.singletonList(new JacksonJaxbJsonProvider()))
				.path(GET_USER_ENDPOINT, user.getId());

		user = client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(user, User.class);

		return user;
	}

}
