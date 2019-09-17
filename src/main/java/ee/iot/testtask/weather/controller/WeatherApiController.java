package ee.iot.testtask.weather.controller;

import ee.iot.testtask.weather.rest.domain.ForecastDto;
import ee.iot.testtask.weather.service.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class WeatherApiController {

    private final WeatherApiService weatherApiService;

    @RequestMapping(value = "forecast", method = GET)
    @ResponseBody
    private ForecastDto getForecast(String lang) {

        return weatherApiService.getForecast(lang);
    }
}

