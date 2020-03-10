package com.gslab.mongo5.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gslab.mongo5.model.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

	public Optional<Project> findByid(String id);

	public void deleteByid(String id);

	/// @Query("{ 'projectName': ?0}")
	public Project findFirstByProjectName(String projectName);
}
