package ee.iot.testtask.weather.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class WeatherApiRestClient {

    private final RestTemplate restTemplate;
    private final String weatherUrl = "http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php";
    private final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                                     "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36";

    public ResponseEntity<String> getForecast(String lang) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", userAgent);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                                                              .queryParam("lang", lang);

        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, String.class);
    }
}
