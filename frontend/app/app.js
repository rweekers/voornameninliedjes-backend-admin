'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'myApp.about',
    'myApp.contact',
    'myApp.home',
    'myApp.remark',
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
    })
    .factory('Data', function() {
        return {
            visit: null,
            setVisit: function(msg) {
                this.visit = msg;
            },
            clear: function() {
                this.visit = null;
            }
        };
    });

function storeVisit($http, Data) {

    $http({
        // url: 'http://127.0.0.1:8180/voornaaminliedje/api/visit/add',
        url: '/namesandsongs/api/visit/add',
        method: "POST",
        params: {
            userAgent: navigator.userAgent
        }
    }).success(function(data) {
        console.log("Stored visit for " + data.ipAddress);
        Data.setVisit(data);
    })
        .error(function(data) {});
}

function storeSearchInstruction($http, argument, visit) {

    $http({
        url: '/namesandsongs/api/searchInstruction/add',
        method: 'POST',
        params: {
            argument: argument,
            userAgent: navigator.userAgent,
            visitId: visit.id
        }
    }).success(function(data) {})
        .error(function(data) {
            console.log("Error: " + data);
        });
}

function checkVisit() {
    var yetVisited = sessionStorage ? sessionStorage['visited'] : $.cookie('visited');

    // store visit for session
    sessionStorage ? sessionStorage['visited'] = 'yes' : $.cookie('visited', 'yes');

    if (!yetVisited) {
        return false;
    }
    return true;
}

function findVisit($http, Data) {
    $http({
        url: '/namesandsongs/api/visit/find',
        method: 'GET',
        params: {
            userAgent: navigator.userAgent
        }
    }).success(function(data) {
        Data.visit = data;
    })
        .error(function(data) {
            console.log("Error: " + data);
        });
}