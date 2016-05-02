<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Global List</title>
<link rel="stylesheet" href="/../APIHealthCheck/resources/style.css"
	type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<div id="banner">
			<p id="apiH1">API</p>
			<p id="healthH1">HealthCheck</p>
		</div>
		<nav id="navBar" class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/APIHealthCheck">API <span
						class="glyphicon glyphicon-heart" id="healthHeart"
						aria-hidden="true"></span></a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="/APIHealthCheck">Home</a></li>
						<li><a href="/APIHealthCheck/globalList">Global List</a></li>
						<li><a id="personalListLink">Personal List</a></li>
						<li><a href="/APIHealthCheck/about/">About</a></li>
						<li><a href="/APIHealthCheck/contact/">Contact Us</a></li>
					</ul>
					<ul class="nav navbar-nav pull-right">
						<li class="navLogin"><a id="loginLink"
							href="/APIHealthCheck/login/">Login</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</header>
	<section id="mainElement">
		<div class="container">
			<div class="row" id="indexIntro">
				<div class="col-xs-4">
					<h2>Check Out The Global List</h2>
					<button id="btnToGlobal" class="btn btn-primary" onclick="location.href='/APIHealthCheck/globalList';">Global List</button>
				</div>
				<div class="col-xs-5">
					<h2>Why Use APIHealthCheck?</h2>
					<p>The API HealthCheck project began as a simple attempt to make it easier
					to monitor and view APIs being used. Now it has grown into a platform for
					all software developers to use. It allows its users to keep tabs on any APIs
					they're using or are interested in. It provides valuable data to its users about
					APIs in order to help form a picture about how reliable and useful they really
					are. But we're not just trying to find faults in others work. We hope that those
					making public APIs will upload them into the global list where everyone can see
					just how good they can be. Of course anyone can upload to the global list and help
					the community to grow. So why use APIHealthCheck? 'cos it's freakin' badass.</p>
				</div>
				<div class="col-xs-3">
					<h2>Register Here</h2>
					<button id="btnToReg" class="btn btn-primary" onclick="location.href='/APIHealthCheck/login/';">Register</button>
				</div>
			</div>
		</div>
	</section>
</body>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<script src="/../APIHealthCheck/resources/js/jquery.md5.js"></script>
<script>
$(document)
.ready(
		function() {
			var personalListLink = $('#personalListLink');
			var name = "loggedUser";
			var c = getCookie(name);
			if (c.length > 0) {
				c = JSON.parse(c);
				personalListLink.attr("href",
						"/APIHealthCheck/personal/" + c.id);
				var btn = $('#loginLink');
				btn.attr("href", "");
				btn.empty();
				btn.text("Logout " + c.fname + "?");

				btn
						.click(function() {
							var result = confirm("Are you sure you want to logout?");
							if (result) {
								document.cookie = "loggedUser=; expires=Thu, 01 Jan 1970 00:00:01 GMT;path=/";
								location.reload(true);
							}
						})
			} else {
				personalListLink.attr("href",
						"/APIHealthCheck/login/");
			}

			function getCookie(cname) {
				var name = cname + "=";
				var ca = document.cookie.split(';');
				for (var i = 0; i < ca.length; i++) {
					var c = ca[i].trim();
					if (c.indexOf(name) == 0)
						return c.substring(name.length, c.length);
				}
				return "";
			}

		});
</script>
</html>