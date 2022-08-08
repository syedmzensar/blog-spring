package com.syed.blog.exception;

public class ResourceNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6163250354768477979L;
	String message;

	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
