'use strict';

/* Controllers */

var songApp = angular.module('songApp', []);

songApp.controller('SongListCtrl', function($scope, $http) {
  $http.get('http://localhost:8080/voornaaminliedje/api/songs/all').success(function(data) {
    $scope.songs = data.splice(0,10);
  });

  $http.get('http://localhost:8080/voornaaminliedje/api/song/all').success(function(data) {
    $scope.songOfTheDay = data;
  });

  $scope.orderProp = 'artist';
});
