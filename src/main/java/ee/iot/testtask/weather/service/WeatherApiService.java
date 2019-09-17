package ee.iot.testtask.weather.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ee.iot.testtask.weather.rest.WeatherApiRestClient;
import ee.iot.testtask.weather.rest.domain.ForecastDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class WeatherApiService {

    private final WeatherApiRestClient weatherApiRestClient;

    public ForecastDto getForecast(String lang) {
        var mapper = new XmlMapper();
        var response = weatherApiRestClient.getForecast(lang);
        ForecastDto forecast = null;

        try {
            forecast = mapper.readValue(response.getBody(), ForecastDto.class);
        } catch (IOException e) {
            log.warn("Unable to convert response body", e);
        }

        return forecast;
    }
}
