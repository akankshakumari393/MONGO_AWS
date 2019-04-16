package com.gslab.mongo5.view;

public class EmployeeView {
	@Override
	public String toString() {
		return "EmployeeView [id=" + id + ", name=" + name + ", lead=" + lead + ", managerId=" + managerName
				+ ", practiceHead=" + practiceHead + ", roleName=" + roleName + ", projectName=" + projectName + "]";
	}
	private String id;
	private String name;
	public EmployeeView(String id, String name, String lead, String managerName, String practiceHead, String roleName, String projectName) {
		super();
		this.id = id;
		this.name = name;
		this.lead = lead;
		this.managerName = managerName;
		this.practiceHead = practiceHead;
		this.roleName = roleName;
		this.projectName = projectName;
	}
	private String lead;
	private String managerName;
	private String practiceHead;
	private String roleName;
	private String projectName;
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
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getPracticeHead() {
		return practiceHead;
	}
	public void setPracticeHead(String practiceHead) {
		this.practiceHead = practiceHead;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
