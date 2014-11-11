'use strict';

angular.module('myApp.searches', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/searches', {
    templateUrl: 'searches/searches.html',
    controller: 'SearchesCtrl'
  });
}])

.controller('SearchesCtrl', [function() {

}]);