package com.example.weatherapi.Service;

import com.example.weatherapi.Model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WeatherService {
    //url:
    private static final String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?q={}&appid={}";

    private ObjectMapper objectMapper;

    //Response entity:
    //Rest template configure:

//    ObjectMapper => functinalities -> jasckson it helps in reading and wrritng json. -> Bean(POJO) JSonNOde

    //second

    public Weather getWeather(ResponseEntity<String> response) {

        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return new Weather(jsonNode.path("weather").get(0).path("description").asText(),
                    BigDecimal.valueOf(jsonNode.path("main").path("temp").asDouble()),
                    jsonNode.path("sys").path("country").asText()
            );

        } catch (JsonProcessingException e) {
            throw new RuntimeException("error json parsing:");
        }


    }
}
