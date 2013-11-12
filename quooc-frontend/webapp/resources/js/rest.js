var artist;
var title;

var customTags;

var ipAddress;
var country;
var browser;
var operatingSystem;

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
    // storeVisit();
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
    var geoipUrl = "http://api.hostip.info/get_json.php";

    $.getJSON(geoipUrl, function(data) {
        console.log('Your IP Address is ' + data.ip);
        console.log('Your country is ' + data.country_name);
        console.log('Your city is ' + data.city);
        console.log('Your browser is ' + BrowserDetect.browser);
        ipAddress = data.ip;
    }).fail(function(error) {
        console.log(error);
    });
}

function storeVisit() {
    url = "http://localhost:8080/voornaaminliedje/api/visit/add?ipAddress=" + ipAddress + "&browser=" + browser + "&operatingSystem=" + operatingSystem;

    $.getJSON(url, function() {
        console.log("Storing visit for ip " + ipAddress);
    });
}