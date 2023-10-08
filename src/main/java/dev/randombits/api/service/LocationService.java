package dev.randombits.api.service;

import dev.randombits.api.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class LocationService {

    private final List<Location> allLocations = new ArrayList<>();

    public Location createLocation(String name) {
        Location location = new Location();
        location.setId(UUID.randomUUID().toString());
        location.setName(name);
        this.allLocations.add(location);
        return location;
    }

    public List<Location> getLocations() {
        return this.allLocations;
    }

    public Location getLocation(String id) {
        return this.allLocations.stream().filter(loc -> loc.getId().equals(id)).findFirst().get();
    }
}
