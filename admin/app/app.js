'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'ngCookies',
  'myApp.visit',
  'myApp.visits',
  'myApp.songs',
  'myApp.songAdd',
  'myApp.songEdit',
  'myApp.search',
  'myApp.searches',
  'myApp.remark',
  'myApp.remarks',
  'myApp.suggestion',
  'myApp.suggestions',
  'myApp.items',
  'myApp.itemAdd',
  'myApp.itemEdit',
  'myApp.login',
  'myApp.error',
  'myApp.version'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/visits'});
}])
.controller('HeaderCtrl', function($scope, $location, $rootScope, Auth) {
    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.logout = function() {
        console.log("Logging out " + $rootScope.username);
        Auth.clearCredentials();
    };
})

.config(['$httpProvider',
    function($httpProvider) {
        $httpProvider.interceptors.push('errorHttpInterceptor');
    }
])

.factory('Auth', ['Base64', '$cookieStore', '$http', '$location', '$rootScope', 
    function(Base64, $cookieStore, $http, $location, $rootScope) {
        // initialize to whatever is in the cookie, if anything
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $cookieStore.get('authdata');

        return {
            setCredentials: function(username, password) {
                var encoded = Base64.encode(username + ':' + password);
                console.log("Settings credentials...");
                $http.defaults.headers.common.Authorization = 'Basic ' + encoded;
                $cookieStore.put('authdata', encoded);
                $cookieStore.put('user', username);
                $location.path('/visits');
                $rootScope.username = username;
            },
            clearCredentials: function() {
                document.execCommand("ClearAuthenticationCache");
                console.log("Clearing credentials...");
                $cookieStore.remove('authdata');
                $cookieStore.remove('user');
                $http.defaults.headers.common.Authorization = 'Basic ';
                $rootScope.username = null;
            }
        };
    }
])

.factory('Base64', function() {
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
})

.factory('ErrorService', function() {
    return {
        errorMessage: null,
        setError: function(msg) {
            console.log("Setting error " + msg);
            this.errorMessage = msg;
        },
        clear: function() {
            this.errorMessage = null;
        }
    };
})

// register the interceptor as a service
.factory('errorHttpInterceptor', function($q, $location, $cookieStore, ErrorService) {
    return {
        // optional method
        'responseError': function(rejection) {
            // do something on error
            console.log("Response error is " + rejection.status);
            console.log("Cookie aanwezig? " + $cookieStore.get('authdata'));
            if (rejection.status == 401) {
                if ($cookieStore.get('authdata')) {
                    ErrorService.setError('Code: ' + rejection.status + ' Please provide the correct username/password');
                } else {
                    ErrorService.setError('Code: ' + rejection.status + ' Please login');
                }
                $location.path('/login');
            } else {
                ErrorService.setError(rejection.status);
                $location.path('/error')
            }
            return $q.reject(rejection);
        }
    };
})

.directive('formAutofillFix', function() {
  return function(scope, elem, attrs) {
    // Fixes Chrome bug: https://groups.google.com/forum/#!topic/angular/6NlucSskQjY
    elem.prop('method', 'POST');

    // Fix autofill issues where Angular doesn't know about autofilled inputs
    if(attrs.ngSubmit) {
      setTimeout(function() {
        elem.unbind('submit').bind('submit', function(e) {
          e.preventDefault();
          elem.find('input, textarea, select').trigger('input').trigger('change').trigger('keydown');
          scope.$apply(attrs.ngSubmit);
        });
      }, 0);
    }
  };
});;
