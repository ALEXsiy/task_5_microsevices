package com.baturin.weather_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication
@EnableCaching
public class WeatherMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeatherMicroserviceApplication.class, args);
	}

}
