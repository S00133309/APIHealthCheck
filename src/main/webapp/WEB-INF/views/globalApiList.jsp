<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Global List</title>
<link rel="stylesheet" href="resources/style.css" type="text/css">
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
						<li class="active"><a href="/APIHealthCheck/globalList">Global List</a></li>
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
				<table id="apiTable" class="table col-xs-7">
					<thead>
						<tr>
							<td>Name</td>
							<td>Current Status</td>
							<td class="pull-right">
								<div class="row">
									<button id="btnModalTrigger" class="btn btn-success col-xs-2"
										data-toggle="modal" data-target="#addModal">
										<span class="glyphicon glyphicon-plus"></span>
									</button>
									<!-- Modal -->
									<div id="addModal" class="modal fade" role="dialog">
										<div class="modal-dialog">

											<!-- Modal content-->
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">Add New API</h4>
												</div>
												<div class="modal-body">
													<div>
														<form action="" method="post">
															<div class="row">
																<label for="apiName" class="col-xs-4">API Name</label>
																<div class="col-xs-8">
																	<input id="apiName" type="text" class="form-control"
																		placeholder="API Name" required>
																</div>
															</div>
															<div class="row">
																<label for="apiTime" class="col-xs-4">API Test
																	Time</label>
																<div class="col-xs-8">
																	<select id="apiTime" class="form-control">
																		<option value="1">1 AM</option>
																		<option value="2">2 AM</option>
																		<option value="3">3 AM</option>
																		<option value="4">4 AM</option>
																		<option value="5">5 AM</option>
																		<option value="6">6 AM</option>
																		<option value="7">7 AM</option>
																		<option value="8">8 AM</option>
																		<option value="9">9 AM</option>
																		<option value="10">10 AM</option>
																		<option value="11">11 AM</option>
																		<option value="12">12 AM</option>
																		<option value="13">1 PM</option>
																		<option value="14">2 PM</option>
																		<option value="15">3 PM</option>
																		<option value="16">4 PM</option>
																		<option value="17">5 PM</option>
																		<option value="18">6 PM</option>
																		<option value="19">7 PM</option>
																		<option value="20">8 PM</option>
																		<option value="21">9 PM</option>
																		<option value="22">10 PM</option>
																		<option value="23">11 PM</option>
																		<option value="24">12 PM</option>
																	</select>
																</div>
															</div>
															<div class="row">
																<table id="urlAddTable">
																	<thead>
																		<td>URLs</td>
																		<td></td>
																	</thead>
																	<tbody id="tableAddUrlsBody">
																		<tr>
																			<td><input id=urlToAdd type="text"
																				class="form-control" placeholder="URL" required></td>
																			<td><button id="btnAddUrl" type="button"
																					class="btn btn-success">
																					<span class="glyphicon glyphicon-plus"></span>
																				</button></td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</form>
													</div>
												</div>
												<div class="modal-footer">
													<button id="btnAddApi" type="button"
														class="btn btn-success">Add</button>
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">Close</button>
												</div>
											</div>
										</div>
									</div>
									<!--End Modal -->
									<div class="col-xs-10">
										<input id="searchBox" type="text" class="form-control"
											placeholder="Search">
									</div>
								</div>
							</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="globalViewData" items="${data}">
							<tr id="${globalViewData.id}">
								<td id="${globalViewData.id}Name">${globalViewData.name}</td>
								<td class="api${globalViewData.currentStatus}">${globalViewData.currentStatus}</td>
								<td><span
									class="glyphicon glyphicon-chevron-up pull-right chevrons"></span></td>
							</tr>
							<tr id="${globalViewData.id}Hidden" class="hideRow">
								<td colspan=3>
									<div class="container">
										<div class="row detailRow">
											<p class="col-lg-4 detailsText">Last 10 Results</p>
											<p class="col-lg-3 detailsText tablePieData">%
												Up/Down/Unstable</p>
											<p class="col-lg-3 detailsText">Total Results</p>
										</div>
										<div class="row detailRow">
											<div class="col-lg-4">
												<canvas id="${globalViewData.id}HistoryChart" width="300"
													height="200"></canvas>
											</div>
											<div class="col-lg-3 tablePieData">
												<canvas id="${globalViewData.id}PercentageChart" width="200"
													height="200"></canvas>
											</div>
											<div class="col-lg-3">
												<canvas id="${globalViewData.id}TotalChart" width="200"
													height="200"></canvas>
											</div>
											<div class="col-lg-2">
												<a href="details/${globalViewData.id}"
													data-apiId="${globalViewData.id}"
													class="btn btn-default moreDetails">More Details</a>
											</div>
										</div>

									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
							$('#btnModalTrigger').show();

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
									"/APIHealthCheck/login");
							$('#btnModalTrigger').hide();
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

						var data = new Array();
						var dataObject;
						<c:forEach items="${data}" var="globalViewData" varStatus="status">
						dataObject = new Object();
						dataObject.id = ${globalViewData.id};
						dataObject.name = "${globalViewData.name}";
						dataObject.currentStatus = "${globalViewData.currentStatus}";

						dataObject.lastTenResultStatus = new Array();
						<c:forEach items="${globalViewData.lastTenResultStatus}" var="status">
						var i = "${status}";
						dataObject.lastTenResultStatus.push(i);
						</c:forEach>

						dataObject.lastTenDates = new Array();
						<c:forEach items="${globalViewData.lastTenDates}" var="date">
						var d = new Date("${date}");
						var i = d.getDate() + "-" + (d.getMonth() + 1) + "-"
								+ d.getFullYear();
						dataObject.lastTenDates.push(i);
						</c:forEach>

						dataObject.totalUp = ${globalViewData.totalUp};
						dataObject.totalDown = ${globalViewData.totalDown};
						dataObject.totalUnstable = ${globalViewData.totalUnstable};
						data.push(dataObject);
						</c:forEach>
						$("#apiTable > tbody > tr").mouseover(function() {
							$(this).addClass("hovering");
						});
						$("#apiTable > tbody > tr").mouseout(function() {
							$(this).removeClass("hovering");
						});
						$("#apiTable > tbody > tr")
								.click(
										function() {
											var id = $(this).attr('id');
											if ($(this)
													.find('td:last > span')
													.hasClass(
															"glyphicon-chevron-up")) {
												$(this)
														.find('td:last > span')
														.removeClass(
																"glyphicon-chevron-up")
														.addClass(
																"glyphicon-chevron-down");
												for (var int = 0; int < data.length; int++) {
													if (data[int].id == id) {
														showRow(data[int]);
													}
												}

											} else {
												$(this)
														.find('td:last > span')
														.removeClass(
																"glyphicon-chevron-down")
														.addClass(
																"glyphicon-chevron-up");
												$("#" + id + "Hidden").hide();
											}
										});
					});

	$('#btnAddUrl')
			.click(
					function() {
						var url = $('#urlToAdd').val();
						var tableBody = $('#tableAddUrlsBody');
						var btnEnd = "<button type='button' class='btn btn-danger' onclick='btnRemoveClick(this)'><span class='glyphicon glyphicon-minus'></span></button>"
						tableBody.append("<tr><td>" + url + "</td><td>"
								+ btnEnd + "</td></tr>");

					});
	function btnRemoveClick(callerElement) {
		$(callerElement).parent().parent().remove();
	}

	$('#btnAddApi').click(function() {
		var api = {};
		api["name"] = $('#apiName').val();
		api["time"] = $('#apiTime').find('option:selected').val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			'url' : "/APIHealthCheck/saveApi",
			data : JSON.stringify(api),
			dataType : 'json',
			complete : function(data) {
				if (data.responseJSON != null) {
					api = data.responseJSON;
					var urlCount = 0;
					var rowCount = 0;
					var rows = $("#urlAddTable > tbody > tr");
					for (var int = 1; int < rows.length; int++) {
						rowCount++;
						var url = {}
						var tds = rows[int].children;
						url["url"] = tds[0].innerHTML;
						url["apiId"] = api.id;
						$.ajax({
							type : "POST",
							contentType : "application/json",
							'url' : "/APIHealthCheck/saveURL",
							data : JSON.stringify(url),
							dataType : 'json',
							complete : function(data) {
								if (data.responseJSON != null) {
									urlCount++;
								}
							}
						});
					}
					if (urlCount == rowCount) {
						alert("Succesfully Added API");
						$('#addModal').hide();
					} else {
						alert("Sorry something went wrong when adding urls");
					}
				} else {
					alert("Sorry something went wrong when adding API");
				}
			}
		});
	});

	$('#searchBox').keyup(
			function() {
				var searchPhrase = $('#searchBox').val().toUpperCase();
				var rows = $("#apiTable > tbody > tr:even");
				for (var int = 0; int < rows.length; int++) {
					var name = $('#' + rows[int].getAttribute('id') + 'Name')
							.html().toUpperCase();
					if (name.indexOf(searchPhrase) == -1)
						$('#' + rows[int].getAttribute('id')).hide();
					else
						$('#' + rows[int].getAttribute('id')).show();
				}
			});

	function showRow(data) {
		$("#" + data.id + "Hidden").show();

		var setUp = new Array();
		var setDown = new Array();
		var setUn = new Array();

		for (var int = 0; int < data.lastTenResultStatus.length; int++) {

			if (data.lastTenResultStatus[int] == "Healthy") {
				setUp.push(1);
				setDown.push(0);
				setUn.push(0);
			} else if (data.lastTenResultStatus[int] == "Unstable") {
				setUp.push(0);
				setDown.push(0);
				setUn.push(1);
			} else {
				setUp.push(0);
				setDown.push(1);
				setUn.push(0);
			}
		}

		while (setUp.length < 10) {
			setUp.push(0);
		}
		while (setDown.length < 10) {
			setDown.push(0);
		}
		while (setUn.length < 10) {
			setUn.push(0);
		}
		var lineChartData = {
			labels : [ data.lastTenDates[0], data.lastTenDates[1],
					data.lastTenDates[2], data.lastTenDates[3],
					data.lastTenDates[4], data.lastTenDates[5],
					data.lastTenDates[6], data.lastTenDates[7],
					data.lastTenDates[8], data.lastTenDates[9] ],
			datasets : [
					{

						label : "UnstableTime",
						fillColor : "#FEED01",
						strokeColor : "#FFF",
						pointColor : "#FEED01",
						pointStrokeColor : "#FFF",
						data : [ setUn[0], setUn[1], setUn[2], setUn[3],
								setUn[4], setUn[5], setUn[6], setUn[7],
								setUn[8], setUn[9] ]
					},
					{
						label : "DownTime",
						fillColor : "#F7464A",
						strokeColor : "#FFF",
						pointColor : "#F7464A",
						pointStrokeColor : "#FFF",
						data : [ setDown[0], setDown[1], setDown[2],
								setDown[3], setDown[4], setDown[5], setDown[6],
								setDown[7], setDown[8], setDown[9] ]
					},
					{
						label : "UpTime",
						fillColor : "#6ECD55",
						strokeColor : "#FFF",
						pointColor : "#6ECD55",
						pointStrokeColor : "#FFF",
						data : [ setUp[0], setUp[1], setUp[2], setUp[3],
								setUp[4], setUp[5], setUp[6], setUp[7],
								setUp[8], setUp[9] ]
					} ]
		};

		var totalData = {
			labels : [ "Total Results" ],
			datasets : [ {
				label : "UpTime",
				fillColor : "#6ECD55",
				strokeColor : "#FFF",
				highlightFill : "#89d675",
				highlightStroke : "#FFF",
				data : [ data.totalUp ]
			}, {
				label : "DownTime",
				fillColor : "#F7464A",
				strokeColor : "#FFF",
				highlightFill : "#FF5A5E",
				highlightStroke : "#FFF",
				data : [ data.totalDown ]
			}, {
				label : "Unstable",
				fillColor : "#FEED01",
				strokeColor : "#FFF",
				highlightFill : "#FEED01",
				highlightStroke : "#FFF",
				data : [ data.totalUnstable ]
			} ]
		};

		var total = data.totalUnstable + data.totalDown + data.totalUp;
		var perUp = 0;
		var perDown = 0;
		var perUn = 0;
		if (total > 0) {
			var perUp = (data.totalUp / total) * 100;
			var perDown = (data.totalDown / total) * 100;
			var perUn = (data.totalUnstable / total) * 100;
		}
		var pieData = [ {
			value : perDown,
			color : "#F7464A",
			highlight : "#FF5A5E",
			label : "Down % "
		}, {
			value : perUp,
			color : "#6ECD55",
			highlight : "#89d675",
			label : "Up % "
		}, {
			value : perUn,
			color : "#FEED01",
			highlight : "#FEED01",
			label : "Unstable % "
		} ];

		var ctx = $("#" + data.id + "HistoryChart")[0].getContext('2d');
		new Chart(ctx).Line(lineChartData);

		ctx = $("#" + data.id + "PercentageChart")[0].getContext("2d");
		new Chart(ctx).Pie(pieData);

		ctx = $("#" + data.id + "TotalChart")[0].getContext("2d");
		new Chart(ctx).Bar(totalData);
	}
</script>
</html>