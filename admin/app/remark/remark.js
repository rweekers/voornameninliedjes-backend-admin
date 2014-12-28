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

.controller('RemarkCtrl', ['$scope', '$routeParams', '$cookieStore', '$http', '$location', '$sce', 'Remark', 'Song',
    function($scope, $routeParams, $cookieStore, $http, $location, $sce, Remark, Song) {
        // $scope.remarks = Remark.query();
        Remark.get({
            id: $routeParams.remarkId
        }).$promise.then(function(data) {
            $scope.remark = data;
            console.log("Gotten remark " + data.id + " with songId " + data.song.id);
            $scope.song = Song.get({
                id: data.song.id
            });
        });

        $("html, body").animate({
            scrollTop: 0
        }, "slow");

        $("#background").on("change keyup", function() {
            $scope.$apply(function() {
                $scope.backgroundHTML = $sce.trustAsHtml($scope.song.background);
            });
        });

        $scope.save = function() {
            $scope.song.userModified = $cookieStore.get('user');
            $scope.song.$save();
            // saving remark
            console.log("Saving remark " + $scope.remark.id);
            $scope.remark.$save(function(){
                $location.path('/remarks');
            });
        };

        $scope.cancel = function() {
            console.log("Canceling...");
        };
    }
]);