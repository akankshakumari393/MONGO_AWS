package com.gslab.mongo5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gslab.mongo5.exception.ProjectNotFoundException;
import com.gslab.mongo5.model.Employee;
import com.gslab.mongo5.model.Project;
import com.gslab.mongo5.repository.EmployeeRepository;
import com.gslab.mongo5.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public List<Project> getAllProject() {
		return (List<Project>) projectRepository.findAll();
	}

	public Project addProject(Project project) {
		return projectRepository.save(project);
	}

	public Project getProject(String id) {
		// System.out.println("finding by id "+ string);
		return projectRepository.findByid(id).orElseThrow(() -> new ProjectNotFoundException(id));
	}

	public Project updateProject(Project newProject, String id) {

		Project updatedProject = projectRepository.findByid(id).map(project -> {
			project.setProjectName(newProject.getProjectName());
			return projectRepository.save(project);
		}).orElseThrow(() -> new ProjectNotFoundException(id));
		return updatedProject;
	}

	public void deleteProject(String id) {
		// TODO Auto-generated method stub
		projectRepository.deleteByid(id);
	}

	public List<Employee> getEmployeesByProjectId(String id) {
		// System.out.print("fetching project by IDS");
		projectRepository.findByid(id).orElseThrow(() -> new ProjectNotFoundException(id));
		List<Employee> allEmployees = employeeRepository.findAll();
		// System.out.print("Got all IDS");

		allEmployees.removeIf(employee -> employee.getProject() == null || (!employee.getProject().getId().equals(id)));
		// System.out.print("removed unnecessary ids");
		return allEmployees;
	}
}
