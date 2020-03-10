package com.gslab.mongo5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gslab.mongo5.exception.EmployeeNotFoundException;
import com.gslab.mongo5.exception.ProjectNotFoundException;
import com.gslab.mongo5.exception.RoleNotFoundException;
import com.gslab.mongo5.model.Employee;
import com.gslab.mongo5.model.Project;
import com.gslab.mongo5.model.Role;
import com.gslab.mongo5.repository.EmployeeRepository;
import com.gslab.mongo5.repository.ProjectRepository;
import com.gslab.mongo5.repository.RoleRepository;
import com.gslab.mongo5.view.EmployeeView;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ProjectRepository projectRepository;

	public List<Employee> getAllEmployee() {
		return (List<Employee>) employeeRepository.findAll();
	}

	public Employee addEmployee(EmployeeView employeeView) {
		// System.out.println("In service method");
		Employee savedEmployee = toEmployee(employeeView);
		// System.out.println("having saved employee");
		Employee addedEmployee = employeeRepository.save(savedEmployee);
		// System.out.println("returning added employee");
		return addedEmployee;
	}

	public Employee getEmployee(String id) {
		return employeeRepository.findByid(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	public Employee updateEmployee(EmployeeView employeeView, String id) {
		Employee updateEmployee = employeeRepository.findByid(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		if (employeeView.getName() != null)
			updateEmployee.setName(employeeView.getName());
		if (employeeView.getPracticeHead() != null)
			updateEmployee.setPracticeHead(employeeView.getPracticeHead());
		if (employeeView.getLead() != null)
			updateEmployee.setLead(employeeView.getLead());
		if (employeeView.getManagerName() != null) {
			Employee manager = employeeRepository.findFirstByid(employeeView.getManagerName());
			if (manager == null)
				throw new EmployeeNotFoundException("employee not found");
			updateEmployee.setManager(manager);
		}
		if (employeeView.getRoleName() != null) {
			Role role = roleRepository.findFirstByRoleName(employeeView.getRoleName());
			if (role == null)
				throw new RoleNotFoundException("role not found");
			updateEmployee.setRole(role);
		}
		if (employeeView.getProjectName() != null) {
			Project project = projectRepository.findFirstByProjectName(employeeView.getProjectName());
			if (project == null)
				throw new ProjectNotFoundException("project not found");
			updateEmployee.setProject(project);
		}
		return employeeRepository.save(updateEmployee);
	}

	public void deleteEmployee(String id) {
		employeeRepository.deleteByid(id);
	}

	public List<Employee> getEmployeesUnderManagerId(String id) {
		employeeRepository.findByid(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		List<Employee> allEmployees = employeeRepository.findAll();
		// System.out.print("Got all IDS");

		allEmployees.removeIf(employee -> employee.getManager() == null || (!employee.getManager().getId().equals(id)));
		return allEmployees;
	}

	Employee toEmployee(EmployeeView employeeView) {
		Role role = null;
		Project project = null;
		Employee manager = null;
		String id = employeeView.getId();
		String practiceHead = employeeView.getPracticeHead();
		String name = employeeView.getName();
		String lead = employeeView.getLead();
		String ManagerName = employeeView.getManagerName();
		System.out.println(ManagerName);
		if (employeeView.getManagerName() != null) {
			manager = employeeRepository.findFirstByName(employeeView.getManagerName());
			if (manager == null)
				throw new EmployeeNotFoundException("Employee not found");
		}
		if (employeeView.getRoleName() != null) {
			role = roleRepository.findFirstByRoleName(employeeView.getRoleName());
			if (role == null)
				throw new RoleNotFoundException("role not found");
		}
		if (employeeView.getProjectName() != null) {
			project = projectRepository.findFirstByProjectName(employeeView.getProjectName());
			if (project == null)
				throw new ProjectNotFoundException("project not found");
		}
		Employee employee = new Employee(id, name, lead, manager, practiceHead, project, role);

		return employee;

	}

}
