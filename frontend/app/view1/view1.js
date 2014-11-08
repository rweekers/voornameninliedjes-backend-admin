'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$location', '$http', '$scope', function($location, $http, $scope) {
	var yetVisited = sessionStorage ? sessionStorage['visited'] : $.cookie('visited');
    if (!yetVisited) 
    {
        // console.log('Storing visit ' + $location.path());
		store($http);
    }

    // store visit for session
    sessionStorage ? sessionStorage['visited'] = 'yes' : $.cookie('visited', 'yes');

}]);