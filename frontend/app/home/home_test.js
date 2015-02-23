'use strict';

describe('myApp.home module', function() {

  beforeEach(module('myApp.home'));

  describe('home controller', function(){

    it('should ....', inject(function($controller) {
      // var scope = {},
      //   ctrl = $controller('HomeCtrl', {$scope:scope});

      //spec body
      var Data = {};
      var Item = {
      	query : function() {return null}
      }
      var $scope = {};
      var homeCtrl = $controller('HomeCtrl', {Data: Data, Item: Item, $scope: $scope});
      expect(homeCtrl).toBeDefined();
    }));

  });
});