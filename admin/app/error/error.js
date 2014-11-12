'use strict';

angular.module('myApp.error', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/error', {
        templateUrl: 'error/error.html',
        controller: 'ErrorCtrl'
    });
}])

.controller('ErrorCtrl', ['$scope', 'ErrorService', function($scope, ErrorService) {
	$scope.email = e("info","namesandsongs",0,"");
	$scope.bl = ErrorService.errorMessage;
}]);
