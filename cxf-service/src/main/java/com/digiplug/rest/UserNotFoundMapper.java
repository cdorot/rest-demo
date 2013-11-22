package com.digiplug.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.digiplug.persistence.exceptions.UnknownUserException;

@Provider
public class UserNotFoundMapper implements ExceptionMapper<UnknownUserException> {

	@Override
	public Response toResponse(UnknownUserException exception) {
		return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
	}

}
