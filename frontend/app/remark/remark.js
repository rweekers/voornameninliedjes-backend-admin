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

        // Create service for remark, add new remark
        /*
        $scope.song = Song.get({
            id: 12070
        });*/
        /*
        $scope.song = new Song({
            artist: 'Test Artist'
        });
        $scope.song.title = 'Test Title';
        */

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
            console.log("Gotten song " + $scope.song.title);
            console.log("Visit is with " + Data.visit.browser);
            $scope.remark = new Remark();
            $scope.remark.commentary = 'RemarkComment1';
            $scope.remark.status = 'New';
            // $scope.remark.song = $scope.song;
            // $scope.remark.visit = Data.visit;
        }, function(errorResponse) {
            console.log("Error...");
        });

        $scope.save = function() {
            console.log("Saving remark...");

            $scope.remark.$save({
                    visitId: Data.visit.id,
                    songId: $scope.song.id
                },

                function(user) {
                    if (user.id) {
                        console.log("Remark saved is " + user.id);
                        // $location.path('/songs');
                    } else {
                        console.log("Remark could not be saved");
                    }
                });


        };

        $scope.cancel = function() {
            console.log("Canceling...");
            $location.path('/songs');
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