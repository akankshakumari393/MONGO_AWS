package com.gslab.mongo5.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.mongo5.assembler.EmployeeResourceAssembler;
import com.gslab.mongo5.assembler.ProjectResourceAssembler;
import com.gslab.mongo5.controller.EmployeeController;
import com.gslab.mongo5.model.Employee;
import com.gslab.mongo5.model.Project;
import com.gslab.mongo5.service.ProjectService;
import com.gslab.mongo5.view.EmployeeView;

@RestController
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@Autowired
	ProjectResourceAssembler projectAssembler;

	@Autowired
	EmployeeResourceAssembler employeeAssembler;

	@GetMapping("/projects")
	public Resources<Resource<Project>> getAllProject() {
		// System.out.println("getting all project");
		List<Project> project = projectService.getAllProject();

		List<Resource<Project>> projects = project.stream().map(projectAssembler::toResource)
				.collect(Collectors.toList());
		// System.out.println("Going to get all project");
		return new Resources<>(projects, linkTo(methodOn(ProjectController.class).getAllProject()).withSelfRel());
	}

	@PostMapping("/projects")
	public ResponseEntity<?> newProject(@RequestBody Project project) throws URISyntaxException {

		Project savedProject = projectService.addProject(project);
		Resource<Project> resource = projectAssembler.toResource(savedProject);

		return ResponseEntity.created(new URI(resource.getId().expand(savedProject.getId()).getHref())).body(resource);

	}

	@GetMapping("/projects/{id}")
	public Resource<Project> getProject(@PathVariable String id) {
		Project project = projectService.getProject(id);
		return projectAssembler.toResource(project);
	}

	@PutMapping("/projects/{id}")
	ResponseEntity<?> replaceProject(@RequestBody Project newProject, @PathVariable String id)
			throws URISyntaxException {

		Project updatedProject = projectService.updateProject(newProject, id);

		Resource<Project> resource = projectAssembler.toResource(updatedProject);

		return ResponseEntity.created(new URI(resource.getId().expand(updatedProject.getId()).getHref()))
				.body(resource);
	}

	@DeleteMapping("/projects/{id}")
	ResponseEntity<?> deleteProject(@PathVariable String id) {

		projectService.deleteProject(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/projects/{id}/employees")
	public Resources<Resource<EmployeeView>> getEmployeesByProject(@PathVariable String id) {

		List<Employee> employee = projectService.getEmployeesByProjectId(id);

		List<Resource<EmployeeView>> employees = employee.stream().map(employeeAssembler::toResource)
				.collect(Collectors.toList());
		// System.out.println("Going to get all employee");
		return new Resources<>(employees, linkTo(methodOn(EmployeeController.class).getAllEmployee()).withSelfRel());
	}
}
