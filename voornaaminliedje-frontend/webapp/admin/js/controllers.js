'use strict';

/* Controllers */

var phonecatControllers = angular.module('phonecatControllers', []);
var la;
var lo;

phonecatControllers.controller('VisitListCtrl', ['$scope', 'Visit', 'Auth',
    function($scope, Visit, Auth) {

        Auth.setCredentials('admin', '5095df0e6547e2647d5bc40f1ecd9afe')

        $scope.phones = Visit.query();
        $scope.orderProp = 'ipAddress';
    }
]);

phonecatControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', 'VisitDetail', 'Auth',
    function($scope, $routeParams, VisitDetail, Auth) {

        Auth.setCredentials('admin', '5095df0e6547e2647d5bc40f1ecd9afe')

        $scope.phone = VisitDetail.get({
            id: $routeParams.phoneId
        });

        $scope.phone.$promise.then(function(data) {
            console.log("Latitude " + $scope.phone.latitude);
            console.log("Longitude " + $scope.phone.longitude);

            la = $scope.phone.latitude;
            lo = $scope.phone.longitude;

            console.log("Latitude2 " + la);
            console.log("Longitude2 " + lo);


        });

        console.log("Latitude3 " + la);
        console.log("Longitude3 " + lo);

        $scope.map = {
            center: {
                // latitude: 45,
                // longitude: -73
                latitude: la,
                longitude: lo
            },
            zoom: 8
        };

    }
]);
