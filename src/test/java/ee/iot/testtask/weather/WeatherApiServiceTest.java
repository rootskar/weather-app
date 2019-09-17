package ee.iot.testtask.weather;

import ee.iot.testtask.weather.rest.WeatherApiRestClient;
import ee.iot.testtask.weather.rest.domain.ForecastDto;
import ee.iot.testtask.weather.service.WeatherApiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class WeatherApiServiceTest {
    private static final String SAMPLE_LANG = "eng";
    private static final ForecastDto SAMPLE_FORECAST = new ForecastDto(null);
    private static final String SAMPLE_RESPONSE_BODY = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?>\n" +
                                                       "<forecasts>\n" +
                                                       "</forecasts>";
    private static final ResponseEntity<String> SAMPLE_RESPONSE = ResponseEntity.of(Optional.of(SAMPLE_RESPONSE_BODY));
    private static final ResponseEntity<String> SAMPLE_EMPTY_RESPONSE = ResponseEntity.of(Optional.of(""));

    @Mock
    private WeatherApiRestClient weatherApiRestClient;

    private WeatherApiService weatherApiService;

    @Before
    public void setUp() {
        weatherApiService = new WeatherApiService(weatherApiRestClient);
    }

    @Test
    public void givenValidLangAttribute_whenRequestingForecast_thenForecastReturned() {
        doReturn(SAMPLE_RESPONSE).when(weatherApiRestClient)
                                 .getForecast(SAMPLE_LANG);

        var response = weatherApiService.getForecast(SAMPLE_LANG);

        assertEquals(SAMPLE_FORECAST, response);
    }

    @Test
    public void givenInvalidLangAttribute_whenRequestingForecast_thenNullReturned() {
        doReturn(SAMPLE_EMPTY_RESPONSE).when(weatherApiRestClient)
                                  .getForecast(SAMPLE_LANG);

        var response = weatherApiService.getForecast(SAMPLE_LANG);
        assertNull(response);
    }

}
