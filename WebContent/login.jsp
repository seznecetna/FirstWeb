<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/style_general.css">
</head>
<body>
	<form action="LoginController" method="POST">
		<label style="">Entrez votre nom </label> <input type = "text" value="" name="nomUser"><br/>
		<input type="submit" value="Connection" name="connect"><br/>
	</form>
	<br/><br/>
</body>
</html>