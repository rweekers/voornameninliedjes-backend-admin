'use strict';

angular.module('myApp.songs', ['ngRoute', 'ngResource'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/songs', {
    templateUrl: 'songs/songs.html',
    controller: 'SongsCtrl'
  });
}])

.controller('SongsCtrl', ['$scope', '$location', '$resource', '$http', 'Song',  
    function($scope, $location, $resource, $http, Song) {

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

        $http.get('/namesandsongs/api/song/count', {params: {filterArtist: 'Bob'}})
            .success(function(data) {
            $scope.max = data;
            console.log("Count is " + data);
        });

        $scope.bla = function() {
            if ($scope.page == 0)
            {
                return true;
            }
            return false;
        };

        $scope.bla2 = function() {
            if (($scope.page + 1) * $scope.count > $scope.max )
            {
                return true;
            }
            return false;
        }

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
            console.log("Called next " + $scope.max);
            $scope.page++;
            $scope.update();
        };

        $scope.last = function() {
            // $scope.spice = 'chili';
            console.log("Called last");
        };
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
