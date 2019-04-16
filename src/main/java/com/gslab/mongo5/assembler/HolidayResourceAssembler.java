package com.gslab.mongo5.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.gslab.mongo5.controller.HolidayController;
import com.gslab.mongo5.model.Holiday;


@Component
public class HolidayResourceAssembler implements ResourceAssembler<Holiday,Resource<Holiday>> {

	@Override
	public Resource<Holiday> toResource(Holiday entity) {
		// TODO Auto-generated method stub
		System.out.println("holiady resourced in entitty");
		return new Resource<>(entity,
				linkTo(methodOn(HolidayController.class).getAllHoliday()).withRel("holidays"),
				linkTo(methodOn(HolidayController.class).deleteHoliday(entity.getDate())).withSelfRel()
				);
	}

}
