'use strict';

angular.module('myApp.visit', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/visit/:visitId', {
    templateUrl: 'visit/visit.html',
    controller: 'VisitCtrl'
  });
}])

.controller('VisitCtrl', ['$scope', '$location', '$cookieStore', 'Visit', 
    function($scope, $location, $routeParams, $cookieStore, Visit) {

        console.log("Opening ctrl page for visitdetails");

}]);

/**.factory('VisitDetail', ['Auth', '$http', '$resource',
    function(Auth, $http, $resource) {

        return $resource('/namesandsongs/api/admin/visit/all', {}, {
            query: {
                method: 'GET',
                params: {
                    visitId: 'visits'
                },
                isArray: true
            }
        });
    }
]);*/
