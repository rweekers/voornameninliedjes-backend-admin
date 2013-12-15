'use strict';

/* Controllers */

var songApp = angular.module('songApp', []);
var songOTD;

songApp.controller('SongListCtrl', function($scope, $http) {
    $http.get('http://localhost:8080/voornaaminliedje/api/songs/all').success(function(data) {
        $scope.songs = data.splice(0, 10);
    });

    $http.get('http://localhost:8080/voornaaminliedje/api/song/al').success(function(data) {
        $scope.songOfTheDay = data;
        songOTD = $scope.songOfTheDay;
    });

    logVisit();

    $scope.orderProp = 'artist';

    $scope.zoekOpdracht;

    $scope.search = function($event) {
     logSearchInstruction($scope.zoekOpdracht);
    }
});
