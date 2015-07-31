'use strict';

describe('myApp.songtest module', function() {

  beforeEach(module('myApp.songtest'));

  describe('songtest controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var songCtrl = $controller('SongtestCtrl');
      expect(songtestCtrl).toBeDefined();
    }));

  });
});