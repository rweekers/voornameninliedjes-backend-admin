'use strict';

describe('myApp.songs module', function() {

  beforeEach(module('myApp.songs'));

  describe('songs controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var view1Ctrl = $controller('SongsCtrl');
      expect(view1Ctrl).toBeDefined();
    }));

  });
});