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
import com.gslab.mongo5.assembler.RoleResourceAssembler;
import com.gslab.mongo5.model.Employee;
import com.gslab.mongo5.model.Role;
import com.gslab.mongo5.service.RoleService;
import com.gslab.mongo5.view.EmployeeView;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;

	@Autowired
	RoleResourceAssembler roleAssembler;

	@Autowired
	EmployeeResourceAssembler employeeAssembler;

	@GetMapping("/roles")
	public Resources<Resource<Role>> getAllRole() {
		// System.out.println("getting all role");
		List<Role> role = roleService.getAllRole();

		List<Resource<Role>> roles = role.stream().map(roleAssembler::toResource).collect(Collectors.toList());
		// System.out.println("Going to get all role");
		return new Resources<>(roles, linkTo(methodOn(RoleController.class).getAllRole()).withSelfRel());
	}

	@PostMapping("/roles")
	public ResponseEntity<?> newRole(@RequestBody Role role) throws URISyntaxException {

		Role savedRole = roleService.addRole(role);
		Resource<Role> resource = roleAssembler.toResource(savedRole);

		return ResponseEntity.created(new URI(resource.getId().expand(savedRole.getId()).getHref())).body(resource);

	}

	@GetMapping("/roles/{id}")
	public Resource<Role> getRole(@PathVariable String id) {
		// System.out.println("in getting role with id"+ id);
		Role role = roleService.getRole(id);
		// System.out.println("role id: "+role.get_id()+"role name :"+
		// role.getRolename());
		return roleAssembler.toResource(role);
	}

	@PutMapping("/roles/{id}")
	ResponseEntity<?> replaceRole(@RequestBody Role newRole, @PathVariable String id) throws URISyntaxException {

		Role updatedRole = roleService.updateRole(newRole, id);

		Resource<Role> resource = roleAssembler.toResource(updatedRole);

		return ResponseEntity.created(new URI(resource.getId().expand(updatedRole.getId()).getHref())).body(resource);
	}

	@DeleteMapping("/roles/{id}")
	ResponseEntity<?> deleteRole(@PathVariable String id) {

		roleService.deleteRole(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/roles/{id}/employees")
	public Resources<Resource<EmployeeView>> getEmployeesByRole(@PathVariable String id) {

		List<Employee> employee = roleService.getEmployeesByRoleId(id);

		List<Resource<EmployeeView>> employees = employee.stream().map(employeeAssembler::toResource)
				.collect(Collectors.toList());
		// System.out.println("Going to get all employee");
		return new Resources<>(employees, linkTo(methodOn(EmployeeController.class).getAllEmployee()).withSelfRel());
	}

}
