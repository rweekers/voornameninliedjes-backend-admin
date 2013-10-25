$(function() {
    var viewModel = {};
    viewModel.songs = ko.observableArray();
    ko.applyBindings(viewModel);
    var url = 'http://localhost:8080/voornaaminliedje/api/songs/some';
    var parameters = '?offset=99&max=10';
    

    $.getJSON(url + parameters).then(function(songs) {
        $.each(songs, function() {
            viewModel.songs.push({
                artist: ko.observable(this.artist),
                title: ko.observable(this.title)
            });
        });
    });
});