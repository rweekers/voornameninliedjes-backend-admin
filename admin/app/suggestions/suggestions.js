'use strict';

angular.module('myApp.suggestions', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/suggestions', {
    templateUrl: 'suggestions/suggestions.html',
    controller: 'SuggestionsCtrl'
  });
}])

.controller('SuggestionsCtrl', [function() {

}]);