package com.example.weatherapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Weather {

    private String description;
    private BigDecimal temperature;
    private String country;

}
