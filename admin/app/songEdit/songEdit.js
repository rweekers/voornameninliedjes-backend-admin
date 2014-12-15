'use strict';

angular.module('myApp.songEdit', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/songEdit/:songId', {
    templateUrl: 'songEdit/songEdit.html',
    controller: 'SongEditCtrl'
  });
}])

.controller('SongEditCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', 'Song', 
    function($scope, $location, $routeParams, $cookieStore, Song) {
        $scope.song = Song.get({
            id: $routeParams.songId
        });
        $("html, body").animate({ scrollTop: 0 }, "slow");

        $scope.save = function() {
            console.log("Saving song by user " + $cookieStore.get('user'));
            $scope.song.userModified = $cookieStore.get('user');
            $scope.song.$save();
            $location.path('/songs');
        };

        $scope.cancel = function() {
            console.log("Canceling...");
            $location.path('/songs');
        };
}]);
