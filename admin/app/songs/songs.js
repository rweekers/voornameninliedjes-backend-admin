'use strict';

angular.module('myApp.songs', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/songs', {
    templateUrl: 'songs/songs.html',
    controller: 'SongsCtrl'
  });
}])

.controller('SongsCtrl', ['$scope', 'Song', 
    function($scope, Song) {
        $scope.songs = Song.query();
}])

.factory('Song', ['$resource',
    function($resource) {
        return $resource('/namesandsongs/api/admin/song/:id', {
            id: '@id'
        }, {
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
            },
            get: {
                method: 'GET',
                params: {
                    id: ''
                },
                isArray: false
            },
            /*save: {
                method: 'POST',
                params: {
                    title: ''
                }
            },*/
            update: {
                method: 'PUT',
                params: {
                    artist: '',
                    title: ''
                }
            }
        });
    }
]);