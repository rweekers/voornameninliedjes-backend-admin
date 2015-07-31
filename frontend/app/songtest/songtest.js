'use strict';

angular.module('myApp.songtest', ['ngRoute', 'ngResource'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/songtest', {
            templateUrl: 'songtest/songtest.html',
            controller: 'SongtestCtrl'
        });
    }
])

.controller('SongtestCtrl', ['$scope', '$location', '$http', '$routeParams', '$sce', 'SongDetail', 'Data',
    function($scope, $location, $http, $routeParams, $sce, SongDetail, Data) {
        var start = new Date().getTime();
        var start2 = new Date().getTime();

        $http.get('/namesandsongs/api/song').success(function(data){
            $scope.songs = data;
            var end = new Date().getTime();
            var time = end - start;
            $( ".report" ).append( "<h4>Execution time: </h4>" + time);
            console.log("Time1 " + time);
        });
        $http.get('/node/api/song').success(function(data){
            $scope.songs2 = data;
            var end2 = new Date().getTime();
            var time2 = end2 - start2;
            $( ".report2" ).append( "<h4>Execution time: </h4>" + time2);
        });
    }
]);