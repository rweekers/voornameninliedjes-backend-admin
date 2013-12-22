 /* Services */

 var songServices = angular.module('songServices', ['ngResource']);

 songServices.factory('Song', ['$resource',
     function($resource) {
         return $resource('http://127.0.0.1:8080/voornaaminliedje/api/songs/some', {}, {
             query: {
                 method: 'GET',
                 params: {
                     offset: '',
                     max: ''
                 },
                 isArray: true
             }
         });
     }
 ]);

 songServices.factory('SongOfTheDay', ['$resource',
     function($resource) {
         return $resource('http://127.0.0.1:8080/voornaaminliedje/api/song/al', {}, {
             query: {
                 method: 'GET',
                 isArray: false
             }
         });
     }
 ]);

 songServices.factory('FindSongs', ['$resource',
     function($resource) {
         return $resource('http://127.0.0.1:8080/api/songs/find', {}, {
             query: {
                 method: 'GET',
                 params: {
                     firstname: ''
                 },
                 isArray: true
             }
         });
     }
 ]);
