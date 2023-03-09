package com.techforum.qamicroservice.exception;

public class InvalidCredentialsException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException(String err) {
		super(err);
	}
}
