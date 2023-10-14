package com.baturin.location_microservice.controller;
import com.baturin.location_microservice.model.Geodata;
import com.baturin.location_microservice.model.Weather;
import com.baturin.location_microservice.repository.GeodataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class WeatherController {

    @Autowired
    private GeodataRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/weather")
    public  ResponseEntity<Weather> redirectRequestWeather(@RequestParam String location) {
        Optional<Geodata> geodata_optional = repository.findByName(location);
        if(geodata_optional.isPresent()) {
            Geodata geodata=geodata_optional.get();
            String url = String.format("http://localhost:8082/?lat=%s&lon=%s", geodata.getLat(), geodata.getLon());
            Weather weather = restTemplate.getForObject(url, Weather.class);
            return new ResponseEntity<>(weather,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping
    public Optional<Geodata> getWeather(@RequestParam String location) {
        return repository.findByName(location);
    }

    @PostMapping
    public Geodata save(@RequestBody Geodata geodata) {
        return repository.save(geodata);
    }

}