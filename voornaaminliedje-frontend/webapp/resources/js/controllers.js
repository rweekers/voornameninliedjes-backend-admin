'use strict';

/* Controllers */

var songControllers = angular.module('songControllers', []);

songControllers.controller('VisitCtrl', ['$scope', '$http', 'Visit',
    function($scope, $http, Visit) {

        var params = {
            country: 'NL',
            ipAddres: '1.1.1.1'
        };
        /*
        params = {
            country: 'NL',
            ipAddres: '1.1.1.1'
        };*/

        $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

        $http({
            url: 'http://127.0.0.1:8080/voornaaminliedje/api/visit/add',
            method: "POST",
            data: params
        }).success(function(data) {
            console.log("Post succesful");
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
