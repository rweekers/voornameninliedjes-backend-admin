'use strict';

angular.module('myApp.itemAdd', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/itemAdd', {
            templateUrl: 'itemAdd/itemAdd.html',
            controller: 'ItemAddCtrl'
        });
    }
])

.controller('ItemAddCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', '$sce', 'Item',
    function($scope, $location, $routeParams, $cookieStore, $sce, Item) {

        $scope.item = new Item({
            status: 'New'
        });

        $("html, body").animate({
            scrollTop: 0
        }, "slow");

        $("#story").on("change keyup", function() {
            $scope.$apply(function() {
                $scope.backgroundHTML = $sce.trustAsHtml($scope.item.story);
            });
        });

        $scope.save = function() {
            console.log("Saving item by user " + $cookieStore.get('user'));
            $scope.item.userInserted = $cookieStore.get('user');
            $scope.item.type = 'Testtype';

            $scope.item.$save(function(user) {
                if (user.id) {
                    console.log("Item saved is " + user.id);
                    $location.path('/items');
                } else {
                    console.log("Item could not be saved");
                    $scope.result = 'Please enter the title and story for the item';
                }
            });


        };

        $scope.cancel = function() {
            console.log("Canceling...");
            $location.path('/items');
        };
    }
]);