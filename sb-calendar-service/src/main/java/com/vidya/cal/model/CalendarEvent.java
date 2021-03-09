package com.vidya.cal.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class CalendarEvent {

	private String id;
	private String calendarId;
	private EventType type;
	private String category;
	private String title;
	private String body;

	@JsonProperty("isAllday")
	private boolean isAllday;

	private String start;
	private String end;

	@JsonProperty("isVisible")
	private boolean isVisible;

	private String location;
	private List<Person> attendees;
	private Person createdBy;

	public CalendarEvent(String id, String calendarId, EventType type, String category, String title, String body,
			boolean isAllday, String start, String end, boolean isVisible, String location, List<Person> attendees,
			Person createdBy) {
		super();
		this.id = id;
		this.calendarId = calendarId;
		this.type = type;
		this.category = category;
		this.title = title;
		this.body = body;
		this.isAllday = isAllday;
		this.start = start;
		this.end = end;
		this.isVisible = isVisible;
		this.location = location;
		this.attendees = attendees;
		this.createdBy = createdBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CalendarEvent))
			return false;
		CalendarEvent other = (CalendarEvent) obj;
		return Objects.equals(id, other.id) && Objects.equals(title, other.title) && Objects.equals(type, other.type);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isAllday() {
		return isAllday;
	}

	public void setAllday(boolean isAllday) {
		this.isAllday = isAllday;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Person> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Person> attendees) {
		this.attendees = attendees;
	}

	public Person getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CalendarEvent [id=");
		builder.append(id);
		builder.append(", calendarId=");
		builder.append(calendarId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", category=");
		builder.append(category);
		builder.append(", title=");
		builder.append(title);
		builder.append(", body=");
		builder.append(body);
		builder.append(", isAllday=");
		builder.append(isAllday);
		builder.append(", start=");
		builder.append(start);
		builder.append(", end=");
		builder.append(end);
		builder.append(", isVisible=");
		builder.append(isVisible);
		builder.append(", location=");
		builder.append(location);
		builder.append(", attendees=");
		builder.append(attendees);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append("]");
		return builder.toString();
	}
}