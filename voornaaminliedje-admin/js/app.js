'use strict';

/* App Module */

var adminApp = angular.module('app', [
    'ngRoute',
    'ngCookies',
    'google-maps',
    'adminControllers',
    'visitServices'
]);

adminApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/visits', {
            templateUrl: 'partials/visit-list.html',
            controller: 'VisitListCtrl'
        }).
        when('/visits/:visitId', {
            templateUrl: 'partials/visit-detail.html',
            controller: 'VisitDetailCtrl'
        }).
        when('/login', {
            templateUrl: 'login.html',
            controller: 'LoginCtrl'
        }).
        otherwise({
            redirectTo: '/visits'
        });
    }
]);
/*
adminApp.config(['$httpProvider',
    function($httpProvider) {
        $httpProvider.interceptors.push('errorHttpInterceptor');
    }
]);*/
