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
import com.gslab.mongo5.model.Employee;
import com.gslab.mongo5.service.EmployeeService;
import com.gslab.mongo5.view.EmployeeView;

import io.swagger.annotations.ApiOperation;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeResourceAssembler employeeAssembler;

	@ApiOperation(value = "Get Employees List", notes = "Fetch All Employees")
	@GetMapping("/employees")
	public Resources<Resource<EmployeeView>> getAllEmployee() {
		List<Employee> employee = employeeService.getAllEmployee();
		List<Resource<EmployeeView>> employees = employee.stream().map(employeeAssembler::toResource)
				.collect(Collectors.toList());
		return new Resources<>(employees, linkTo(methodOn(EmployeeController.class).getAllEmployee()).withSelfRel());
	}

	@ApiOperation(value = "Create a new Employee", notes = "Creating a new Employee")
	@PostMapping("/employees")
	public ResponseEntity<?> newEmployee(@RequestBody EmployeeView employeeView) throws URISyntaxException {
		// System.out.println("calling post method");
		Employee savedEmployee = employeeService.addEmployee(employeeView);
		// System.out.println("Got saved Employee");
		Resource<EmployeeView> resource = employeeAssembler.toResource(savedEmployee);
		// System.out.println("Converting to resource and returning");
		return ResponseEntity.created(new URI(resource.getId().expand(savedEmployee.getId()).getHref())).body(resource);
	}

	@ApiOperation(value = "Get Employee By ID", notes = "Fetch a single Employee")
	@GetMapping("/employees/{id}")
	public Resource<EmployeeView> getEmployee(@PathVariable String id) {
		Employee employee = employeeService.getEmployee(id);
		return employeeAssembler.toResource(employee);
	}

	@ApiOperation(value = "Update Employee By ID", notes = "Updating the employee details")
	@PutMapping("/employees/{id}")
	ResponseEntity<?> replaceEmployee(@RequestBody EmployeeView newEmployee, @PathVariable String id)
			throws URISyntaxException {
		Employee updatedEmployee = employeeService.updateEmployee(newEmployee, id);
		Resource<EmployeeView> resource = employeeAssembler.toResource(updatedEmployee);
		return ResponseEntity.created(new URI(resource.getId().expand(updatedEmployee.getId()).getHref()))
				.body(resource);
	}

	@ApiOperation(value = "Delete Employee By ID", notes = "Delete a single Employee")
	@DeleteMapping("/employees/{id}")
	ResponseEntity<?> deleteEmployee(@PathVariable String id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/employees/{id}/subordinateEmployees")
	public Resources<Resource<EmployeeView>> getEmployeesUnderManagerId(@PathVariable String id) {

		List<Employee> employee = employeeService.getEmployeesUnderManagerId(id);

		List<Resource<EmployeeView>> employees = employee.stream().map(employeeAssembler::toResource)
				.collect(Collectors.toList());
		return new Resources<>(employees, linkTo(methodOn(EmployeeController.class).getAllEmployee()).withSelfRel());
	}

}
