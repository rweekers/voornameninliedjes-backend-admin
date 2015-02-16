'use strict';

angular.module('myApp.suggestion', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/suggestion/:suggestionId', {
            templateUrl: 'suggestion/suggestion.html',
            controller: 'SuggestionCtrl'
        });
    }
])

.controller('SuggestionCtrl', ['$scope', '$routeParams', '$cookieStore', '$http', '$location', '$sce', 'Suggestion', 'Song',
    function($scope, $routeParams, $cookieStore, $http, $location, $sce, Suggestion, Song) {
        console.log("Suggestiondetail.")

        Suggestion.get({
            id: $routeParams.suggestionId
        }).$promise.then(function(data) {
            $scope.suggestion = data;
            console.log("Gotten suggestion " + data.id);
        });

        $("html, body").animate({
            scrollTop: 0
        }, "slow");

        $scope.save = function() {
            console.log("Has to be implemented");
            $location.path('/suggestions');
        };

        $scope.cancel = function() {
            console.log("Canceling...");
        };
    }
]);