'use strict';

angular.module('myApp.remarks', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/remarks', {
            templateUrl: 'remarks/remarks.html',
            controller: 'RemarksCtrl'
        });
    }
])

.controller('RemarksCtrl', ['$scope', 'Remark',
    function($scope, Remark) {
        $scope.remarks = Remark.query();

        $("html, body").animate({
            scrollTop: 0
        }, "slow");
    }
])

.factory('Remark', ['$resource',
    function($resource) {
        return $resource('/namesandsongs/api/admin/remark/:id', {
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