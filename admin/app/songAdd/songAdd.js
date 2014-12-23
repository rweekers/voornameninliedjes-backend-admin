'use strict';

angular.module('myApp.songAdd', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/songAdd', {
            templateUrl: 'songAdd/songAdd.html',
            controller: 'SongAddCtrl'
        });
    }
])

.controller('SongAddCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', '$sce', 'Song',
    function($scope, $location, $routeParams, $cookieStore, $sce, Song) {

        /*
        $scope.song = Song.get({
            id: 12070
        });*/
        $scope.song = new Song({
            // artist: 'Test Artist'
        });
        // $scope.song.title = 'Test Title';

        $("html, body").animate({
            scrollTop: 0
        }, "slow");

        $("#background").on("change keyup", function() {
            $scope.$apply(function() {
                $scope.backgroundHTML = $sce.trustAsHtml($scope.song.background);
            });
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