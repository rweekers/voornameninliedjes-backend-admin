'use strict';

/* App Module */

var phonecatApp = angular.module('adminApp', [
  'ngRoute',
  'ngCookies',
  'phonecatControllers',
  'visitServices'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/visits', {
        templateUrl: 'partials/visit-list.html',
        controller: 'VisitListCtrl'
      }).
      when('/visits/:phoneId', {
        templateUrl: 'partials/visit-detail.html',
        controller: 'VisitDetailCtrl'
      }).
      otherwise({
        redirectTo: '/visits'
      });
  }]);
