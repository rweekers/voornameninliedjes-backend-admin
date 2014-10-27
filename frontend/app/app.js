'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2',
  'myApp.about',
  'myApp.contact',
  'myApp.songs',
  'myApp.version'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/view1'});
}])
.controller('HeaderCtrl', function($scope, $location) {
    $scope.isActive = function(route) {
        return route === $location.path();
    }
});
