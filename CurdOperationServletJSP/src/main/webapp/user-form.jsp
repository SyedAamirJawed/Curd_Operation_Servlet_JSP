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


<header>

	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: tomato">

		<div>
			<h2>Management App&nbsp;&nbsp;|&nbsp;&nbsp;</h2>
		</div>



		<div>
			<a href="<%=request.getContextPath()%>/list" class="btn btn-success"> User List </a>
		</div>

	</nav>
</header>
<br>
<div class="container col-md-5">
	<div class="card">
		<div class="card-body">
			  <c:if test="${user != null}">
	     <form action="update" method="post">
			      </c:if>
			      <c:if test="${user == null}">
	     <form action="insert" method="post">
			    </c:if>

			<caption>
				<h2 style="color:yellow" align="center">
					<c:if test="${user != null}"> Edit User </c:if>
					<c:if test="${user == null}"> Add New User </c:if>
				</h2>
			</caption>

			<c:if test="${user != null}">
				<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
			</c:if>

			<fieldset class="form-group">
				<label>User Name</label> <input type="text"
					value="<c:out value='${user.name}' />" class="form-control"
					name="name-jsp" required="required">
			</fieldset>

			<fieldset class="form-group">
				<label>User Email</label> <input type="text"
					value="<c:out value='${user.email}' />" class="form-control"
					name="email-jsp">
			</fieldset>

			<fieldset class="form-group">
				<label>User Phone</label> <input type="text"
					value="<c:out value='${user.phone}' />" class="form-control"
					name="phone-jsp">
			</fieldset>

			<fieldset class="form-group">
				<label>User City</label> <input type="text"
					value="<c:out value='${user.city}' />" class="form-control"
					name="city-jsp">
			</fieldset>

          <div style="text-align: center;">
             <button type="submit" class="btn btn-success">Save</button>
          </div>
			</form>
		</div>
	</div>
</div>
</body>

</html>
