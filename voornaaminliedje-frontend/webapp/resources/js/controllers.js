'use strict';

/* Controllers */

var songControllers = angular.module('songControllers', []);
var ipAddress;
var country;
var city;
var browser;
var operatingSystem;
var params;

songControllers.controller('VisitCtrl', ['$scope', '$http', 'Visit',
    function($scope, $http, Visit) {

        var bla = {
            country: '',
            city: ''
        };

        log(bla);s

        console.log("Country is " + bla.country);

        var geoipUrl = "http://api.hostip.info/get_json.php";

        /*
        $http.get(geoipUrl).success(function(data) {
            $scope.bla = data;
        });*/

        // console.log("params " + bla.country);


        $http({
            url: 'http://127.0.0.1:8080/voornaaminliedje/api/visit/add',
            method: "POST",
            params: {
                // ipAddress: '1.1.1.1',
                ipAddress: ipAddress,
                country: 'NL',
                city: 'Test',
                browser: 'Chrome',
                operatingSystem: '7'
            }
        }).success(function(data) {
            console.log("Post succesful");
            console.log("ipadres is " + $scope.bla);
        })
            .error(function(data) {
                console.log("Post not working");
            });
        /*
        $http.post('http://127.0.0.1:8080/voornaaminliedje/api/visit/add', data).success(successCallback).error(errorCallback);
        /*.success(function() {
            console.log("Goed...");
        }).error(function() {
            console.log("Jammer...");
        });*/

        /*
        Visit.create({
            ipAddress: '1.1.1.1',
            browser: 'Chrome',
            operatingSystem: '7',
            city: 'bla',
            country: 'NED'
        });*/
    }
]);

function log(bla) {

    var geoipUrl = "http://api.hostip.info/get_json.php";

    $.getJSON(geoipUrl, function(data) {
        console.log("ip adres " + data.ip);
        this.ipAddress = data.ip;
        browser = BrowserDetect.browser + BrowserDetect.version;
        operatingSystem = BrowserDetect.OS;
        city = data.city;
        country = data.country_name;
        // storeVisit();
    }).success(function(data) {
        console.log(data.country_name);
        // bla.ipAddress = data.ip;
        // bla.browser = BrowserDetect.browser + BrowserDetect.version;
        // bla.operatingSystem = BrowserDetect.OS;
        bla.city = data.city;
        bla.country = data.country_name;
    })
        .fail(function(error) {
            console.log(error);
        });

    // console.log("/////" + data.country);
}
