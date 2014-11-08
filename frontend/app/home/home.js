'use strict';

angular.module('myApp.home', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {
    templateUrl: 'home/home.html',
    controller: 'HomeCtrl'
  });
}])

.controller('HomeCtrl', ['$location', '$http', '$scope', function($location, $http, $scope) {
	var yetVisited = sessionStorage ? sessionStorage['visited'] : $.cookie('visited');
    if (!yetVisited) 
    {
        // console.log('Storing visit ' + $location.path());
		store($http);
    }

    // store visit for session
    sessionStorage ? sessionStorage['visited'] = 'yes' : $.cookie('visited', 'yes');

}]);