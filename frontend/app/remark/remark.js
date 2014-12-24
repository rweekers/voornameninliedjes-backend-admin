'use strict';

angular.module('myApp.remark', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/remark/:songId', {
            templateUrl: 'remark/remark.html',
            controller: 'RemarkCtrl'
        });
    }
])

.controller('RemarkCtrl', ['$scope', '$location', '$routeParams', 'SongDetail', 'Remark', 'Data',
    function($scope, $location, $routeParams, SongDetail, Remark, Data) {

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
            $scope.remark = new Remark();
            $scope.remark.status = 'New';
        }, function(errorResponse) {
            console.log("Error...");
        });

        $scope.save = function() {

            $scope.remark.$save({
                visitId: Data.visit.id,
                songId: $scope.song.id
            }, function(){
                $location.path('/song/' + $scope.song.id);
            });

            $scope.song = SongDetail.get({
                id: $routeParams.songId
            }).$promise.then(function(data) {
                $scope.song = data;
            }, function(errorResponse) {
                console.log("Error...");
            });

        };

        $scope.cancel = function() {
            $location.path('/song/' + $scope.song.id);
        };
    }
])

.factory('Remark', ['$resource',
    function($resource) {
        return $resource('/namesandsongs/api/remark/:id', {
            id: '@id'
        }, {
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