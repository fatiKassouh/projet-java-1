<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<title>Gestion des taches</title>
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
				<a href="https://www.javaguides.net" class="navbar-brand">
					Application de gstion de tache </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">liste des taches</a></li>
			</ul>
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
					<h2>
						<c:if test="${user != null}">
            			Edit User
            		</c:if>
						<c:if test="${user == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Titre de la tachee</label> <input type="text"
						value="<c:out value='${user.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out value='${user.description}' />" class="form-control"
						name="description">
				</fieldset>

				<fieldset class="form-group">
					<label>Statut de la tache</label> <input type="text"
						value="<c:out value='${user.status}' />" class="form-control"
						name="status">
				</fieldset>


				<fieldset class="form-group">
					<label>Date limite</label> <input type="date"
						value="<c:out value='${user.date_limite}' />" class="form-control"
						name="date_limite">
				</fieldset>
				<fieldset class="form-group">
					<label>Catégorie</label> <select name="categorie_id"
						class="form-control">
						<c:forEach var="category" items="${categories}">
							<option value='${category.id}'>${category.name}</option>
						</c:forEach>

					</select>
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
