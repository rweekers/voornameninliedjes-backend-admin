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
            if (!checkVisit()) {
                storeVisit($http, Data);
            } else {
                // this can occur with a page refresh (checkVisit returns true but Data.visit is gone)
                // find Visit
                findVisit($http, Data);
            }
        }
        $scope.visit = Data.visit;
    }
]);