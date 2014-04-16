'use strict';

/* App Module */

var adminApp = angular.module('app', [
    'ngRoute',
    'ngCookies',
    //'google-maps',
    'adminControllers',
    'visitServices',
    'songServices'
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
        when('/songs', {
            templateUrl: 'partials/song-list.html',
            controller: 'SongListCtrl'
        }).
        when('/songs/:songId', {
            templateUrl: 'partials/song-detail.html',
            controller: 'SongDetailCtrl'
        }).
        when('/login', {
            templateUrl: 'partials/login.html',
            controller: 'LoginCtrl'
        }).
        otherwise({
            redirectTo: '/visits'
        });
    }
]);

adminApp.config(['$httpProvider',
    function($httpProvider) {
        $httpProvider.interceptors.push('errorHttpInterceptor');
    }
]);
