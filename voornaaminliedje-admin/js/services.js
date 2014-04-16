'use strict';

/* Services */

var visitServices = angular.module('visitServices', ['ngResource']);
var songServices = angular.module('songServices', ['ngResource']);

visitServices.factory('Login', ['$location',
    function($location) {
        return {
            isLoggedin: function() {
                return true;
            }
        }
    }
]);

songServices.factory('Song', ['$resource',
     function($resource) {
         return $resource('https://admin.voornaaminliedje.dev/voornaaminliedje/api/song/all', {}, {
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

visitServices.factory('Visit', ['Base64', '$http', '$resource',
    function(Base64, $http, $resource) {

        return $resource('https://admin.voornaaminliedje.dev/voornaaminliedje/api/admin/visit/all', {}, {
            query: {
                method: 'GET',
                params: {
                    visitId: 'visits'
                },
                isArray: true
            }
        });
    }
]);

visitServices.factory('VisitDetail', ['Base64', '$http', '$resource',
    function(Base64, $http, $resource) {

        // return $resource('http://www.voornaaminliedje.nl/voornaaminliedje/api/admin/visit/:id', {}, {
        return $resource('https://admin.voornaaminliedje.dev/voornaaminliedje/api/admin/visit/:id', {}, {
            get: {
                method: 'GET',
                params: {
                    id: ''
                }
            }
        });
    }
]);

visitServices.factory('SongDetail', ['Base64', '$http', '$resource',
    function(Base64, $http, $resource) {

        // return $resource('http://www.voornaaminliedje.nl/voornaaminliedje/api/admin/visit/:id', {}, {
        return $resource('https://admin.voornaaminliedje.dev/voornaaminliedje/api/admin/song/:id', {}, {
            get: {
                method: 'GET',
                params: {
                    id: ''
                }
            }
        });
    }
]);

visitServices.factory('Auth', ['Base64', '$cookieStore', '$http', '$location',
    function(Base64, $cookieStore, $http, $location) {
        // initialize to whatever is in the cookie, if anything
        console.log("Zit er al iets in het cookie? " + $cookieStore.get('authdata'));
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $cookieStore.get('authdata');

        return {
            setCredentials: function(username, password) {
                var encoded = Base64.encode(username + ':' + password);
                console.log("Dit is de methode setCredentials.");
                $http.defaults.headers.common.Authorization = 'Basic ' + encoded;
                $cookieStore.put('authdata', encoded);
                $location.path('/visits');
            },
            clearCredentials: function() {
                document.execCommand("ClearAuthenticationCache");
                console.log("Clearing credentials...");
                $cookieStore.remove('authdata');
                $http.defaults.headers.common.Authorization = 'Basic ';
            }
        };
    }
]);

visitServices.factory('Base64', function() {
    var keyStr = 'ABCDEFGHIJKLMNOP' +
        'QRSTUVWXYZabcdef' +
        'ghijklmnopqrstuv' +
        'wxyz0123456789+/' +
        '=';
    return {
        encode: function(input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);

            return output;
        },

        decode: function(input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";

            } while (i < input.length);

            return output;
        }
    };
});

visitServices.factory('errorService', function() {
    return {
        errorMessage: null,
        setError: function(msg) {
            this.errorMessage = msg;
        },
        clear: function() {
            this.errorMessage = null;
        }
    };
});

// register the interceptor as a service
visitServices.factory('errorHttpInterceptor', ['$q', '$location',
    function($q, $location) {
        return {

            // optional method
            'response': function(response) {
                // do something on success
                console.log("Interceptor met succes.");
                return response || $q.when(response);
            },

            // optional method
            'responseError': function(rejection) {
                // do something on error
                console.log("Interceptor mislukt " + rejection.status);
                if (rejection.status == 401) {
                    $location.path('/login');
                }
                /*
            if (canRecover(rejection)) {
                return responseOrNewPromise
            }*/
                return $q.reject(rejection);
            }
        };
    }
]);
