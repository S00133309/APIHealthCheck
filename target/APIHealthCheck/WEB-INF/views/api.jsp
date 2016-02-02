<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home Page</title>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
</head>
<body>
	<h1>Hello World!</h1>
	<table>
		<tr>
			<!-- <th>Index</th> -->
			<th>ID</th>
			<th>Name</th>
			<th>Time</th>
			<th>Urls</th>
		</tr>
		<c:forEach var="api" items="${listAPI}">
			<tr>
				<!-- 	<td>${status.index}</td>  -->
				<td>${api.id}</td>
				<td>${api.name}</td>
				<td>${api.time}</td>
				<c:forEach var="url" items="${listURL}">
					<c:if test="${url.apiId == api.id }">
						<td>${url.url}</td>
					</c:if>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

	<button id="testDeleteButton">Delete</button>
	<label id="testLabel"></label>
	<input type="text" id="testInput" placeholder="id" />
	</br>
	</br>
	<button id="testEditButton">Edit</button>
	<label id="testLabel2"></label>
	<input id="idInput" placeholder="id" />
	<input id="nameInput2" placeholder="name" />
	</br>
	</br>
	<button id="testCreateButton">New</button>
	<label id="testLabel3"></label>
	<input id="nameInput" placeholder="name" />
</body>
<script>
	$(document).ready(function() {
		$('#testDeleteButton').click(function() {
			var id = $("#testInput").val();
			$.ajax({
				type : "DELETE",
				contentType : "application/json",
				'url' : "/APIHealthCheck/deleteApi/" + id,
				data : JSON.stringify(id),
				dataType : 'json',
				complete : function(data) {
					if (data.responseJSON != null)
						alert("SUCCESS");
					else
						alert("ERROR");
					console.log(data.responseJSON);
				}
			});
		});

		$('#testEditButton').click(function() {

			var api = {}
			api["id"] = $('#idInput').val();
			api["name"] = $('#nameInput2').val();
			$.ajax({
				type : "PUT",
				contentType : "application/json",
				'url' : "/APIHealthCheck/editApi",
				data : JSON.stringify(api),
				dataType : 'json',
				complete : function(data) {
					if (data.responseJSON != null)
						alert("SUCCESS");
					else
						alert("ERROR");
					console.log(data.responseJSON);
				}
			});
		});

		$('#testCreateButton').click(function() {
			var api = {}
			api["name"] = $('#nameInput').val();
			$.ajax({
				type : "POST",
				contentType : "application/json",
				'url' : "/APIHealthCheck/saveApi",
				data : JSON.stringify(api),
				dataType : 'json',
				complete : function(data) {
					if (data.responseJSON != null)
						alert("SUCCESS");
					else
						alert("ERROR");
					console.log(data.responseJSON);
				}
			});
		});
	});
</script>
</html>