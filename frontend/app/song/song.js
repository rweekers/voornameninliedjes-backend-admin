'use strict';

angular.module('myApp.song', ['ngRoute', 'ngResource'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/song/:songId', {
    templateUrl: 'song/song.html',
    controller: 'SongCtrl'
  });
}])

.controller('SongCtrl', ['$scope', '$location', '$routeParams', 'SongDetail', 'MBDetail',
    function($scope, $location, $routeParams, SongDetail, MBDetail) {

        console.log('Locatie song ' + $location.path());

        $scope.song = SongDetail.get({
            id: $routeParams.songId
        }).$promise.then(function(data) {
            $scope.song = data;
            console.log("Gotten song " + $scope.song.title);
            /*
            $scope.mb = MBDetail.get()
            .$promise.then(function(data) {
                var output = '';
                for (var property in data) {
                    output += property + ': ' + data[property]+'; ';
                }
                console.log("Mmkay " + output);
            }, function(errorResponse) {
                console.log("Error with MB...");
            });*/
        }, function(errorResponse) {
            console.log("Error...");
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
 ])

.factory('MBDetail', ['$resource',
     function($resource) {
         return $resource('http://musicbrainz.org/ws/2/work/?query=work:you%20can%20call%20me%20al&artist:paul%20simon', {}, {
             query: {
                 method: 'GET'/*,
                 params: {
                    id: ''
                 }*/
             }
         });
     }
 ]);
