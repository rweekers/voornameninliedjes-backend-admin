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


    $(function() {
        // var token = $("meta[name='_csrf']").attr("content");
        // var header = $("meta[name='_csrf_header']").attr("content");
        var token = 'blabla';
        var header = '_csrf_header';
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    $.post("http://127.0.0.1:8080/voornaaminliedje/api/visit/add", {
        ipAddress: ipAddress,
        country: country,
        city: city,
        browser: browser,
        operatingSystem: operatingSystem
    }).done(function(data) {
        console.log("Post succesful " + data);
    })
        .fail(function(data) {
            console.log("Eror " + data.statusText);
        });

    // var token = $("meta[name='_csrf']").attr("content");
    // var header = $("meta[name='_csrf_header']").attr("content");

    // $http.defaults.headers.common['_csrf_header'] = token;

    /*
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
    }).success(function(data) {})
        .error(function(data) {});*/
}
