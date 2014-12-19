'use strict';

angular.module('myApp.visit', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/visit/:visitId', {
    templateUrl: 'visit/visit.html',
    controller: 'VisitCtrl'
  });
}])

.controller('VisitCtrl', ['$scope', '$location', '$routeParams', '$cookieStore', 'Visit', 
    function($scope, $location, $routeParams, $cookieStore, Visit) {
        $scope.visit = Visit.get({
            id: $routeParams.visitId
        });
        $("html, body").animate({ scrollTop: 0 }, "slow");
}]);
