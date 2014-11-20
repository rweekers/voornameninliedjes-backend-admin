'use strict';

angular.module('myApp.about', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/about', {
            templateUrl: 'about/about.html',
            controller: 'AboutCtrl'
        });
    }
])

.controller('AboutCtrl', ['$location', '$http', 'Data',
    function($location, $http, Data) {
        if (!Data.visit) {
            storeVisit($http, Data);
        }

        console.log("Visit is " + Data.visit.browser + " with id " + Data.visit.id);
    }
]);