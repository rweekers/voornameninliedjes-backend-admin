<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Spring MVC - Ajax</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <style>
    	body { background-color: #eee; font: helvetica; }
    	#container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
    	.green { font-weight: bold; color: green; }
    	.message { margin-bottom: 10px; }
    	label { width:70px; display:inline-block;}
    	.hide { display: none; }
    	.error { color: red; font-size: 0.8em; }
    </style>
  </head>
  <body>
	
	<div id="container">
	
		<h1>Song Page</h1>
		<p>This page demonstrates Spring MVC's powerful Ajax functionality. Retrieve a
		random song, retrieve a song by ID, or save a new song, all without page reload.
		</p>
		
		<h2>Random Song Generator</h2>
		<input type="submit" id="randomSong" value="Get Random Song" /><br/><br/>
		<div id="songResponse"> </div>
		
		<hr/>
		
		<h2>Get By ID</h2>
		<form id="idForm">
			<div class="error hide" id="idError">Please enter a valid ID in range 0-3</div>
			<label for="songId">ID (0-3): </label><input name="id" id="songId" value="0" type="number" />
			<input type="submit" value="Get Song By ID" /> <br /><br/>
			<div id="songIdResponse"> </div>
		</form>
		
		<hr/>
		
		<h2>Submit new Song</h2>
		<form id="newSongForm">
			<label for="nameInput">Name: </label>
			<input type="text" name="name" id="nameInput" />
			<br/>
			
			<label for="ageInput">Age: </label>
			<input type="text" name="age" id="ageInput" />
			<br/>
			<input type="submit" value="Save Song" /><br/><br/>
			<div id="songFormResponse" class="green"> </div>
		</form>
	</div>
	
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			// Random Song AJAX Request
			$('#randomSong').click(function() {
				$.getJSON('${pageContext.request.contextPath}/api/song/random', function(song) {
					$('#songResponse').text(song.name + ', age ' + song.age);
				});
			});
			
			// Request Song by ID AJAX
			$('#idForm').submit(function(e) {
				var songId = +$('#songId').val();
				if(!validateSongId(songId)) 
					return false;
				$.get('${pageContext.request.contextPath}/api/song/' + songId, function(song) {
					$('#songIdResponse').text(song.name + ', age ' + song.age);
				});
				e.preventDefault(); // prevent actual form submit
			});
			
			// Save Song AJAX Form Submit
			$('#randomSong').click(function() {
				$.getJSON('${pageContext.request.contextPath}/api/song/random', function(song) {
					$('#songResponse').text(song.name + ', age ' + song.age);
				});
			});
			
			$('#newSongForm').submit(function(e) {
				// will pass the form date using the jQuery serialize function
				$.post('${pageContext.request.contextPath}/api/song', $(this).serialize(), function(response) {
					$('#songFormResponse').text(response);
				});
				
				e.preventDefault(); // prevent actual form submit and page reload
			});
			
		});
		
		function validateSongId(songId) {
			console.log(songId);
			if(songId === undefined || songId < 0 || songId > 3) {
				$('#idError').show();
				return false;
			}
			else {
				$('#idError').hide();
				return true;
			}
		}
		
	
	</script>
	
  </body>
</html>