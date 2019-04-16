package com.gslab.mongo5.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gslab.mongo5.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	public Optional<Employee> findByid(String id);
	public void deleteByid(String id);
	public List<Employee> findByManager(String name);
	public Employee findFirstByid(String id);
	public Employee findFirstByName(String managerId);
}
