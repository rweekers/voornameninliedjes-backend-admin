'use strict';

/* Controllers */

var phonecatControllers = angular.module('phonecatControllers', []);

phonecatControllers.controller('VisitListCtrl', ['$scope', '$http',
    function($scope, $http) {

        // $http.get('visits/visits.json').success(function(data) {
        $http.get('http://localhost:8080/voornaaminliedje/api/visit/all').success(function(data) {
            $scope.phones = data;
        });

        $scope.orderProp = 'ipAddress';
    }
]);

phonecatControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams',
    function($scope, $routeParams) {
        $scope.phoneId = $routeParams.phoneId;
    }
]);
