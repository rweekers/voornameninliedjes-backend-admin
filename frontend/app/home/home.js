'use strict';

angular.module('myApp.home', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/home', {
            templateUrl: 'home/home.html',
            controller: 'HomeCtrl'
        });
    }
])

.controller('HomeCtrl', ['$location', '$http', '$scope', 'Data',
    function($location, $http, $scope, Data) {
        storeVisit($location, $http, Data);
    }
]);