'use strict';

describe('myApp.song module', function() {

  beforeEach(module('myApp.song'));

  describe('song controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var songCtrl = $controller('SongCtrl');
      expect(songCtrl).toBeDefined();
    }));

  });
});