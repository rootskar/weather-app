package ee.iot.testtask.weather.rest.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties
@JacksonXmlRootElement(localName = "forecasts")
public class ForecastDto {

    @JacksonXmlProperty(localName = "forecast")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Forecast[] forecasts;

    @Value
    private static class Forecast {
        private String date;
        private Info day;
        private Info night;
    }

    @Value
    private static class Info {
        private String phenomenon;
        private String tempmin;
        private String tempmax;
        private String text;
        @JacksonXmlProperty(localName = "place")
        @JacksonXmlElementWrapper(useWrapping = false)
        private Place[] places;
        private Wind wind;
        private String sea;
        private String peipsi;
    }

    @Value
    private static class Place {
        private String name;
        private String phenomenon;
        @JsonAlias({"tempmin", "tempmax"})
        private String temp;
    }

    @Value
    private static class Wind {
        private String name;
        private String direction;
        private String speedmin;
        private String speedmax;
        private String gust;
    }
}
