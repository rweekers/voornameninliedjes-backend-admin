'use strict';

angular.module('myApp.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'login/login.html',
    controller: 'LoginCtrl'
  });
}])

.controller('LoginCtrl', ['$scope', 'Auth', function($scope, Auth) {
	$scope.login = function() {
		Auth.clearCredentials();
		console.log("Logging in for " + $scope.username);
		Auth.setCredentials($scope.username, $scope.password);
	}
}]);
