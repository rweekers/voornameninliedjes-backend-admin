'use strict';

/* Controllers */

var adminControllers = angular.module('adminControllers', []);

function HeaderController($scope, $location) {
    $scope.isActive = function(viewLocation) {
        return viewLocation === $location.path();
    };
}

adminControllers.controller('VisitListCtrl', ['$scope', 'Visit', 
    function($scope, Visit) {

        $scope.visits = Visit.query();
        $scope.orderProp = 'ipAddress';

    }
]);

adminControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', 'VisitDetail', 'Auth',
    function($scope, $routeParams, VisitDetail, Auth) {

        VisitDetail.get({
            id: $routeParams.visitId
        }).$promise.then(function(data) {
            $scope.visit = data;
        }, function(errorResponse) {
            console.log("Error...");
        });

    }
]);

adminControllers.controller('SongListCtrl', ['$scope', 'Song',
    function($scope, Song) {

        $scope.songs = Song.query();

    }
]);

adminControllers.controller('SongDetailCtrl', ['$scope', '$routeParams', 'Song', 
    function($scope, $routeParams, Song) {

        $scope.song = Song.get({
            id: $routeParams.songId
        });

    }
]);

adminControllers.controller('SongEditCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', 'Song', 
    function($scope, $location, $routeParams, $cookieStore, Song) {

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
