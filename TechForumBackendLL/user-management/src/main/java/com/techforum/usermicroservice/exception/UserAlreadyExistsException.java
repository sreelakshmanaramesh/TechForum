package com.techforum.usermicroservice.exception;

public class UserAlreadyExistsException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException() {
		super("User already exists");
	}
}
