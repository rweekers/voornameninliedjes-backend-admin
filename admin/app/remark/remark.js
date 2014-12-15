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
        });

        $("html, body").animate({ scrollTop: 0 }, "slow");

        $scope.save = function() {
            console.log("Saving song by user " + $cookieStore.get('user'));
            $scope.song.userModified = $cookieStore.get('user');
            $scope.song.$save();
        };

        $scope.cancel = function() {
            console.log("Canceling...");
        };
    }
]);