$(function() {
    var viewModel = {};
    viewModel.songs = ko.observableArray();
    ko.applyBindings(viewModel);

    $.getJSON('http://localhost:8080/voornaaminliedje/api/songs/all').then(function(songs) {
        $.each(songs, function() {
            viewModel.songs.push({
                artist: ko.observable(this.artist),
                title: ko.observable(this.title)
            });
        });
    });
});