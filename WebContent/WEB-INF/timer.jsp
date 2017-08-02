<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="description" content="WebTimer is a tool for a musician and a live coder to keep contact and to interact with the audiance.">
		<meta name="author" content="RVA van Loon - aka borrob & offzz">	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
		<link rel="stylesheet" href="css/style.css">
		<script type="text/javascript" src="js/app.js"></script>
		<title>WebTImer</title>
	</head>
	<body onload="init()">
		<h1>WebTimer</h1>
		<div>
			<p id="countdowntimeparagraph">
				<%
					out.println(Integer.parseInt("" + request.getAttribute("countdown")));
				%>
			</p>
			<div id="nextIntervalDiv" class="next">
				<p>The next interval is: <span id="timersparagraph" class="intervalText">
					<%
						out.println(request.getAttribute("next_interval"));
					%>
				</span> seconds.</p>
				<button id="plus" class="timerButton">+ 10 sec</button><br/>
				<button id="minus" class="timerButton">- 10 sec</button><br/>
			</div>
			<div id="nextnextIntervalDiv" class="next">
				<p>The interval after that is: <span id="timersparagraph2" class="intervalText">
					<%
						out.println(request.getAttribute("next_interval2"));
					%>
				</span> seconds.</p>
				<button id="plus2" class="timerButton">+ 10 sec</button><br/>
				<button id="minus2" class="timerButton">- 10 sec</button><br/>
			</div>
		</div>
		<div class="bottomthings">
			<form method="POST" action="TimerComment">
				<label for="userCommentInput">Add comment:</label><br/>
				<input type="text" id="userCommentInput" name="userCommentInput" value=""/><br/>
				<button type="submit" id="commentButton">COMMENT</button>
			</form>
			<p id="userComments">
				<%
					out.println(request.getAttribute("userComments"));
				%>
			</p>
		</div>

	</body>
</html>