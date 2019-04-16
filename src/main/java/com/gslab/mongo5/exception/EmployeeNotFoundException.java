package com.gslab.mongo5.exception;

public class EmployeeNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String id) {
		super("Could not find employee " + id);
	}

}
