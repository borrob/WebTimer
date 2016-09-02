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
		<div>
			<p id="userComments">
			<%
				out.println(request.getAttribute("userComments"));
			%>
			</p>
			<form method="POST" action="TimerComment">
				<label for="userCommentInput">Add comment:</label>
				<input type="text" id="userCommentInput" name="userCommentInput" value="type..."/>
				<button type="submit">COMMENT</button>
			</form>
		</div>
		<h2>New timers</h2>

		<div class="timerControl">
			<p id="timersparagraph">
				<%
				out.println(request.getAttribute("next_interval"));
				%>
			</p>
			<div class="timerControlBox"><a href="TimerAction?action=plus" class="timerControl">+++</a></div>
			<div class="timerControlBox"><a href="TimerAction?action=minus" class="timerControl">---</a></div>
		</div>
		<div class="timerControl">
			<p id="timersparagraph2">
				<%
				out.println(request.getAttribute("next_interval2"));
				%>
			</p>
			<div class="timerControlBox"><a href="TimerAction?action=plus2" class="timerControl">+++</a></div>
			<div class="timerControlBox"><a href="TimerAction?action=minus2" class="timerControl">---</a></div>
		</div>
	</body>
</html>