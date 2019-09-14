package ee.ot.testtask.weather.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ForecastsDto {
    private List<ForecastDto> forecasts;

    private static class ForecastDto {
        private String date;
        private NightDto night;
        private DayDto day;
    }

    private static class NightDto {
        private String phenomenon;
        private String tempmin;
        private String tempmax;
        private String text;
        private List<PlaceDto> place;
        private WindDto wind;
        private String sea;
        private String peipsi;

    }

    private static class DayDto {

    }

    private static class PlaceDto {
        private String name;
        private String phenomenon;
        private String tempmin;
    }

    private static class WindDto {
        private String name;
        private String direction;
        private String speedmin;
        private String speedmax;
    }
}
