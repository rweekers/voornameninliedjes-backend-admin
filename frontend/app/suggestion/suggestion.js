'use strict';

angular.module('myApp.suggestion', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/suggestion', {
            templateUrl: 'suggestion/suggestion.html',
            controller: 'SuggestionCtrl'
        });
    }
])

.controller('SuggestionCtrl', ['$scope', '$location', '$routeParams', 'SongDetail', 'Suggestion', 'Data',
    function($scope, $location, $routeParams, SongDetail, Suggestion, Data) {

        if (!Data.visit) {
            if (!checkVisit()) {
                storeVisit($http, Data);
            } else {
                // this can occur with a page refresh (checkVisit returns true but Data.visit is gone)
                // find Visit
                findVisit($http, Data);
            }
        }

        $scope.suggestion = new Suggestion();
        $scope.suggestion.status = 'New';

        $scope.save = function() {

            $scope.suggestion.$save({
                visitId: Data.visit.id
            }, function() {
                $location.path('/songs/');
            });
        };

        $scope.cancel = function() {
            $location.path('/songs/');
        };

        $scope.valid = function(e) {
            if ($scope.suggestion) {
                if ($scope.suggestion.artist && $scope.suggestion.title) {
                    return true;
                }
                return false;
            } else {
                return false;
            }

        };
    }
])

.factory('Suggestion', ['$resource',
    function($resource) {
        return $resource('/namesandsongs/api/suggestion/:id', {
            id: '@id'
        }, {
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