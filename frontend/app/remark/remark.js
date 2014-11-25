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

.controller('RemarkCtrl', ['$scope', '$location', '$routeParams', 'SongDetail', 'Data', 
    function($scope, $location, $routeParams, SongDetail, Data) {

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
        }, function(errorResponse) {
            console.log("Error...");
        });

        $scope.save = function() {
            console.log("Saving song by user " + $cookieStore.get('user'));
            $scope.song.userInserted = $cookieStore.get('user');

            $scope.song.$save(function(user) {
                if (user.id) {
                    console.log("Song saved is " + user.id);
                    $location.path('/songs');
                } else {
                    console.log("Song could not be saved");
                    $scope.result = 'Please enter the firstname that is found in the title (case-sensitive)';
                }
            });


        };

        $scope.cancel = function() {
            console.log("Canceling...");
            $location.path('/songs');
        };
    }
]);