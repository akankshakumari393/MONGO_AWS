package com.gslab.mongo5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gslab.mongo5.exception.RoleNotFoundException;
import com.gslab.mongo5.model.Employee;
import com.gslab.mongo5.model.Role;
import com.gslab.mongo5.repository.EmployeeRepository;
import com.gslab.mongo5.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	public List<Role> getAllRole() {

		return (List<Role>) roleRepository.findAll();

	}

	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	public Role getRole(String id) {
		// System.out.println("finding by id "+ string);
		return roleRepository.findByid(id).orElseThrow(() -> new RoleNotFoundException(id));
	}

	public Role updateRole(Role newRole, String id) {

		Role updatedRole = roleRepository.findByid(id).map(role -> {
			role.setRoleName(newRole.getRoleName());
			return roleRepository.save(role);
		}).orElseThrow(() -> new RoleNotFoundException(id));

		return updatedRole;
	}

	public void deleteRole(String id) {
		// TODO Auto-generated method stub
		roleRepository.deleteByid(id);
	}

	public List<Employee> getEmployeesByRoleId(String id) {
		// System.out.print("fetching project by IDS");
		roleRepository.findByid(id).orElseThrow(() -> new RoleNotFoundException(id));
		List<Employee> allEmployees = employeeRepository.findAll();
		// System.out.print("Got all IDS");
		allEmployees.removeIf(employee -> employee.getProject() == null || (!employee.getProject().getId().equals(id)));
		// System.out.print("removed unnecessary ids");
		return allEmployees;
	}
}
