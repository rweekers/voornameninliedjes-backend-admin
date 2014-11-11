'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'ngCookies',
  'myApp.visits',
  'myApp.songs',
  'myApp.searches',
  'myApp.suggestions',
  'myApp.login',
  'myApp.version'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/visits'});
}])
.controller('HeaderCtrl', function($scope, $location) {
    $scope.isActive = function(route) {
        return route === $location.path();
    }
});
