'use strict';

/* Controllers */

var adminControllers = angular.module('adminControllers', []);

function HeaderController($scope, $location) {
    $scope.isActive = function(viewLocation) {
        return viewLocation === $location.path();
    };
}

adminControllers.controller('VisitListCtrl', ['$scope', '$location', 'Login', 'Visit', 'Auth',
    function($scope, $location, Login, Visit, Auth) {

        console.log('Login nodig: ' + Login.isLoggedin());
        /*
        $scope.$on('event:loginRequired', function() {
            $location.path('/login');
        });*/
        console.log('Locatie ' + $location.path());
        console.log('Locatie2 ' + $location.path());

        $scope.visits = Visit.query();
        $scope.orderProp = 'ipAddress';
    }
]);

adminControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', '$cookieStore', '$http', 'VisitDetail', 'Auth',
    function($scope, $routeParams, $cookieStore, $http, VisitDetail, Auth) {

        console.log("blabla1");

        VisitDetail.get({
            id: $routeParams.visitId
        }).$promise.then(function(data) {
            console.log("blabladiebla");
            $scope.visit = data;
            console.log("Bla " + $scope.visit.ipAddress);
            /*
                $scope.map = {
                    center: {
                        latitude: $scope.visit.latitude,
                        longitude: $scope.visit.longitude
                    },
                    zoom: 8
                };*/
        }, function(errorRespone) {
            console.log("errortje...");
        });

        /*
            $scope.map = {
                center: {
                    latitude: 51,
                    longitude: 0
                },
                zoom: 8
            };*/
    }
]);

adminControllers.controller('SongListCtrl', ['$scope', '$location', 'Song',
    function($scope, $location, Song) {

        console.log('Locatie ' + $location.path());
        console.log('Locatie2 ' + $location.path());

        $scope.songs = Song.query();
    }
]);

adminControllers.controller('SongDetailCtrl', ['$scope', '$routeParams', '$cookieStore', '$http', 'SongDetail', 'Auth',
    function($scope, $routeParams, $cookieStore, $http, SongDetail, Auth) {

        console.log("blabla1");

        SongDetail.get({
            id: $routeParams.songId
        }).$promise.then(function(data) {
            console.log("blabladiebla");
            $scope.song = data;
            console.log("Bla " + $scope.song.title);
        }, function(errorRespone) {
            console.log("errortje...");
        });
    }
]);

adminControllers.controller('SongAddCtrl', ['$scope', '$location', 'Login', 'Visit', 'Auth',
    function($scope, $location, Login, Visit, Auth) {

        console.log('Login nodig: ' + Login.isLoggedin());

        console.log('Locatie ' + $location.path());
    }
]);

adminControllers.controller('LoginCtrl', ['$scope', '$routeParams', '$cookieStore', 'Auth', 'Base64',
    function($scope, $routeParams, $cookieStore, Auth, Base64) {

        console.log('Hallo');
        Auth.clearCredentials();

        $scope.login = function() {
            console.log("Hallo " + $scope.username);
            // var encoded = Base64.encode($scope.username + ':' + $scope.password);
            // $cookieStore.put('authdata', encoded);
            Auth.setCredentials($scope.username, $scope.password);
        };

    }
]);
