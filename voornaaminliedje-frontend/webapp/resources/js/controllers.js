'use strict';

/* Controllers */

var songControllers = angular.module('songControllers', []);

songControllers.controller('VisitCtrl', ['$scope', '$http', 'GeoIP',
    function($scope, $http, GeoIP) {

        var location = GeoIP.query();

        location.$promise.then(function(data) {
            store($http, data.ip, data.country_name, data.city);
        });
    }
]);

songControllers.controller('SongOfTheDayCtrl', ['$scope', 'SongOfTheDay',
    function($scope, SongOfTheDay) {

        $scope.songOfTheDay = SongOfTheDay.query();
        // songOTD = $scope.songOfTheDay;
    }
]);

function store($http, ipAddress, country, city) {
    var browser = BrowserDetect.browser + BrowserDetect.version;
    var operatingSystem = BrowserDetect.OS;

    console.log("Useragent " + navigator.userAgent);

    $http({
        url: 'http://127.0.0.1:8180/voornaaminliedje/api/visit/add',
        method: "POST",
        params: {
            ipAddress: ipAddress,
            userAgent: navigator.userAgent
        }
    }).success(function(data) {})
        .error(function(data) {});

    /*
    $http({
        url: 'http://127.0.0.1:8180/voornaaminliedje/api/visit/add',
        method: "POST",
        params: {
            ipAddress: ipAddress,
            country: country,
            city: city,
            browser: browser,
            operatingSystem: operatingSystem
        }
    }).success(function(data) {})
        .error(function(data) {});
        */
}
