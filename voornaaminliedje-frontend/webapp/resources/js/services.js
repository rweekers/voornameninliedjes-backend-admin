 /* Services */

 var songServices = angular.module('songServices', ['ngResource']);

 songServices.factory('Song', ['$resource',
     function($resource) {
         return $resource('http://127.0.0.1:8080/voornaaminliedje/api/song/some', {}, {
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

  songServices.factory('Song2', ['$resource',
     function($resource) {
         return $resource('http://127.0.0.1:8080/voornaaminliedje/api/song/all', {}, {
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
             }
         });
     }
 ]);

  songServices.factory('Visit', ['$resource',
     function($resource) {
         return $resource('http://127.0.0.1:8080/voornaaminliedje/api/visit/all', {}, {
             query: {
                 method: 'GET',
                 params: {
                     page: '',
                     count: ''
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
         return $resource('http://127.0.0.1:8080/voornaaminliedje/api/song/find', {}, {
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
