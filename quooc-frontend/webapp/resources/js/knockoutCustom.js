/* ko.applyBindings({
	songs : [ {
		artist : 'Bert',
		title : 'Bertington'
	}, {
		artist : 'Charles',
		title : 'Charlesforth'
	}, {
		artist : 'Denise',
		title : 'Dentiste'
	} ]
});*/

/*
ko
		.applyBindings({
			songs : $
					.getJSON('http://localhost:8080/voornaaminliedje/api/song/find/bert')
		});*/

$(function() {
	$.getJSON('http://localhost:8080/voornaaminliedje/api/song/find/bert', function(result) {

		function viewModel() {
			return ko.mapping.fromJS(result);
		}
		;
	    
		ko.applyBindings(new viewModel());
		
	}).error(function() {
		alert("error");
	});
});