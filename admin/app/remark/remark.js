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

.controller('RemarkCtrl', ['$scope', '$routeParams', '$cookieStore', 'Remark', 'Song',
    function($scope, $routeParams, $cookieStore, Remark, Song) {
        // $scope.remarks = Remark.query();
        Remark.get({
            id: $routeParams.remarkId
        }).$promise.then(function(data) {
            $scope.remark = data;
            console.log('blabla' + data.song.id);
            $scope.song = Song.get({
                id: data.song.id
            })
        });;
        /*
        $scope.remark = Remark.get({
            id: $routeParams.remarkId
        }).then(function(data) {
            console.log("Blabla " + data.song.id)
            $scope.song = Song.get(data.song.id);
            // user.abc = true;
            // user.$save();
        });*/
        /*
        Remark.get({
            id: $routeParams.remarkId
        })
            .$promise.then(function(data) {
                $scope.remark = data;
                console.log("Blabla " + data.song);
                $scope.song = data.song;
                // $scope.user = user;
            });*/
        /*
        $scope.song = Song.get({
            id: 12743
        });*/

        $scope.save = function() {
            console.log("Saving song by user " + $cookieStore.get('user'));
            // $scope.song.userModified = $cookieStore.get('user');
            // $scope.song.$save();
            // $location.path('/songs');
            // console.log("Saving song " + $scope.remark.song.artist + " - " + $scope.remark.song.title);
            $scope.song.$save();
        };

        $scope.cancel = function() {
            // console.log("Canceling...");
            // $location.path('/remarks');
        };
        // console.log("Opening remark details...");
    }
]);