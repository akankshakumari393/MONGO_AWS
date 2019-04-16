package com.gslab.mongo5.exception;

public class HolidayNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HolidayNotFoundException(String id) {
		super("Could not find holiday " + id);
	}

}
