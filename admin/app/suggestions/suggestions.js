'use strict';

angular.module('myApp.suggestions', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/suggestions', {
            templateUrl: 'suggestions/suggestions.html',
            controller: 'SuggestionsCtrl'
        });
    }
])

.controller('SuggestionsCtrl', ['$scope', 'Suggestion',
    function($scope, Suggestion) {
        $scope.suggestions = Suggestion.query();

        $("html, body").animate({
            scrollTop: 0
        }, "slow");
    }
])

.factory('Suggestion', ['$resource',
    function($resource) {
        return $resource('/namesandsongs/api/admin/suggestion/:id', {
            id: '@id'
        }, {
            query: {
                method: 'GET',
                params: {},
                isArray: true
            },
            get: {
                method: 'GET',
                params: {
                    id: ''
                },
                isArray: false
            },
            /*save: {
                method: 'POST',
                params: {
                    title: ''
                }
            },*/
            update: {
                method: 'PUT',
                params: {
                    artist: '',
                    title: ''
                }
            }
        });
    }
]);