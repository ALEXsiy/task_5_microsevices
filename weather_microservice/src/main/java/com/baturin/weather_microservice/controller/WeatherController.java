package com.baturin.weather_microservice.controller;


import com.baturin.weather_microservice.model.Weather;
import jakarta.persistence.GeneratedValue;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/weathers")
public class WeatherController {

    @GetMapping("/lon/{lon}/lat/{lat}")
    public Weather addWeatherToLocation(@PathVariable double lon, @PathVariable double lat) throws IOException {

            Weather weather=new Weather();
            final String key="ad294eadb905c3a3bb8b29a2b3cc4512";
            String url = "https://api.openweathermap.org/data/2.5/weather?lat="+Double.toString(lat)+"&lon="+Double.toString(lon)+"&appid=" + key;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject weatherJSON = new JSONObject(response.toString());
            weather.setTemp(weatherJSON.getJSONObject("main").getDouble("temp"));
            weather.setFeels_like(weatherJSON.getJSONObject("main").getDouble("feels_like"));
            weather.setTemp_min(weatherJSON.getJSONObject("main").getDouble("temp_min"));
            weather.setTemp_max(weatherJSON.getJSONObject("main").getDouble("temp_max"));
            weather.setPressure(weatherJSON.getJSONObject("main").getDouble("pressure"));
            weather.setHumidity( weatherJSON.getJSONObject("main").getDouble("humidity"));
            return  weather;

    }
}
