'use strict';

describe('myApp.home module', function() {

  beforeEach(module('myApp.home'));

  describe('home controller', function(){

    it('should ....', inject(function($controller) {
      // var scope = {},
      //   ctrl = $controller('HomeCtrl', {$scope:scope});

      //spec body
      var Date = {};
      var homeCtrl = $controller('HomeCtrl', {Data: Data});
      expect(homeCtrl).toBeDefined();
      expect(scope).toBeDefined();
    }));

  });
});