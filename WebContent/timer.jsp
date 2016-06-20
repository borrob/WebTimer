<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<script type="text/javascript" src="js/app.js"></script>
		<title>WebTImer</title>
	</head>
	<body onload="update()">
		<h1>WebTimer</h1>
		<p id="countdowntimeparagraph">
			<%
			out.println(Integer.parseInt("" + request.getAttribute("countdown"))/1000);
			%>
		</p>
		<h2>New time</h2>
		<form action="timer" method="post">
			<input type="number" id="newcountdown" name="newcountdowntime" min=3 max=100>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>