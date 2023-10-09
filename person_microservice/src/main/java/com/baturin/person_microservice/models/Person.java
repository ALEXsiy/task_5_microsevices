package com.baturin.person_microservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String first_name;
    private String second_name;
    private String city_name;
    @OneToOne
    @JsonIgnore
    private Location location;
    @OneToOne
    @JsonIgnore
    private Weather weather;




}