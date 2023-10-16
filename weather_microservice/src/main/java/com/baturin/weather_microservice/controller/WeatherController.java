package com.baturin.weather_microservice.controller;
import com.baturin.weather_microservice.model.Main;
import com.baturin.weather_microservice.model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
public class WeatherController {

        @Autowired
        private RestTemplate restTemplate;
        @Value("${appid}")
        private String appId;
        @Value("${url.weather}")
        private String urlWeather;

        @GetMapping
       @Cacheable(value = "cacheForWeathers",key = "#lat+':'+#lon")
        public Main getWeather(@RequestParam String lat, @RequestParam String lon) {
                String request = String.format("%s?lat=%s&lon=%s&units=metric&appid=%s", urlWeather, lat, lon, appId);
                return restTemplate.getForObject(request, Root.class).getMain();
        }
}


