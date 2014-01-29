'use strict';

/* Controllers */

var songControllers = angular.module('songControllers', []);

songControllers.controller('VisitCtrl', ['$scope', '$http', 
    function($scope, $http) {

        store($http);

    }
]);

songControllers.controller('SongOfTheDayCtrl', ['$scope', 'SongOfTheDay',
    function($scope, SongOfTheDay) {

        $scope.songOfTheDay = SongOfTheDay.query();
        // songOTD = $scope.songOfTheDay;
    }
]);

function store($http) {

    $http({
        // url: 'http://127.0.0.1:8180/voornaaminliedje/api/visit/add',
        url: 'http://localhost:8180/voornaaminliedje/api/visit/add',
        method: "POST",
        params: {
            userAgent: navigator.userAgent
        }
    }).success(function(data) {})
        .error(function(data) {});

}
