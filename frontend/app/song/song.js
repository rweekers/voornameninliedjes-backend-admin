'use strict';

angular.module('myApp.song', ['ngRoute', 'ngResource'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/song/:songId', {
            templateUrl: 'song/song.html',
            controller: 'SongCtrl'
        });
    }
])

.controller('SongCtrl', ['$scope', '$location', '$http', '$routeParams', 'SongDetail', 'Data',
    function($scope, $location, $http, $routeParams, SongDetail, Data) {

        if (!Data.visit) {
            if (!checkVisit()) {
                storeVisit($http, Data);
            } else {
                // this can occur with a page refresh (checkVisit returns true but Data.visit is gone)
                // find Visit
                findVisit($http, Data);
            }
        }

        $scope.song = SongDetail.get({
            id: $routeParams.songId
        }).$promise.then(function(data) {
            $scope.song = data;
            $scope.theBestVideo = 'sMKoNBRZM1M';
            // scroll to top
            $("html, body").animate({
                scrollTop: 0
            }, "slow");
        }, function(errorResponse) {
            console.log("Error...");
        });

        $(function() {
            $('[data-toggle="tooltip"]').tooltip()
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