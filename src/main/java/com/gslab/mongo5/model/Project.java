package com.gslab.mongo5.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Project {

	@Id 
	private String id;
	private String projectName;
	
	
	public Project() {
	}
	public Project(String id, String projectName) {
		super();
		this.id = id;
		this.projectName = projectName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
