'use strict';

angular.module('myApp.songEdit', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/songEdit/:songId', {
            templateUrl: 'songEdit/songEdit.html',
            controller: 'SongEditCtrl'
        });
    }
])

.controller('SongEditCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', '$sce', 'Song',
    function($scope, $location, $routeParams, $cookieStore, $sce, Song) {

        $scope.song = Song.get({
            id: $routeParams.songId
        }).$promise.then(function(data) {
            $scope.song = data;
            $scope.backgroundHTML = $sce.trustAsHtml($scope.song.background);
        }, function(errorResponse) {
            console.log("Error...");
        });

        $("html, body").animate({
            scrollTop: 0
        }, "slow");

        $("#background").on("change keyup", function() {
            $scope.$apply(function () {
                $scope.backgroundHTML = $sce.trustAsHtml($scope.song.background);
            });
        });

        $scope.save = function() {
            console.log("Saving song by user " + $cookieStore.get('user'));
            $scope.song.userModified = $cookieStore.get('user');
            $scope.song.$save(function(){
                $location.path('/songs');
            });
        };

        $scope.cancel = function() {
            console.log("Canceling...");
            $location.path('/songs');
        };

        $scope.refresh = function() {
            $scope.backgroundHTML = $sce.trustAsHtml($scope.song.background);
        }
    }
]);