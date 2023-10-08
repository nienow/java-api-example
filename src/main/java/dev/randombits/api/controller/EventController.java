package dev.randombits.api.controller;

import dev.randombits.api.model.Event;
import dev.randombits.api.model.Location;
import dev.randombits.api.service.EventService;
import dev.randombits.api.service.LocationService;
import dev.randombits.api.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EventController {

    private EventService eventService;
    private LocationService locationService;
    private OrgService orgService;

    @Autowired
    public EventController(EventService eventService, LocationService locationService, OrgService orgService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.orgService = orgService;
    }

    @QueryMapping
    public List<Event> events() {
        return this.eventService.getEvents();
    }

    @MutationMapping
    public Event createEvent(@Argument String name, @Argument String locationId, @Argument LocalDateTime start, @Argument LocalDateTime end) {
        return this.eventService.createEvent(name, locationId, start, end);
    }

    @SchemaMapping(typeName = "Event", field = "location")
    public Location getLocation(Event event) {
        return this.locationService.getLocation(event.getLocationId());
    }

//    @SchemaMapping(typeName = "Event", field = "org")
//    public Org getOrg(Event event) {
//        return this.orgService.getOrg(event.getOrgId());
//    }
}
