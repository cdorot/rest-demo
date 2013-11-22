package com.digiplug.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.JSONP;

import com.digiplug.persistence.entities.User;
import com.digiplug.persistence.services.UserService;

@Path("user")
public class UserResource {

	@Inject
	private UserService userService;

	@GET
	@Path("{userId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public User getUserAsJson(@PathParam("userId") Long userId) {
		return this.userService.findUserById(userId);
	}

	@GET
	@Path("jsonp/{userId}")
	@JSONP(callback = "eval", queryParam = "jsonp")
	@Produces({ "application/javascript" })
	public User getUserAsJsonP(@PathParam("userId") Long userId) {
		return this.userService.findUserById(userId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User postUser(User user) {
		return this.userService.persist(user);
	}

}
