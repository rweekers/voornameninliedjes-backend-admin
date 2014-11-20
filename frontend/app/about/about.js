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
        console.log("Check visit " + Data.visit.browser);
        storeVisit($location, $http);
    }
]);