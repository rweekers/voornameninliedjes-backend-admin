'use strict';

/* Controllers */

var adminControllers = angular.module('adminControllers', []);

function HeaderController($scope, $location) {
    $scope.isActive = function(viewLocation) {
        return viewLocation === $location.path();
    };
}

adminControllers.controller('VisitListCtrl', ['$scope', '$location', 'Visit', 'Auth',
    function($scope, $location, Visit, Auth) {

        // console.log('Login nodig: ' + !Login.isLoggedin());
        /*
        $scope.$on('event:loginRequired', function() {
            $location.path('/login');
        });*/
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

        $scope.songs = Song.query();
    }
]);

adminControllers.controller('SongDetailCtrl', ['$scope', '$routeParams', '$cookieStore', '$http', 'Song', 'Auth',
    function($scope, $routeParams, $cookieStore, $http, Song, Auth) {

        $scope.song = Song.get({
            id: $routeParams.songId
        });
    }
]);

adminControllers.controller('SongEditCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', '$http', 'Song', 'Auth',
    function($scope, $location, $routeParams, $cookieStore, $http, Song, Auth) {

        $scope.song = Song.get({
            id: $routeParams.songId
        });

        $scope.save = function() {
            console.log("Saving song by user " + $cookieStore.get('user'));
            $scope.song.userModified = $cookieStore.get('user');
            $scope.song.$save();
            $location.path('/songs');
        };

        $scope.cancel = function() {
            console.log("Canceling...");
            $location.path('#/songs');
        };

    }
]);

adminControllers.controller('SongAddCtrl', ['$scope', '$location', 'Login', 'Song', 'Auth',
    function($scope, $location, Login, Visit, Auth) {

        console.log('Login nodig: ' + Login.isLoggedin());

        console.log('Locatie ' + $location.path());
    }
]);

adminControllers.controller('LoginCtrl', ['$scope', '$routeParams', '$cookieStore', 'Auth', 'Base64', 'ErrorService',
    function($scope, $routeParams, $cookieStore, Auth, Base64, ErrorService) {

        $scope.errorService = ErrorService;

        $scope.login = function() {
            Auth.clearCredentials();
            console.log("Logging in for " + $scope.username);
            Auth.setCredentials($scope.username, $scope.password);
        };

    }
]);

adminControllers.controller('ErrorCtrl', ['$scope', '$routeParams', '$cookieStore', 'Auth', 'Base64',
    function($scope, $routeParams, $cookieStore, Auth, Base64) {



    }
]);
