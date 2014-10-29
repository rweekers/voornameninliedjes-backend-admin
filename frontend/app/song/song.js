'use strict';

angular.module('myApp.song', ['ngRoute', 'ngResource'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/song/:songId', {
    templateUrl: 'song/song.html',
    controller: 'SongCtrl'
  });
}])

.controller('SongCtrl', ['$scope', '$location', '$routeParams', 'SongDetail',
    function($scope, $location, $routeParams, SongDetail) {

        console.log('Locatie song ' + $location.path());

        $scope.song = SongDetail.get({
            id: $routeParams.songId
        });
    }
])

.factory('SongDetail', ['$resource',
     function($resource) {
         return $resource('/namesandsongs/api/song/:id', {}, {
             query: {
                 method: 'GET',
                 params: {
                    id: ''
                 }
             }
         });
     }
 ]);
