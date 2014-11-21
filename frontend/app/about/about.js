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
            if (!checkVisit()) {
                storeVisit($http, Data);
            } else {
                // this can occur with a page refresh (checkVisit returns true but Data.visit is gone)
                // find Visit
                findVisit($http, Data);
            }
        }
    }
]);