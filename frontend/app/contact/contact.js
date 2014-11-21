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
            if (!checkVisit()) {
                storeVisit($http, Data);
            } else {
                // this can occur with a page refresh (checkVisit returns true but Data.visit is gone)
                // find Visit
                findVisit($http, Data);
            }
        }

        // console.log("Message " + e("info","namesandsongs",0,""));
        $scope.email = e("info", "namesandsongs", 0, "");
    }
]);