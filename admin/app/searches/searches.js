'use strict';

angular.module('myApp.searches', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/searches', {
    templateUrl: 'searches/searches.html',
    controller: 'SearchesCtrl'
  });
}])

.controller('SearchesCtrl', ['$scope', 'Search', 
    function($scope, Search) {
        $scope.searches = Search.query();
}])

.factory('Search', ['$resource',
    function($resource) {
        return $resource('/namesandsongs/api/admin/searchInstruction/:id', {
            id: '@id'
        }, {
            query: {
                method: 'GET',
                params: {
                    page: '',
                    count: '',
                    sortingArtist: '',
                    sortingTitle: '',
                    filterArtist: '',
                    filterTitle: ''
                },
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