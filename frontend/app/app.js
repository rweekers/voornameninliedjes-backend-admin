'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'myApp.about',
    'myApp.contact',
    'myApp.home',
    'myApp.songs',
    'myApp.song',
    'myApp.version'
]).
config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.otherwise({
            redirectTo: '/home'
        });
    }
])
    .controller('HeaderCtrl', function($scope, $location) {
        $scope.isActive = function(route) {
            return route === $location.path();
        }
    });

function store($http) {

    $http({
        // url: 'http://127.0.0.1:8180/voornaaminliedje/api/visit/add',
        url: '/namesandsongs/api/visit/add',
        method: "POST",
        params: {
            userAgent: navigator.userAgent
        }
    }).success(function(data) {})
        .error(function(data) {});
}

function storeSearchInstruction($http, argument) {
    $http({
        url: '/namesandsongs/api/searchInstruction/add',
        method: 'POST',
        params: {
            argument: argument,
            userAgent: navigator.userAgent
        }
    }).success(function(data) {})
        .error(function(data) {});
}

function storeVisit($location, $http) {
    var yetVisited = sessionStorage ? sessionStorage['visited'] : $.cookie('visited');
    if (!yetVisited) {
        console.log('Storing visit ' + $location.path());
        store($http);
    }

    // store visit for session
    sessionStorage ? sessionStorage['visited'] = 'yes' : $.cookie('visited', 'yes');
}