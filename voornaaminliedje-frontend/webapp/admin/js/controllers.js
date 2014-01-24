'use strict';

/* Controllers */

var phonecatControllers = angular.module('phonecatControllers', []);

phonecatControllers.controller('VisitListCtrl', ['$scope', 'Visit',
    function($scope, Visit) {

        $scope.phones = Visit.query();
        $scope.orderProp = 'ipAddress';
    }
]);

phonecatControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams',
    function($scope, $routeParams) {
        $scope.phoneId = $routeParams.phoneId;
    }
]);
