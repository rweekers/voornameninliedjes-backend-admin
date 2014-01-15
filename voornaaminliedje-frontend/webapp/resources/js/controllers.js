'use strict';

/* Controllers */

var songControllers = angular.module('songControllers', []);

songControllers.controller('VisitCtrl', ['$scope', 'Visit',
    function($scope, Visit) {

        console.log("Blabla");

        Visit.create({
            ipAddress: '1.1.1.1',
            browser: 'Chrome',
            operatingSystem: '7',
            city: 'bla',
            country: 'NED'
        });
    }
]);
