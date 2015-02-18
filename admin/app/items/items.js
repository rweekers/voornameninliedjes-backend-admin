'use strict';

angular.module('myApp.items', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/items', {
    templateUrl: 'items/items.html',
    controller: 'ItemsCtrl'
  });
}])

.controller('ItemsCtrl', ['$scope', 'Item', 
    function($scope, Item) {
        $scope.items = Item.query();
}])

.factory('Item', ['$resource',
    function($resource) {
        return $resource('/namesandsongs/api/admin/item/:id', {
            id: '@id'
        }, {
            query: {
                method: 'GET',
                params: {},
                isArray: true
            },
            get: {
                method: 'GET',
                params: {
                    id: ''
                },
                isArray: false
            },
            /*save: {
                method: 'POST',
                params: {
                    title: ''
                }
            },*/
            update: {
                method: 'PUT',
                params: {
                    artist: '',
                    title: ''
                }
            }
        });
    }
]);