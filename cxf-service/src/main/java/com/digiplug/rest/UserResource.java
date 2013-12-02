package com.digiplug.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.digiplug.persistence.entities.User;
import com.digiplug.persistence.services.UserService;

@Path("users")
public class UserResource {

	@Context
	private UriInfo uriInfo;

	@Inject
	private UserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return this.userService.findAll();
	}

	@GET
	@Path("{userId}")
	@Produces({ MediaType.APPLICATION_JSON, "application/javascript" })
	public User getUser(@PathParam("userId") Long userId) {
		return this.userService.findUserById(userId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postUser(User user) {
		User createdUser = this.userService.persist(user);

		URI createdUri = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdUser.getId())).build();

		return Response.created(createdUri).entity(createdUser).build();
	}

	@DELETE
	@Path("{userId}")
	public void deleteUser(@PathParam("userId") Long userId) {
		User user = this.userService.findUserById(userId);

		this.userService.delete(user);
	}

	@PUT
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User putUser(@PathParam("userId") Long userId, User user) {
		return this.userService.update(user);
	}

}
