package com.baturin.location_microservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    int id;
    private double lon;
    private double lat;
    private String city_name;
   public Location findByCityName(String city_name, List<Location> listlocations) {
        for(Location location : listlocations) {
            if(location.getCity_name().equals(city_name)) {
                return location;
            }
        }
        return null;
    }
}