'use strict';

describe('myApp.home module', function() {

  beforeEach(module('myApp.home'));

  describe('home controller', function(){

    it('should ....', inject(function($controller) {
      var scope = {},
        ctrl = $controller('HomeCtrl', {$scope:scope});

      //spec body
      // var homeCtrl = $controller('HomeCtrl');
      // expect(homeCtrl).toBeDefined();
      expect(scope).toBeDefined();
    }));

  });
});