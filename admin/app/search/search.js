'use strict';

angular.module('myApp.search', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/search/:searchId', {
    templateUrl: 'search/search.html',
    controller: 'SearchCtrl'
  });
}])

.controller('SearchCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', 'Visit', 
    function($scope, $location, $routeParams, $cookieStore, Search) {
        $scope.search = Search.get({
            id: $routeParams.searchId
        });
}]);
