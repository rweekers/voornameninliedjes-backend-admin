var artist;
var title;

var customTags;

var ipAddress;
var country;
var browser;
var operatingSystem;

$(document).ready(function() {

    // Random Song AJAX Request
    /*
    $('#randomSong').click(function() {
        $.getJSON('http://localhost:8080/voornaaminliedje/api/song/all', function(song) {
            $('#songResponse').text(song.artist + ' - ' + song.title);
            artist = song.artist;
            title = song.title;
            customTags = artist + " " + title;
        }).fail(function() {
            console.log(error);
        });
    });
    */

    // logVisit();
    // google.load("swfobject", "2.1");
    // _run();
});

/*
$(function() {
    $.getJSON('http://localhost:8080/voornaaminliedje/api/song/all', function(song) {
        $('#songResponse').text(song.artist + ' - ' + song.title);
        artist = song.artist;
        title = song.title;
        customTags = artist + " " + title;
        callFlickr(customTags);
    });
});

function callFlickr(tags) {
    var flickerAPI = "http://api.flickr.com/services/feeds/photos_public.gne?jsoncallback=?";
    $.getJSON(flickerAPI, {
        tags: "music guitar",
        tagmode: "any",
        format: "json"
    }).done(function(data) {
        $.each(data.items, function(i, item) {
            $("<img>").attr("src", item.media.m).appendTo("#images");
            if (i === 0) {
                return false;
            }
        });
    });
}
*/
/*
function _run() {
        /*
        * Simple player embed
        */
// console.debug("Songofthe day is " + songOfTheDay.title);
// The video to load.
/*        var videoID = "uq-gYOrU8bA"
        // Lets Flash from another domain call JavaScript
        var params = { allowScriptAccess: "always" };
        // The element id of the Flash embed
        var atts = { id: "ytPlayer" };
        // All of the magic handled by SWFObject (http://code.google.com/p/swfobject/)
        swfobject.embedSWF("http://www.youtube.com/v/" + videoID + "?version=3&enablejsapi=1&playerapiid=player1", 
                           "videoDiv", "380", "214", "9", null, null, params, atts);
        
        
      }*/
// google.setOnLoadCallback(_run);*/
