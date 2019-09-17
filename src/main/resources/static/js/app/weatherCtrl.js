module.controller('WeatherCtrl', ['$scope', '$timeout', 'weatherService',
    function ($scope, $timeout, weatherService) {

    const ctrl = this;

    ctrl.forecastLoaded = false;
    ctrl.lang = "eng";
    ctrl.forecasts = null;
    ctrl.selectedForecast = null;
    ctrl.selectedLocation = 'Estonia';
    ctrl.selectedTime = 'Day';
    ctrl.locationOptions = [];
    ctrl.timeOptions = ['Day', 'Night'];
    ctrl.test = 'Cloudy with clear spells';

    ctrl.$onInit = () => {
        loadForecast(ctrl.lang);
    };

    ctrl.getTodaysForecast = () => {
        return ctrl.forecasts[0].day;
    };

    ctrl.setLocation = (newLocation) => {
        ctrl.selectedLocation = newLocation;
        updateSelectedForecast();
    };

    ctrl.setTime = (newTime) => {
        ctrl.selectedTime = newTime;
        updateSelectedForecast();
    };

    ctrl.isCity = () => {
        return ctrl.selectedLocation !== 'Estonia';
    };

    ctrl.getWeatherIcon = (forecast) => {
        const phenomenon = forecast[ctrl.selectedTime.toLowerCase()].phenomenon;

        if (isRaining(phenomenon)) {
            return 'lnr-drop';
        } else if (isCloudy(phenomenon)) {
            return 'lnr-cloud'
        } else if (ctrl.selectedTime === 'Night') {
            return 'lnr-moon'
        }
        return 'lnr-sun';
    };

    ctrl.clearError = () => {
        ctrl.errorMessage = null;
    };

    function isCloudy(sentence) {
        const keywords = ['cloudy'];

        return containsAnyKeyword(sentence, keywords);
    }

    function isRaining(sentence) {
        const keywords = ['shower', 'thunderstorm', 'rain'];

        return containsAnyKeyword(sentence, keywords);
    }

    function containsAnyKeyword(sentence, keywords) {
        return sentence.split(" ").some(word => {
            return keywords.includes(word.toLowerCase());
        });
    }

    function updateSelectedForecast() {
        ctrl.selectedForecast = getSelectedCityForecast();
        updateWeatherBackground();
    }

    function updateWeatherBackground() {
        const phenomenon = ctrl.selectedForecast.phenomenon;

        if(isRaining(phenomenon)) {
            ctrl.weatherType = 'rain';
        } else if(isCloudy(phenomenon)) {
            ctrl.weatherType = 'cloud';
        } else {
            ctrl.weatherType = '';
        }
    }

    function getSelectedCityForecast() {
        if(ctrl.isCity()) {
            return ctrl.todaysForecast[ctrl.selectedTime.toLowerCase()].places.filter(place => {
                return place.name === ctrl.selectedLocation;
            })[0];
        }

        return ctrl.todaysForecast[ctrl.selectedTime.toLowerCase()];
    }

    function loadForecast(lang) {
        let forecastPromise = weatherService.getForecast(lang);

        forecastPromise.then((forecast) => {
            ctrl.forecasts = forecast.data.forecasts;

            ctrl.todaysForecast = ctrl.forecasts[0];
            ctrl.locationOptions = ctrl.todaysForecast.day.places.map(place => {
                return place.name
            });
            ctrl.locationOptions.unshift('Estonia');

            let updatePromise = $timeout(updateSelectedForecast);

            updatePromise.then(() => {
                ctrl.forecastLoaded = true
            });
        }).catch(() => {
            ctrl.errorMessage = 'Failed to load weather data'
        });
    }
}]);