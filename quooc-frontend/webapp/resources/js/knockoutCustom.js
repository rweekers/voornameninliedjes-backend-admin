function TasksViewModel() {
	var self = this;
	self.tasksURI = 'http://localhost:8080/voornaaminliedje/api.song/find/bert';
	self.songs = ko.observableArray();

	self.ajax = function(uri, method, data) {
		var request = {
			url : uri,
			type : method,
			contentType : "application/json",
			accepts : "application/json",
			cache : false,
			dataType : 'json',
			data : JSON.stringify(data),
		};
		return $.ajax(request);
	}

	$.getJSON('http://localhost:8080/voornaaminliedje/api/song/find/maria',
			function(data) {
				for (var i = 0; i < data.length; i++) {
					self.songs.push({
						artist : ko.observable(data[i].artist),
						title : ko.observable(data[i].title)
					});
				}
			});
}
ko.applyBindings(new TasksViewModel(), $('#main')[0]);
