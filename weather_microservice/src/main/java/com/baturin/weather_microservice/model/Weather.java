package com.baturin.weather_microservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private int id;
    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double feels_like;
    private double humidity;
}
