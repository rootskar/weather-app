module.service('weatherService', ['$http', function ($http) {
    const svc = this;

    svc.getForecast = (lang) => {
        return $http.get('forecast', {
            params: {
                lang: lang
            }
        });
    };

}]);