package com.digiplug.persistence.exceptions;

public class UnknownUserException extends RuntimeException {

	private static final long serialVersionUID = 2373208428705351278L;

	public UnknownUserException(String message) {
		super(message);
	}

}
