package com.example.weatherapi.Service;

import com.example.weatherapi.Model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class WeatherService {
    //url:
    private static final String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";

    @Value("${apiKey}")
    private String apikey;
    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    public WeatherService(ObjectMapper objectMapper, RestTemplateBuilder restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate.build();
    }


    //Response entity:
    //Rest template configure:

//    ObjectMapper => functinalities -> jasckson it helps in reading and wrritng json. -> Bean(POJO) JSonNOde

    //second

    public Weather getCurrentWeather(String city) {
        URI uri = null;
        uri = new UriTemplate(weatherUrl).expand(city, apikey);
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        System.out.println(response);
        return getWeather(response);

    }

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
