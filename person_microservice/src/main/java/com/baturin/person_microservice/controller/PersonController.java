package com.baturin.person_microservice.controller;
import com.baturin.person_microservice.models.Location;
import com.baturin.person_microservice.models.Person;
import com.baturin.person_microservice.models.Weather;
import com.baturin.person_microservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personrepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/persons")
    public Iterable<Person> findAllPersons() {
        return personrepository.findAll();
    }

    @GetMapping("/persons/{id}/getWeather")
    public Weather getWeather(@PathVariable int id) {
        Optional<Person> optional_person = personrepository.findById(id);
        if (optional_person.isPresent()) {
            Person person = optional_person.get();
            Location location =restTemplate.getForObject("http://localhost:8081/locations/"+person.getCity_name(), Location.class);
            person.setLocation(location);
            Weather weather = new Weather();
            if(location!=null) {
                weather = restTemplate.getForObject("http://localhost:8082/weathers/lon/" + person.getLocation().getLon() + "/lat/" + person.getLocation().getLat(), Weather.class);
            }
            person.setWeather(weather);
            return weather;
        }
        return new Weather();
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        if(personrepository.findById(person.getId()).isPresent())
        return new ResponseEntity(personrepository.findById(person.getId()), HttpStatus.BAD_REQUEST);
        else return new ResponseEntity(personrepository.save(person), HttpStatus.CREATED);
    }

}