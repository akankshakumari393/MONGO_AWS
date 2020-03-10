package com.gslab.mongo5.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.gslab.mongo5.controller.ProjectController;
import com.gslab.mongo5.model.Project;

@Component
public class ProjectResourceAssembler implements ResourceAssembler<Project, Resource<Project>> {

	@Override
	public Resource<Project> toResource(Project entity) {
		// TODO Auto-generated method stub

		return new Resource<>(entity, linkTo(methodOn(ProjectController.class).getAllProject()).withRel("projects"),
				linkTo(methodOn(ProjectController.class).getProject(entity.getId())).withSelfRel());
	}

}
