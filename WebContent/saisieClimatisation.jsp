<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Saisie des informations</title>

<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="Climatisation.js"></script>

</head>
<body>
<c:if test="${not empty sessionScope.nomUser}">
<div>${sessionScope.nomUser} est connecté</div>
<a href="LogoutController">se déconnecter</a><br/>
</c:if>
<c:if test="${empty sessionScope.nomUser}">
<a href="LoginController">se connecter</a><br/>
</c:if>


<a href="ListClimatisation">liste des climatisations</a><br/>
<br/>
<br/>

<form action="ClimatisationController" method="POST">
	<label>nom appareil</label><input id="sourceNbId" type="text" value="${nom}" name="nom"/>
	<span style="color:red">${nomErreur}</span>
	<span id="nbId" style="color:blue"></span>
	<br/>
	
	<label>temperature</label><input type="text" value="${temperature}" name="temperature"/><span style="color:red">${temperatureErreur}</span><br/>
	<label>pression</label><input type="text" value="${pression}" name="pression"/><span style="color:red">${pressureErreur}</span><br/>
	<label>Entrez l'année </label><input type = "text" value="${annee}" name="annee"><span style="color:red">${anneeErreur}</span><br/>
	<input type="submit" value="Valider" name="action"/>
	
</form>


</body>
</html>