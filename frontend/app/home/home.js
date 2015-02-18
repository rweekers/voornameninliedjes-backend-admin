'use strict';

angular.module('myApp.home', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/home', {
            templateUrl: 'home/home.html',
            controller: 'HomeCtrl'
        });
    }
])

.controller('HomeCtrl', ['$http', '$scope', 'Data', 'Item', 
    function($http, $scope, Data, Item) {
        if (!Data.visit) {
            if (!checkVisit()) {
                storeVisit($http, Data);
            } else {
                // this can occur with a page refresh (checkVisit returns true but Data.visit is gone)
                // find Visit
                findVisit($http, Data);
            }
        }
        $scope.items = Item.query();
    }
])

.factory('Item', ['$resource',
    function($resource) {
        return $resource('/namesandsongs/api/item/:id', {
            id: '@id'
        }, {
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