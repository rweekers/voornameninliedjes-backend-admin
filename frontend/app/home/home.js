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

.controller('HomeCtrl', ['$http', '$scope', 'Data',
    function($http, $scope, Data) {
        if (!Data.visit) {
            storeVisit($http, Data);
        }
    }
]);