package dev.randombits.api.service;

import dev.randombits.api.model.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class EventService {

    private List<Event> allEvents = new ArrayList<>();

    public Event createEvent(String name, String locationId, LocalDateTime start, LocalDateTime end) {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setName(name);
        event.setLocationId(locationId);
        event.setStart(start);
        event.setEnd(end);
        this.allEvents.add(event);
        return event;
    }

    public List<Event> getEvents() {
        return this.allEvents;
    }
}
