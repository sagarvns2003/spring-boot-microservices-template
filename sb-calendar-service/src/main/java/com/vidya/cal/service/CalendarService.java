package com.vidya.cal.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.springframework.stereotype.Service;

import com.vidya.cal.model.CalendarEvent;

@Service
public class CalendarService {

	static int counter = 0;

	public List<CalendarEvent> findEventsByDateRange(Instant dateStart, Instant dateEnd, String type,
			boolean canceled) {

		EasyRandom generator = new EasyRandom();
		List<CalendarEvent> entries = generator.objects(CalendarEvent.class, 10)
				.map(ce -> changeValues(ce, dateStart, dateEnd)).collect(Collectors.toList());
		counter = 0;

		// TODO get events from db
		// CalendarEvent cal = new CalendarEvent("1", "", EventType.DEMO, null, "test
		// entry", null, false, null, null, false,
		// null, null, null);

		return entries;
	}

	private CalendarEvent changeValues(CalendarEvent ce, Instant dateStart, Instant dateEnd) {
		ce.setId(String.valueOf(counter));
		ce.setAllday(false);
		ce.setVisible(true);  //default true
		ce.setTitle(ce.getType() + "-" + ce.getTitle());
		ce.setCategory("time");

		if (counter / 2 == 0) {
			ce.setStart(dateStart.plus(Duration.ofHours(counter)).toString());
			ce.setEnd(dateEnd.toString());
		} else {
			ce.setStart(dateStart.toString());
			ce.setEnd(dateEnd.minus(Duration.ofHours(counter)).toString());
		}
		counter++;

		return ce;
	}
}