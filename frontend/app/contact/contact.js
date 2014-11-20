'use strict';

angular.module('myApp.contact', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/contact', {
            templateUrl: 'contact/contact.html',
            controller: 'ContactCtrl'
        });
    }
])

.controller('ContactCtrl', ['$location', '$http', '$scope', 'Data', 
    function($location, $http, $scope, Data) {
        if (!Data.visit) {
            storeVisit($http, Data);
        }

        console.log("Visit is " + Data.visit.browser);

        // console.log("Message " + e("info","namesandsongs",0,""));
        $scope.email = e("info", "namesandsongs", 0, "");
    }
]);