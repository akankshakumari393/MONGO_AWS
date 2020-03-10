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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.mongo5.assembler.HolidayResourceAssembler;
import com.gslab.mongo5.model.Holiday;
import com.gslab.mongo5.service.HolidayService;

@RestController
public class HolidayController {

	@Autowired
	HolidayService holidayService;

	@Autowired
	HolidayResourceAssembler holidayAssembler;

	@GetMapping("/holidays")
	public Resources<Resource<Holiday>> getAllHoliday() {
		// System.out.println("getting all holiday");
		List<Holiday> holiday = holidayService.getAllHoliday();

		List<Resource<Holiday>> holidays = holiday.stream().map(holidayAssembler::toResource)
				.collect(Collectors.toList());
		// System.out.println("Going to get all holiday");
		return new Resources<>(holidays, linkTo(methodOn(HolidayController.class).getAllHoliday()).withSelfRel());
	}

	@PostMapping("/holidays")
	public ResponseEntity<?> newHoliday(@RequestBody Holiday holiday) throws URISyntaxException {

		Holiday savedHoliday = holidayService.addHoliday(holiday);
		// holiday saved
		System.out.println("holiady saved");
		Resource<Holiday> resource = holidayAssembler.toResource(savedHoliday);
		System.out.println("holiady resourced");
		System.out.println(resource.toString());
		System.out.println("  " + resource.getId());
		return ResponseEntity.created(new URI(resource.getId().expand(savedHoliday.getDate()).getHref()))
				.body(resource);
	}

	@GetMapping("/holidays/{year}")
	public Resources<Resource<Holiday>> getHolidaysByYear(@PathVariable String year) {
		List<Holiday> holiday = holidayService.getHolidaysByYear(year);

		List<Resource<Holiday>> holidays = holiday.stream().map(holidayAssembler::toResource)
				.collect(Collectors.toList());
		// System.out.println("Going to get all holiday");
		return new Resources<>(holidays, linkTo(methodOn(HolidayController.class).getAllHoliday()).withSelfRel());
	}

	//
	@GetMapping("/holidays/{year}/{MM}")
	public Resources<Resource<Holiday>> getHolidaysByYearMonth(@PathVariable String year, @PathVariable String MM) {
		// System.out.println("getting all holiday");
		List<Holiday> holiday = holidayService.getHolidaysByYearMonth(year, MM);

		List<Resource<Holiday>> holidays = holiday.stream().map(holidayAssembler::toResource)
				.collect(Collectors.toList());
		// System.out.println("Going to get all holiday");
		return new Resources<>(holidays, linkTo(methodOn(HolidayController.class).getAllHoliday()).withSelfRel());
	}

	@DeleteMapping("/holidays/{date}")
	public ResponseEntity<?> deleteHoliday(@PathVariable String date) {
		holidayService.deleteHoliday(date);
		return ResponseEntity.noContent().build();
	}

	/*
	 * @PutMapping("/holidays/{date}") ResponseEntity<?> replaceHoliday(@RequestBody
	 * Holiday newHoliday, @PathVariable String date) throws URISyntaxException {
	 * 
	 * Holiday updatedHoliday = holidayService.updateHoliday(newHoliday, date);
	 * 
	 * Resource<Holiday> resource = holidayAssembler.toResource(updatedHoliday);
	 * 
	 * return ResponseEntity .created(new
	 * URI(resource.getId().expand(updatedHoliday.getDate()).getHref()))
	 * .body(resource); }
	 */

}
