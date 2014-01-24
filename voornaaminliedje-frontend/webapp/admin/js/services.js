'use strict';

/* Services */

var visitServices = angular.module('visitServices', ['ngResource']);

visitServices.factory('Visit', ['$resource',
    function($resource) {
        return $resource('http://localhost:8080/voornaaminliedje/api/visit/all', {}, {
            query: {
                method: 'GET',
                params: {
                    phoneId: 'phones'
                },
                isArray: true
            }
        });
    }
]);
