'use strict';

describe('myApp.remark module', function() {

  beforeEach(module('myApp.remark'));

  describe('remark controller', function(){

    it('should ....', inject(function($controller) {
      var $scope = {};
      //spec body
      var remarkCtrl = $controller('RemarkCtrl', {$scope: $scope});
      expect(remarkCtrl).toBeDefined();
    }));

  });
});