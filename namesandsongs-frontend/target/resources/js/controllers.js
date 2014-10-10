'use strict';

/* Controllers */

var songControllers = angular.module('songControllers', []);

songControllers.controller('VisitCtrl', ['$scope', '$http', 'GeoIP', 'Visit',
    function($scope, $http, GeoIP, Visit) {

        var location = GeoIP.query();

        location.$promise.then(function(data) {
            store($http, data.ip, data.country_name, data.city);
        });
    }
]);

function store($http, ipAddress, country, city) {
    var browser = BrowserDetect.browser + BrowserDetect.version;
    var operatingSystem = BrowserDetect.OS;

    $http({
        url: 'http://127.0.0.1:8080/voornaaminliedje/api/visit/add',
        method: "POST",
        params: {
            ipAddress: ipAddress,
            country: country,
            city: city,
            browser: browser,
            operatingSystem: operatingSystem
        }
    }).success(function(data) {
    })
        .error(function(data) {
        });
}
