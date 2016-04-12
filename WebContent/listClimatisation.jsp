<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des climatisation</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.nomUser}">
			<div>${sessionScope.nomUser} est connecté</div>
			<a href="ClimatisationController"> Ajouter un appareil</a>
			<br />
			<a href="LogOutController"> Se déconnecter</a>
			<br />
			<br />
		</c:when>
		<c:otherwise>
			<a href="LoginController"> Se connecter</a>
			<br />
			<br />
		</c:otherwise>
	</c:choose>
	<fieldset>
		<legend>Liste des climatisations</legend>
		<!-- <table>
			<tr>
				<th>Nom</th>
				<th>Température</th>
				<th>Pression</th>
			</tr> -->
		<p>La list des climatisations : ${listClims}</p>
		

		<c:forEach var="object_clim" items="${listClims}">
			<tr>
				<td>${object_clim.nomAppareil}</td>
				<td>${object_clim.temperature}</td>
				<td>${object_clim.pression}</td>
				<td>${object_clim.datation}</td>
			</tr>
		</c:forEach>
	</fieldset>

	<div>${rechercheToutErreur}</div>

</body>
</html>