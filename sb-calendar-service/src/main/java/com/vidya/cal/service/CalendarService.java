package com.vidya.cal.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jeasy.random.EasyRandom;
import org.springframework.stereotype.Service;

import com.vidya.cal.model.CalendarEvent;
import com.vidya.cal.model.EventType;
import com.vidya.cal.model.Person;

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
	
	
	public List<CalendarEvent> findEventsByDateRangeFromMultipleSource(Instant dateStart, Instant dateEnd, String type,
			boolean canceled) throws InterruptedException, ExecutionException {

		CalendarEvent cal1 = new CalendarEvent("1", "calendarId1", EventType.DEMO, "time", "title1", "body1",
				false, dateStart.toString(), dateEnd.toString(), true, "location1", null,
				Person.builder().name("Vidya").build());
		
		CalendarEvent cal2 = new CalendarEvent("2", "calendarId2", EventType.BIRTHDAY, "time", "title2", "body2",
				false, dateStart.toString(), dateEnd.toString(), true, "location2", null,
				Person.builder().name("Ram").build());

		CompletableFuture<CalendarEvent> future1  
		  = CompletableFuture.supplyAsync(() -> cal1);
		
		CompletableFuture<CalendarEvent> future2  
		  = CompletableFuture.supplyAsync(() -> cal2);
		
		CompletableFuture<CalendarEvent> future3  
		  = CompletableFuture.supplyAsync(() -> null);

		//CompletableFuture<Void> combinedFuture 
		//  = CompletableFuture.allOf(future1, future2, future3);

		//combinedFuture.get();
		
		
		List<CalendarEvent> entries = Stream.of(future1, future2, future3)
				  .map(CompletableFuture::join)
				  .collect(Collectors.toList());
		
		entries = entries.stream().filter(Objects::nonNull).collect(Collectors.toList());
		
		return entries;
	}
	
	
}