'use strict';

angular.module('myApp.searches', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/searches', {
    templateUrl: 'searches/searches.html',
    controller: 'SearchesCtrl'
  });
}])

.controller('SearchesCtrl', ['$scope', 'Search', 
	function($scope, Search) {

		$scope.searches = Search.query();

}])

.factory('Search', ['Auth', '$http', '$resource',
    function(Auth, $http, $resource) {

        return $resource('/namesandsongs/api/admin/searchInstruction/all', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]);