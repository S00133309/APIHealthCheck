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
			<div class="row detailsContainers">
				<header id="detailsHeader" class="col-lg-10 col-lg-offset-1">
					<h1 id="apiName" class="displayAPIName">${api.name}</h1>
				</header>
			</div>
			<div class="row detailsContainers">
				<div class="col-lg-5">
					<table id=urlTable>
						<thead>
							<td>URLs</td>
							<td></td>
						</thead>
						<tbody>
							<c:forEach var="url" items="${urls}">
								<tr id="${url.id}">
									<td><a href="${url.url}" target="_blank">${url.url}</a></td>
									<td><button data-urlId="${url.id}"
											class="showResults btn btn-default">Show Results</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-lg-7">
					<table id=resultsTable>
						<thead>
							<td>Response Code</td>
							<td>Time Pinged</td>
							<td>Date Pinged</td>
							<td>Notes</td>
						</thead>
						<tbody>
						</tbody>
					</table>
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

						var results = new Array();
						var result;
						<c:forEach items="${results}" var="result">
						result = new Object();
						result.id = ${result.id};
						result.urlId = ${result.urlId};
						result.code = ${result.responseCode};
						result.time = "${result.timePinged}";
						var d = new Date("${result.datePinged}");
						var dFormated = d.getDate() + "-" + (d.getMonth() + 1)
								+ "-" + d.getFullYear();
						result.date = dFormated;
						result.note = "${result.note}";
						results.push(result);
						</c:forEach>

						$(".showResults")
								.click(
										function() {
											$('#resultsTable > tbody > tr')
													.remove();
											var id = $(this).attr('data-urlId');
											for (var int = 0; int < results.length; int++) {

												if (results[int].urlId == id) {
													var classCode = "codeRed";
													if (results[int].code == 200)
														classCode = "codeGreen";
													$('#resultsTable > tbody')
															.append(
																	'<tr><td class='+classCode+'>'
																			+ results[int].code
																			+ '</td><td>'
																			+ results[int].time
																			+ '</td><td>'
																			+ results[int].date
																			+ '</td><td>'
																			+ results[int].note
																			+ '</td></tr>');
												}
											}
										});
					});
</script>
</html>