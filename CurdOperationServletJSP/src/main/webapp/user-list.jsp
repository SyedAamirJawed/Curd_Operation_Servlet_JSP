<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">

			<div>
				<h2>Management App&nbsp;&nbsp;|&nbsp;&nbsp;</h2>
			</div>

			<div>
				<a href="<%=request.getContextPath()%>/new"
					class="btn btn-success ml-auto">Add New User</a>
			</div>
		</nav>
	</header>



	<br>

	<div class="row">
		

		<div class="container">
			<h1 class="text-center">List of Users</h1>
			<hr>
            <br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Phone</th>
						<th>City</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.phone}" /></td>
							<td><c:out value="${user.city}" /></td>
							<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a> 
								   &nbsp;&nbsp;&nbsp;&nbsp; 
							    <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
							    
							 </td>
						</tr>
					</c:forEach>
					
				</tbody>

			</table>
		</div>
	</div>
</body>

</html>
