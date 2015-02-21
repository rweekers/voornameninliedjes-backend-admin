'use strict';

angular.module('myApp.itemEdit', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/itemEdit/:itemId', {
            templateUrl: 'itemEdit/itemEdit.html',
            controller: 'ItemEditCtrl'
        });
    }
])

.controller('ItemEditCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', '$sce', 'Item',
    function($scope, $location, $routeParams, $cookieStore, $sce, Item) {

        $scope.item = Item.get({
            id: $routeParams.itemId
        }).$promise.then(function(data) {
            $scope.item = data;
            $scope.backgroundHTML = $sce.trustAsHtml($scope.item.story);
        }, function(errorResponse) {
            console.log("Error...");
        });

        $("html, body").animate({
            scrollTop: 0
        }, "slow");

        $("#story").on("change keyup", function() {
            $scope.$apply(function () {
                $scope.backgroundHTML = $sce.trustAsHtml($scope.item.story);
            });
        });

        $scope.save = function() {
            console.log("Saving item by user " + $cookieStore.get('user'));
            $scope.item.userInserted = $cookieStore.get('user');
            $scope.item.$save(function(){
                $location.path('/items');
            });
        };

        $scope.cancel = function() {
            console.log("Canceling...");
            $location.path('/items');
        };

        $scope.refresh = function() {
            $scope.backgroundHTML = $sce.trustAsHtml($scope.item.story);
        }
    }
]);