package com.baturin.location_microservice.controller;
import com.baturin.location_microservice.repository.LocationRepository;
import com.baturin.location_microservice.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class LocationController {
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/getLocation")
    public Location getLocation(@RequestParam String name) {
        Optional<Location> optional_location =locationRepository.findByName(name);
        Location location =optional_location.get();
        return location;

    }
    @PostMapping("/saveLocation")
    public ResponseEntity<Location> saveLocation(@RequestBody Location location) {

        if(locationRepository.findByName(location.getName()).isPresent())
            return new ResponseEntity(locationRepository.findByName(location.getName()), HttpStatus.OK);
        else {
            //locationRepository.save(location);
            return new ResponseEntity(locationRepository.save(location), HttpStatus.CREATED);
        }
        }
    }





