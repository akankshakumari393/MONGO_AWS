package com.gslab.mongo5.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author GS-1842
 *
 */

@Document
public class Employee {

	@Id
	private String id;
	private String name;
	private String lead;
	public Employee() {
		super();
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Employee(String id, String name, String lead, Employee manager, String practiceHead, Project project,
			Role role) {
		super();
		this.id = id;
		this.name = name;
		this.lead = lead;
		this.manager = manager;
		this.practiceHead = practiceHead;
		this.project = project;
		this.role = role;
	}
	@DBRef
	@Field("Manager")
	private Employee manager;
	private String practiceHead;
	@DBRef
	@Field("project")
	private Project project;
	
	@DBRef
	@Field("role")
	private Role role;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLead() {
		return lead;
	}
	public void setLead(String lead) {
		this.lead = lead;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public String getPracticeHead() {
		return practiceHead;
	}
	public void setPracticeHead(String practiceHead) {
		this.practiceHead = practiceHead;
	}
}
