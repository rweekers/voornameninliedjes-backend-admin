 /* Services */

 var songServices = angular.module('songServices', ['ngResource']);

 songServices.factory('Visit', ['$resource',
     function($resource) {
         return $resource('http://127.0.0.1:8080/voornaaminliedje/api/visit/add', {}, {
             create: {
                 method: 'POST',
                 params: {
                     ipAddress: '',
                     browser: '',
                     operatingSystem: '',
                     city: '',
                     country: ''
                 }
             },
         });
     }
 ]);
