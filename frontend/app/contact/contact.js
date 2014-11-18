'use strict';

angular.module('myApp.contact', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/contact', {
    templateUrl: 'contact/contact.html',
    controller: 'ContactCtrl'
  });
}])

.controller('ContactCtrl', ['$location', '$http', '$scope', function($location, $http, $scope) {
	storeVisit($location, $http);
	// console.log("Message " + e("info","namesandsongs",0,""));
	$scope.email = e("info","namesandsongs",0,"");
}]);