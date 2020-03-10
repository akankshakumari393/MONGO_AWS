package com.gslab.mongo5.exception;

public class ProjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectNotFoundException(String id) {
		super("Could not find project " + id);
	}

}
