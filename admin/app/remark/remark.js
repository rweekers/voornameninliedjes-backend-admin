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

.controller('RemarkCtrl', ['$scope', '$routeParams', '$cookieStore', '$http', 'Remark', 'Song',
    function($scope, $routeParams, $cookieStore, $http, Remark, Song) {
        // $scope.remarks = Remark.query();
        Remark.get({
            id: $routeParams.remarkId
        }).$promise.then(function(data) {
            $scope.remark = data;
            console.log("Gotten remark " + data.id + " with songId " + data.song.id);
            //$scope.song = Song.get({
            //    id: data.song.id
            //})
            $http({
                url: '/namesandsongs/api/admin/remark/song/' + data.id,
                method: 'GET',
                params: {
                }
            }).success(function(song) {
                $scope.song = song;
            })
                .error(function(error) {
                    console.log("Error: " + error);
                });
        });

        $("html, body").animate({
            scrollTop: 0
        }, "slow");

        $scope.save = function() {
            $scope.song.userModified = $cookieStore.get('user');
            $scope.song.$save();
            // saving remark
            console.log("Saving remark " + $scope.remark.id);
            $scope.remark.$save();
        };

        $scope.cancel = function() {
            console.log("Canceling...");
        };
    }
]);