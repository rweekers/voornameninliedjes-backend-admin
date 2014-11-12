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

        $scope.visits = Visit.query();
        $scope.orderProp = 'ipAddress';
}])

.factory('Visit', ['Auth', '$http', '$resource',
    function(Auth, $http, $resource) {

        return $resource('/namesandsongs/api/admin/visit/all', {}, {
            query: {
                method: 'GET',
                params: {
                    visitId: 'visits'
                },
                isArray: true
            }
        });
    }
]);
