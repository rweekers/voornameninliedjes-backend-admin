<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<head>
<meta charset="utf-8">
<title>Voornaam in liedje</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<meta name="description" content="Welcome to my basic template.">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" />
</head>

<body>
	<div id="wrapper">
		<header>
			<h1>Welkom bij de site met voornamen. </h1>

			<nav>
				<ul>
					<li><a rel="external" href="#">Home</a></li>
					<li><a rel="external" href="#">Over ons</a></li>
					<li><a rel="external" href="#">Contact</a></li>
				</ul>
			</nav>
		</header>

		<h2>Random Song Generator</h2>
		<input type="submit" id="randomSong" value="Get Random Song" /><br />
		<br />
		<div id="songResponse"></div>

		<div id="core" class="clearfix">
			<section id="left">
				<p>some content here.</p>
			</section>

			<section id="right">
				<p>but some here as well!</p>
			</section>
		</div>

		<footer>
			<p>Some copyright and legal notices here. Maybe use the � symbol
				a bit.</p>
		</footer>
	</div>

	<script type="text/javascript">
	
		$(document).ready(function() {
			
			// Random Song AJAX Request
			$('#randomSong').click(function() {
				$.getJSON('${pageContext.request.contextPath}/api/song/random', function(song) {
					$('#songResponse').text(song.title + ', artist ' + song.artist);
				});
			});
			
		});
		
	
	</script>

</body>
</html>
