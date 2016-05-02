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
			<div class="row">
				<div id="logForm" class="col-xs-3 col-xs-offset-1"
					action="/APIHealthCheck/login/" method="post">
					<h2 class="form-signin-heading">Enter Login Details</h2>
					<input type="email" id="inputEmail"
						class="loginInputs form-control" placeholder="Email address"
						required autofocus> <input type="password"
						id="inputPassword" class="loginInputs form-control"
						placeholder="Password" required>
					<button id="loginBtn" class="btn btn-lg btn-primary btn-block">Login</button>
				</div>

				<div id="regForm" class="col-xs-6 col-xs-offset-1"
					action="/APIHealthCheck/register/" method="post">
					<h2 class="form-signin-heading">Register As New User</h2>
					<div>
						<div>
							<label for="regEmail" class="col-xs-4 regInputs">Email
								Address</label>
							<div class="col-xs-8 regInputs">
								<input type="email" id="regEmail" class="form-control"
									placeholder="Email address" required>
							</div>
						</div>
						<div>
							<label for="regPassword" class="col-xs-4 regInputs">Enter
								Password</label>
							<div class="col-xs-8 regInputs">
								<input type="password" id="regPassword" class="form-control"
									placeholder="Password" required>
							</div>
						</div>
						<div>
							<label for="regFname" class="col-xs-4 regInputs">First
								Name</label>
							<div class="col-xs-8 regInputs">
								<input type="text" id="regFname" class="form-control"
									placeholder="First Name" required>
							</div>
						</div>
						<div>
							<label for="regSname" class="col-xs-4 regInputs">Second
								Name</label>
							<div class="col-xs-8 regInputs">
								<input type="text" id="regSname" class="form-control"
									placeholder="Second Name" required>
							</div>
						</div>
					</div>
					<button id="registerBtn" class="btn btn-lg btn-success btn-block">Register</button>
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

						$('#loginBtn')
								.click(
										function() {
											var email = $('#inputEmail').val();
											var password = $.md5($(
													'#inputPassword').val());
											var person = {};
											person["email"] = email;
											person["password"] = password;
											$
													.ajax({
														type : "POST",
														contentType : "application/json",
														'url' : "/APIHealthCheck/login",
														data : JSON
																.stringify(person),
														dataType : 'json',
														complete : function(
																data) {
															if (data.responseJSON != null) {
																var data = data.responseJSON;
																var user = {};
																user["fname"] = data.fname;
																user["sname"] = data.sname;
																user["email"] = data.email;
																user["id"] = data.id;
																document.cookie = "loggedUser="
																		+ JSON
																				.stringify(user)
																		+ ";path=/";
																location
																		.reload(true);
															} else {
																alert("Sorry, your username or password was incorrect.")
															}
														}
													});

										});

						$('#registerBtn')
								.click(
										function() {
											if ($('#regEmail').val().length > 0
													&& $('#regPassword').val().length > 0
													&& $('#regFname').val().length > 0
													&& $('#regSname').val().length > 0) {
												var person = {};
												person['email'] = $('#regEmail')
														.val();
												person['password'] = $.md5($(
														'#regPassword').val());
												person['fname'] = $('#regFname')
														.val();
												person['sname'] = $('#regSname')
														.val();

												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															'url' : "/APIHealthCheck/register",
															data : JSON
																	.stringify(person),
															dataType : 'json',
															complete : function(
																	data) {
																if (data.responseJSON != null) {
																	var data = data.responseJSON;
																	var user = {};
																	user["fname"] = data.fname;
																	user["sname"] = data.sname;
																	user["email"] = data.email;
																	document.cookie = "loggedUser="
																			+ JSON
																					.stringify(user)
																			+ ";path=/";
																	location
																			.reload(true);
																} else {
																	alert("Sorry, something went wrong.")
																}
															}
														});
											} else {
												alert("Please fill in all the fields to register");
											}
										});
					});
</script>
</html>