var artist;
var title;

var customTags;

$(document).ready(function() {

	// Random Song AJAX Request
	$('#randomSong').click(function() {
		console.log("Starting function randomSong from button.");
		$.getJSON('http://localhost:8080/api/song/random', function(song) {
			console.log("Gotten song " + song.artist);
			$('#songResponse').text(song.artist + ' - ' + song.title);
			artist = song.artist;
			title = song.title;
			customTags = artist + " " + title;
			// callFlickr(customTags);
		}).fail(function() {
			// $('#songResponse').text(song.artist + ', title ' + song.title);
			// console.log( "error" + song );
			console.log("niet goed");
		});
	});
});

$(function() {
	console.log("Starting function randomSong from page load.");
	$.getJSON('http://localhost:8080/api/song/random', function(song) {
		console.log("Gotten song " + song.artist);
		$('#songResponse').text(song.artist + ' - ' + song.title);
		artist = song.artist;
		title = song.title;
		customTags = artist + " " + title;
		console.log("De artist en title variabelen zijn " + artist + " "
				+ title);
		callFlickr(customTags);
	});
});

function callFlickr(tags) {
	var flickerAPI = "http://api.flickr.com/services/feeds/photos_public.gne?jsoncallback=?";
	console.log("Calling flickr api with tags: " + tags);
	$.getJSON(flickerAPI, {
		tags : "music guitar",
		// tags: tags,
		tagmode : "any",
		format : "json"
	}).done(function(data) {
		$.each(data.items, function(i, item) {
			$("<img>").attr("src", item.media.m).appendTo("#images");
			if (i === 0) {
				return false;
			}
		});
	});
}
