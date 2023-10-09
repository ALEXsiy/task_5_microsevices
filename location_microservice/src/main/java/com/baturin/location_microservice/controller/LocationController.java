package com.baturin.location_microservice.controller;
import com.baturin.location_microservice.model.Location;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
@RestController
public class LocationController {

    List<Location> locations = Arrays.asList(new Location(1, 45.1749,54.1838,"Saransk"));

    @GetMapping("/locations/{city_name}")
    public Location getLocation(@PathVariable String city_name) {
        Location location = new Location().findByCityName(city_name,locations);
        return location;
    }

}
