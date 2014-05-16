'use strict';

/* Directives */


var songDirectives = angular.module('songDirectives', []);

songDirectives.directive('alertBar', ['$parse',
    function($parse) {
        return {
            restrict: 'A',
            template: '<div class="alert alert-error alert-bar"' + 'ng-show="errorMessage">' + '<button type="button" class="close" ng-click="hideAlert()">' + 'x</button>' + '{{errorMessage}}</div>',

            link: function(scope, elem, attrs) {
                var alertMessageAttr =
                    attrs['alertMessage'];
                scope.errorMessage = null;

                scope.$watch(alertMessageAttr, function(newVal) {
                    scope.errorMessage = newVal;
                });
                scope.hideAlert = function() {
                    scope.errorMessage = null;
                    $parse(alertMessageAttr).assign(scope, null);
                };
            }
        };
    }
]);
