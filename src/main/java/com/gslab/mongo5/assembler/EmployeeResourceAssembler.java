package com.gslab.mongo5.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.gslab.mongo5.controller.EmployeeController;
import com.gslab.mongo5.controller.ProjectController;
import com.gslab.mongo5.controller.RoleController;
import com.gslab.mongo5.model.Employee;
import com.gslab.mongo5.view.EmployeeView;


@Component
public class EmployeeResourceAssembler implements ResourceAssembler<Employee,Resource<EmployeeView>> {

	@Override
	public Resource<EmployeeView> toResource(Employee entity) {
				
		String roleName=null, projectName=null, managerName=null;
		String id =entity.getId();
		String practiceHead = entity.getPracticeHead();
		String name = entity.getName();
		String lead = entity.getLead();
		if(entity.getManager()!=null)
			managerName = entity.getManager().getId();
		if(entity.getRole()!=null)
			roleName = entity.getRole().getRoleName();
		if(entity.getProject()!=null)
			projectName = entity.getProject().getProjectName();
		EmployeeView employeeView = new EmployeeView(id , name, lead, managerName, practiceHead, roleName, projectName);
				Resource<EmployeeView> employeeResource = new Resource<>(employeeView, 
				linkTo(methodOn(EmployeeController.class).getAllEmployee()).withRel("employees"),
				linkTo(methodOn(EmployeeController.class).getEmployee(entity.getId())).withSelfRel()
				);
		
		if (entity.getProject() != null) {
			employeeResource.add(linkTo(methodOn(ProjectController.class).getProject(entity.getProject().getId())).withRel("project"));
		}
		if (entity.getRole() != null) {
			employeeResource.add(linkTo(methodOn(RoleController.class).getRole(entity.getRole().getId())).withRel("role"));
		}
		if (entity.getManager() != null) {
			employeeResource.add(linkTo(methodOn(EmployeeController.class).getEmployee(entity.getManager().getId())).withRel("manager"));
		}
		return employeeResource;
		}

}
