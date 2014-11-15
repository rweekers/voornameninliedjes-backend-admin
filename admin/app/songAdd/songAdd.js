'use strict';

angular.module('myApp.songAdd', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/songAdd', {
    templateUrl: 'songAdd/songAdd.html',
    controller: 'SongAddCtrl'
  });
}])

.controller('SongAddCtrl', ['$scope', '$location', '$cookieStore', 'Song', 
    function($scope, $location, $routeParams, $cookieStore, Song) {

        console.log("Opening ctrl page for adding song");

        $scope.save = function() {
            console.log("Saving song by user " + $cookieStore.get('user'));
            $scope.song.userModified = $cookieStore.get('user');
            $scope.song.$save();
            $location.path('/songs');
        };

        $scope.cancel = function() {
            console.log("Canceling adding song...");
            $location.path('/songs');
        };
}]);
