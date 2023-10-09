package com.baturin.person_microservice.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    @Id @GeneratedValue
    @JsonIgnore
    private int id;
    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double feels_like;
    private double humidity;
}
