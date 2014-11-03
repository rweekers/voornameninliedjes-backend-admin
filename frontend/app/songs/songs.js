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

        $scope.songs = Song.query({
                                    count: 5,
                                    page: 1,
                                    filterArtist: 'Bob'
        });
        // $scope.orderProp = 'ipAddress';
    }
])

.factory('Song', ['$resource',
     function($resource) {
         return $resource('/namesandsongs/api/song/all', {}, {
             query: {
                 method: 'GET',
                 params: {
                     page: '',
                     count: '',
                     sortingArtist: '',
                     sortingTitle: '',
                     filterArtist: '',
                     filterTitle: ''
                 },
                 isArray: true
             }
         });
     }
 ]);

/*
                        count: $scope.tableParams.$params.count,
                        page: $scope.tableParams.$params.page - 1,
                        sortingArtist: $scope.tableParams.$params.sorting.artist,
                        sortingTitle: $scope.tableParams.$params.sorting.title,
                        filterArtist: $scope.tableParams.$params.filter.artist,
                        filterTitle: $scope.tableParams.$params.filter.title
*/