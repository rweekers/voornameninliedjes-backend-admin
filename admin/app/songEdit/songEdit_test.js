'use strict';

describe('myApp.songs module', function() {

  beforeEach(module('myApp.songs'));

  describe('songs controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var songEditCtrl = $controller('SongEditCtrl');
      expect(songEditCtrl).toBeDefined();
    }));

  });
});