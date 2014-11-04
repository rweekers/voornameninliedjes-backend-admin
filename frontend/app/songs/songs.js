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

        $scope.sizes = [ {code: 5, name: '5'}, {code: 10, name: '10'}, {code: 20, name: '20'}, {code: 50, name: '50'}];
        $scope.count = $scope.sizes[1];
        $scope.page = 0;

        $scope.songs = Song.query({
                                    count: $scope.count.code,
                                    page: $scope.page,
                                    filterArtist: 'Bob'
        });

        $scope.update = function() {
            // console.log($scope.item.code, $scope.item.name);
            // $scope.count = $scope.item.code;
            $scope.songs = Song.query({
                                    count: $scope.count.code,
                                    page: $scope.page,
                                    filterArtist: 'Bob'
            });
        }

        $scope.bla = function() {
            if ($scope.page == 0)
            {
                return true;
            }
            return false;
        };

        $scope.first = function() {
            // $scope.spice = 'chili';
            console.log("Called first");
            if ($scope.page != 0)
            {
                $scope.page = 0;
                $scope.update();
            }
        };

        $scope.previous = function() {
            // $scope.spice = 'chili';
            console.log("Called previous");
            if ($scope.page > 0)
            {
                $scope.page--;
                $scope.update();
            }
        };

        $scope.next = function() {
            // $scope.spice = 'chili';
            console.log("Called next");
            $scope.page++;
            $scope.update();
        };

        $scope.last = function() {
            // $scope.spice = 'chili';
            console.log("Called last");
        };

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