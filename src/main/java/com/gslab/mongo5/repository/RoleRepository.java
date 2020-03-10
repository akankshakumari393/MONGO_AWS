package com.gslab.mongo5.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gslab.mongo5.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	public Optional<Role> findByid(String id);

	public void deleteByid(String id);

	public Role findFirstByRoleName(String roleName);

}
