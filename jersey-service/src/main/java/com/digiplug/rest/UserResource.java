package com.digiplug.rest;

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
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.JSONP;

import com.digiplug.persistence.entities.User;
import com.digiplug.persistence.services.UserService;

@Path("users")
public class UserResource {

	@Inject
	private UserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return this.userService.findAll();
	}

	/**
	 * @response.representation.200.mediaType application/json
	 * @response.representation.404.mediaType text/plain
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("userId") Long userId) {
		return this.userService.findUserById(userId);
	}

	@GET
	@Path("jsonp/{userId}")
	@JSONP(callback = "eval", queryParam = "jsonp")
	@Produces("application/javascript")
	public User getUserAsJsonP(@PathParam("userId") Long userId) {
		return this.getUser(userId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User postUser(User user) {
		return this.userService.persist(user);
	}

	/**
	 * 
	 * @param userId
	 *        the technical identifier of the user to delete
	 */
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
