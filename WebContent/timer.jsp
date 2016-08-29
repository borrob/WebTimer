<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
		<h2>New timers</h2>
		<p id="timersparagraph">
			<%
			out.println(request.getAttribute("next_interval"));
			%>
		</p>
		<a href="TimerPlus">+++</a><a href="TimerMinus">---</a>
	</body>
</html>