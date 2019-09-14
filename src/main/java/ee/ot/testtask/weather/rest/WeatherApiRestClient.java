package ee.ot.testtask.weather.rest;

import ee.ot.testtask.weather.rest.domain.ForecastsDto;
import org.springframework.web.client.RestTemplate;

public class WeatherApiRestClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public ForecastsDto getForecast(String lang) {
        return restTemplate.getForObject("http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang={lang}",
                                            ForecastsDto.class, lang);
    }
}
