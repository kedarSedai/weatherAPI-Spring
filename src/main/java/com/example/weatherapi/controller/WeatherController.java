package com.example.weatherapi.controller;

import com.example.weatherapi.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String getWeather(Model model) {
       model.addAttribute("weather", weatherService.getCurrentWeather("kathmandu"));
        return "index";
    }

}
