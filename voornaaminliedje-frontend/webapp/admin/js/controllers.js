'use strict';

/* Controllers */

var adminControllers = angular.module('adminControllers', []);

adminControllers.controller('VisitListCtrl', ['$scope', '$location', 'Login', 'Visit', 'Auth',
    function($scope, $location, Login, Visit, Auth) {

        console.log('Login nodig: ' + Login.isLoggedin());
        /*
        $scope.$on('event:loginRequired', function() {
            $location.path('/login');
        });*/
        console.log('Locatie ' + $location.path());
        $location.path('/login');
        console.log('Locatie2 ' + $location.path());

        Auth.setCredentials('admin', '5095df0e6547e2647d5bc40f1ecd9afe')

        $scope.visits = Visit.query();
        $scope.orderProp = 'ipAddress';
    }
]);

adminControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', 'VisitDetail', 'Auth',
    function($scope, $routeParams, VisitDetail, Auth) {

        Auth.setCredentials('admin', '5095df0e6547e2647d5bc40f1ecd9afe')

        console.log("Credentials uit cookie: " + $cookieStore.get('authdata2'));

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

adminControllers.controller('LoginCtrl', ['$scope', '$routeParams', '$cookieStore', 'Auth', 'Base64',
    function($scope, $routeParams, $cookieStore, Auth, Base64) {

        // Auth.setCredentials('admin', '5095df0e6547e2647d5bc40f1ecd9afe')

        // var encoded = Base64.encode('admin' + ':' + '5095df0e6547e2647d5bc40f1ecd9afe');
        // $http.defaults.headers.common.Authorization = 'Basic ' + encoded;

        console.log('Hallo');

        $scope.login = function() {
            console.log("Hallo " + $scope.username);
            var encoded = Base64.encode($scope.username + ':' + $scope.password);
            $cookieStore.put('authdata2', encoded);
        };

    }
]);
