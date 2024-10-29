package com.hanul.gwangs.service.impl;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class WeatherService {
	
	@Value("${openweather.api.url}")
    private String apiUrl;
	
	public Map<String, String> getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        JSONObject jsonObject = new JSONObject(response);
        String temp = jsonObject.getJSONObject("main").get("temp").toString();
        String icon = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");

        String iconUrl = "http://openweathermap.org/img/wn/" + icon + "@2x.png";

        return Map.of("temp", temp, "iconUrl", iconUrl);
    }
}
