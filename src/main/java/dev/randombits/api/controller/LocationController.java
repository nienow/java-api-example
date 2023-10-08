package dev.randombits.api.controller;

import dev.randombits.api.model.Location;
import dev.randombits.api.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @QueryMapping
    public List<Location> locations() {
        return this.locationService.getLocations();
    }

    @MutationMapping
    public Location createLocation(@Argument String name) {
        return this.locationService.createLocation(name);
    }
}
