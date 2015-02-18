'use strict';

describe('myApp.items module', function() {

  beforeEach(module('myApp.items'));

  describe('itemEdit controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var itemAddCtrl = $controller('ItemAddCtrl');
      expect(itemAddCtrl).toBeDefined();
    }));

  });
});