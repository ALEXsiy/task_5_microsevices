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
   // java -Dserver.port=8088 -Dspring.datasource.url=jdbc:h2:file:./data/locationsssdb -jar location_microservice\target\location_microservice-0.0.1-SNAPSHOT.jar


    @GetMapping("/weather")
    public  ResponseEntity<Weather> redirectRequestWeather(@RequestParam String location) {
        Optional<Geodata> geodata_optional = repository.findByName(location);
        if(geodata_optional.isPresent()) {
            Geodata geodata=geodata_optional.get();
            String url = String.format("http://weather-info-service/?lat=%s&lon=%s", geodata.getLat(), geodata.getLon());
            Weather weather = restTemplate.getForObject(url, Weather.class);
            return new ResponseEntity<>(weather,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping
    public Optional<Geodata> getWeather(@RequestParam String location) {
        return repository.findByName(location);
    }

    //Проверка на уникальность значений при добавлении
    @PostMapping
    public ResponseEntity<Geodata> save(@RequestBody Geodata geodata) {
        Optional<Geodata> geodata_optional = repository.findByName(geodata.getName());
        if(geodata_optional.isPresent()) {
            return new ResponseEntity<>(geodata_optional.get(),HttpStatus.OK);
        }
        else return new ResponseEntity<>(repository.save(geodata),HttpStatus.CREATED);
    }

}