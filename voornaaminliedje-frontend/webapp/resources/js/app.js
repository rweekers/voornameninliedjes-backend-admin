'use strict';

/* App Module */
var songApp = angular.module('songApp', [
  'ngRoute',
  'songControllers',
  'songServices'
]);

songApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/visits', {
        templateUrl: 'partials/visits.html',
        controller: 'DemoCtrl'
      }).
      when('/visits/:visitId', {
        templateUrl: 'partials/visit-detail.html',
        controller: 'VisitDetailCtrl'
      }).
      otherwise({
        redirectTo: '/visits'
      });
  }]);