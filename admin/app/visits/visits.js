'use strict';

angular.module('myApp.visits', ['ngRoute', 'ngResource'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/visits', {
    templateUrl: 'visits/visits.html',
    controller: 'VisitsCtrl'
  });
}])

.controller('VisitsCtrl', ['$scope', 'Visit', 
    function($scope, Visit) {

    Visit.query({
        count: 15
    }).$promise.then(function(data){
        $scope.visits = data;
        Visit.query(function(d) {
            $scope.visits = d;
        });
    });
}])

.factory('Visit', ['$resource',
    function($resource) {
        return $resource('/namesandsongs/api/admin/visit/:id', {
            id: '@id'
        }, {
            query: {
                method: 'GET',
                params: {
                    count: ''
                },
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