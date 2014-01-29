'use strict';

/* Controllers */

var adminControllers = angular.module('adminControllers', []);

adminControllers.controller('VisitListCtrl', ['$scope', 'Visit', 'Auth',
    function($scope, Visit, Auth) {

        Auth.setCredentials('admin', '5095df0e6547e2647d5bc40f1ecd9afe')

        $scope.visits = Visit.query();
        $scope.orderProp = 'ipAddress';
    }
]);

adminControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', 'VisitDetail', 'Auth',
    function($scope, $routeParams, VisitDetail, Auth) {

        Auth.setCredentials('admin', '5095df0e6547e2647d5bc40f1ecd9afe')

        $scope.visit = VisitDetail.get({
            id: $routeParams.visitId
        });

        $scope.visit.$promise.then(function(data) {

            $scope.map = {
                center: {
                    latitude: $scope.visit.latitude,
                    longitude: $scope.visit.longitude
                },
                zoom: 8
            };
        });

        $scope.map = {
            center: {
                latitude: 51,
                longitude: 0
            },
            zoom: 8
        };

    }
]);
