package com.gslab.mongo5.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Holiday {

	@Id
	String date;

	public String getDate() {
		return date;
	}

	public Holiday() {
		super();
	}

	public Holiday(String date) {
		super();
		this.date = date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
