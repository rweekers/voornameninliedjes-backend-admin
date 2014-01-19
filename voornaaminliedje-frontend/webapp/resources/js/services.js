 /* Services */

 var songServices = angular.module('songServices', ['ngResource']);

 songServices.factory('GeoIP', ['$resource',
     function($resource) {
         return $resource('http://api.hostip.info/get_json.php', {}, {
             query: {
                 method: 'GET',
                 isArray: false
             },
         });
     }
 ]);
