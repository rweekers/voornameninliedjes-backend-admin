'use strict';

angular.module('myApp.songs', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/songs', {
    templateUrl: 'songs/songs.html',
    controller: 'SongsCtrl'
  });
}])

.controller('SongsCtrl', ['$scope', '$location', 
    function($scope, $location, Login, Visit, Auth) {

        console.log('Locatie ' + $location.path());
        console.log('Locatie2 ' + $location.path());

        // $scope.visits = Visit.query();
        // $scope.orderProp = 'ipAddress';
    }
]);
