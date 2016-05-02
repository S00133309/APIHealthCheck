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
						<li><a href="/APIHealthCheck">Home</a></li>
						<li><a href="/APIHealthCheck/globalList">Global List</a></li>
						<li><a id="personalListLink">Personal List</a></li>
						<li><a href="/APIHealthCheck/about/">About</a></li>
						<li class="active"><a href="/APIHealthCheck/contact/">Contact
								Us</a></li>
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
			<div class="row">
				<form id="logForm" class="col-xs-10 col-xs-offset-1"
					action="/APIHealthCheck/contact/" method="post">
					<h2 class="form-signin-heading">Contact Us</h2>
					<div>
						<div class="row">
							<label for="contactEmail" class="col-xs-3 regInputs">Your
								Email Address </label>
							<div class="col-xs-6">
								<input type="email" id="contactEmail" class="form-control"
									placeholder="Email address" required autofocus>
							</div>
						</div>
						<div class="row">
							<label for="contactBody" class="regInputs col-xs-12">What
								Do You Want To Tell Us </label>
							<div class="col-xs-12">
								<textarea id="contactBody" class="form-control"
									placeholder="What Do You Want To Tell Us" required></textarea>
							</div>
						</div>
						<div class="row" id="submitContact">
							<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
						</div>
					</div>
				</form>
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