'use strict';

/* App Module */

var adminApp = angular.module('app', [
    'ngRoute',
    'ngCookies',
    'adminControllers',
    'visitServices',
    'songServices',
    'songDirectives'
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
        when('/songs/edit/:songId', {
            templateUrl: 'partials/song-edit.html',
            controller: 'SongEditCtrl'
        }).
        when('/song/add', {
            templateUrl: 'partials/song-add.html',
            controller: 'SongAddCtrl'
        }).
        when('/login', {
            templateUrl: 'partials/login.html',
            controller: 'LoginCtrl'
        }).
        when('/error', {
            templateUrl: 'partials/error.html',
            controller: 'ErrorCtrl'
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
