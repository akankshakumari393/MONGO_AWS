package com.gslab.mongo5.exception;

public class RoleNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleNotFoundException(String id) {
		super("Could not find role " + id);
	}

}
