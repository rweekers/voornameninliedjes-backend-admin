'use strict';

angular.module('myApp.songs', ['ngRoute', 'ngResource'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/songs', {
    templateUrl: 'songs/songs.html',
    controller: 'SongsCtrl'
  });
}])

.controller('SongsCtrl', ['$scope', '$location', 'Song',
    function($scope, $location, Song) {

        console.log('Locatie ' + $location.path());
        console.log('Locatie2 ' + $location.path());

        $scope.songs = Song.query();
        // $scope.orderProp = 'ipAddress';
    }
])

.factory('Song', ['$resource',
     function($resource) {
         return $resource('http://namesandsongs.dev/namesandsongs/api/song/all', {}, {
             query: {
                 method: 'GET',
                 isArray: true
             }
         });
     }
 ]);
