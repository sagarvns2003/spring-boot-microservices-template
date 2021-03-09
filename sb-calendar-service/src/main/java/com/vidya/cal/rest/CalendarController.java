package com.vidya.cal.rest;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.vidya.cal.model.CalendarEvent;
import com.vidya.cal.service.CalendarService;

@RestController
@RequestMapping("/v1/calendar")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;
	
	@GetMapping(value = "/entry", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CalendarEvent> findEventsByDateRange(@RequestParam(required = true) String dateStart,
			@RequestParam(required = true) String dateEnd, @RequestParam(required = false) String type,
			@RequestParam(required = false) boolean canceled) {

		List<CalendarEvent> entries = calendarService.findEventsByDateRange(Instant.parse(dateStart), Instant.parse(dateEnd), type, canceled);
		
		return entries; 
	}

	@PostMapping(value = "/entry")
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody CalendarEvent event) {
		Preconditions.checkNotNull(event);

		// TODO create events in db

		return 0l;
	}

	/*
	 * @PutMapping(value = "/{id}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public void update(@PathVariable("id") Long
	 * id, @RequestBody Foo resource) { Preconditions.checkNotNull(resource);
	 * RestPreconditions.checkNotNull(service.getById(resource.getId()));
	 * service.update(resource); }
	 * 
	 * @DeleteMapping(value = "/{id}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public void delete(@PathVariable("id") Long
	 * id) { service.deleteById(id); }
	 */
}
