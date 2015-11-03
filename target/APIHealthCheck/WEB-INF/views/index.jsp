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
			<th>Email</th>
			<th>Address</th>
			<th>Telephone</th>
		</tr>
		<c:forEach var="contact" items="${listContact}" varStatus="status">
			<tr>
				<!-- 	<td>${status.index}</td>  -->
				<td>${contact.id}</td>
				<td>${contact.name}</td>
				<td>${contact.email}</td>
				<td>${contact.address}</td>
				<td>${contact.telephone}</td>
			</tr>
		</c:forEach>
	</table>

	<button id="testDeleteButton">Delete</button>
	<label id="testLabel"></label>
	<input type="text" id="testInput" />
	</br>
	</br>
	<button id="testEditButton">Edit</button>
	<label id="testLabel2"></label>
	<input id="idInput" placeholder="id"/>
	<input id="nameInput2" placeholder="name"/>
	<input id="emailInput2" placeholder="email"/>
	<input id="addInput2" placeholder="address"/>
	<input id="teleInput2" placeholder="telephone"/>
	</br>
	</br>
	<button id="testCreateButton">New</button>
	<label id="testLabel3"></label>
	<input id="nameInput" placeholder="name"/>
	<input id="emailInput" placeholder="email"/>
	<input id="addInput" placeholder="address"/>
	<input id="teleInput" placeholder="telephone"/>
</body>
<script>
	$(document).ready(function() {
		$('#testDeleteButton').click(function() {
			var id = $("#testInput").val();
			$.ajax({
				type : "DELETE",
				contentType : "application/json",
				'url' : "/APIHealthCheck/deleteContact/" + id,
				data : JSON.stringify(id),
				dataType : 'json',
				complete : function(data) {
					if (data.responseJSON == id)
						console.log("SUCCESS: ", data.responseJSON);
					else
						console.log("ERROR: ", data.responseJSON);
				}
			});
		});

		$('#testEditButton').click(function() {
			
			var contact = {}
			contact["id"] = $('#idInput').val();
			contact["name"] = $('#nameInput2').val();
			contact["email"] = $('#emailInput2').val();
			contact["address"] = $('#addInput2').val();
			contact["telephone"] = $('#teleInput2').val();
			$.ajax({
				type : "PUT",
				contentType : "application/json",
				'url' : "/APIHealthCheck/editContact",
				data : JSON.stringify(contact),
				dataType : 'json',
				complete : function(data) {
					if (data.responseJSON != null)
						console.log("SUCCESS: ", data.responseJSON);
					else
						console.log("ERROR: ", data.responseJSON);
				}
			});
		});

		$('#testCreateButton').click(function() {
			var contact = {}
			contact["name"] = $('#nameInput').val();
			contact["email"] = $('#emailInput').val();
			contact["address"] = $('#addInput').val();
			contact["telephone"] = $('#teleInput').val();
			$.ajax({
				type : "POST",
				contentType : "application/json",
				'url' : "/APIHealthCheck/saveContact",
				data : JSON.stringify(contact),
				dataType : 'json',
				complete : function(data) {
					if (data.responseJSON != null)
						console.log("SUCCESS: ", data.responseJSON);
					else
						console.log("ERROR: ", data.responseJSON);
				}
			});
		});
	});
</script>
</html>