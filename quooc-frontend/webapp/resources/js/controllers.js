'use strict';

/* Controllers */

var songApp = angular.module('songApp', []);
var songOTD;

songApp.controller('SongListCtrl', function($scope, $http) {
    $http.get('http://localhost:8080/voornaaminliedje/api/songs/all').success(function(data) {
        $scope.songs = data.splice(0, 10);
    });

    $http.get('http://localhost:8080/voornaaminliedje/api/song/al').success(function(data) {
        $scope.songOfTheDay = data;
        run($scope.songOfTheDay);
    });

    logVisit();

    $scope.orderProp = 'artist';
});

function run(songOTD) {
    /*
     * Simple player embed
     */
    console.debug("Songofthe day is " + songOTD.youtube);
    // The video to load.
    // var videoID = "uq-gYOrU8bA"
    var videoID = songOTD.youtube;
    // Lets Flash from another domain call JavaScript
    var params = {
        allowScriptAccess: "always"
    };
    // The element id of the Flash embed
    var atts = {
        id: "ytPlayer"
    };
    // All of the magic handled by SWFObject (http://code.google.com/p/swfobject/)
    swfobject.embedSWF("http://www.youtube.com/v/" + videoID + "?version=3&enablejsapi=1&playerapiid=player1",
        "videoDiv", "380", "214", "9", null, null, params, atts);


}
