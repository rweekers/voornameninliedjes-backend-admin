var artist;
var title;

var customTags;

$(document).ready(function() {

    // Random Song AJAX Request
    $('#randomSong').click(function() {
        $.getJSON('http://localhost:8080/voornaaminliedje/api/song/random', function(song) {
            $('#songResponse').text(song.artist + ' - ' + song.title);
            artist = song.artist;
            title = song.title;
            customTags = artist + " " + title;
        }).fail(function() {
            console.log(error);
        });
    });

    logVisit();
});

$(function() {
    $.getJSON('http://localhost:8080/voornaaminliedje/api/song/random', function(song) {
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
        // tags: tags,
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

function logVisit() {

    console.log("logging visit...");
    // function getip(json) {

    // var geoipUrl = "http://jsonip.appspot.com/?callback=getip";
    // var geoipUrl = "http://smart-ip.net/geoip-json";
    var geoipUrl = "http://api.hostip.info/get_html.php";

    //Get the JSON Code
    /*$.getJSON(geoipUrl, {},
     function(data) {
     //JS Goodness here! Your ip variable is data.ip
     alert('Your IP Address is ' + data.ip)
     });*/

    $.getJSON(geoipUrl, function(data) {
        alert('Your IP Address is ' + data.ip)
    }).fail(function(error) {
        console.log(error);
    });

    // } json.ip
    // <script type="application/javascript" src="http://jsonip.appspot.com/?callback=getip"> </script>

}