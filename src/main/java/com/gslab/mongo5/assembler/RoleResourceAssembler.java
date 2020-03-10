package com.gslab.mongo5.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.gslab.mongo5.controller.RoleController;
import com.gslab.mongo5.model.Role;

@Component
public class RoleResourceAssembler implements ResourceAssembler<Role, Resource<Role>> {

	@Override
	public Resource<Role> toResource(Role entity) {
		// TODO Auto-generated method stub

		return new Resource<>(entity, linkTo(methodOn(RoleController.class).getAllRole()).withRel("roles"),
				linkTo(methodOn(RoleController.class).getRole(entity.getId())).withSelfRel());
	}

}
