'use strict';

angular.module('myApp.remark', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/remark/:remarkId', {
            templateUrl: 'remark/remark.html',
            controller: 'RemarkCtrl'
        });
    }
])

.controller('RemarkCtrl', ['$scope', '$routeParams', 'Remark',
    function($scope, $routeParams, Remark) {
        // $scope.remarks = Remark.query();
        $scope.remark = Remark.get({
            id: $routeParams.remarkId
        });

        $scope.save = function() {
            // console.log("Saving song by user " + $cookieStore.get('user'));
            // $scope.song.userModified = $cookieStore.get('user');
            // $scope.song.$save();
            // $location.path('/songs');
            console.log("Saving song " + $scope.remark.song.artist + " - " + $scope.remark.song.title);
            $scope.remark.song.$save();
        };

        $scope.cancel = function() {
            console.log("Canceling...");
            $location.path('/remarks');
        };
        console.log("Opening remark details...");
    }
]);
