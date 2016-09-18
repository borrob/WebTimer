var alreadyFlashed;

function init(){
	update();
	setupUI();
	alreadyFlashed = false;
}

function update(){
	//Get the new timer and next interval and put it on the page.
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			data = JSON.parse(xhttp.response);
			document.getElementById("countdowntimeparagraph").innerHTML = data.countdown;
			document.getElementById("timersparagraph").innerHTML = data.interval;
			document.getElementById("timersparagraph2").innerHTML = data.interval2;
			document.getElementById("userComments").innerHTML = data.comments;
			
			//change background color when count down is reached
			showOrHideInterval(data.interval!=999999);
			showOrHideInterval2(data.interval2!=999999);
			if (data.countdown > 0 || alreadyFlashed){
				document.body.style.backgroundColor = "green";
				if (alreadyFlashed){
					document.body.style.backgroundColor = "red"
				}
			}
			else {
				flash();
				if (data.interval == 999999){
					alreadyFlashed=true;;
				}
			}
	    }
	  };
	  xhttp.open("GET", "getData", true);
	  xhttp.send();
		  
	  //repeat update after 1 sec
	  setTimeout(function() { update(); }, 1000);
}

function flash(){
	document.body.style.backgroundColor = "red";
	setTimeout(function(){
		document.body.style.backgroundColor = "green";
		setTimeout(function(){
			document.body.style.backgroundColor = "red";
			setTimeout(function(){
				document.body.style.backgroundColor = "green";
				setTimeout(function(){
					document.body.style.backgroundColor = "red";
				}, 200);
			}, 200);
		}, 200);
	}, 200);
}

function setupUI(){
	//add listeners to the buttons
	var buttons = document.getElementsByClassName('timerButton');
	for (var i = 0; i < buttons.length; i++){
		buttons[i].addEventListener("click",function(e){
			timerButtonClick(e.srcElement.id);
		});
	}
	
	//set focus to the user comment input text box
	document.getElementById("userCommentInput").focus();
}

function timerButtonClick(buttonid){
	location.href="TimerAction?action="+buttonid;
}

function showOrHideInterval(show){
	document.getElementById("nextIntervalDiv").hidden=!show;
}

function showOrHideInterval2(show){
	document.getElementById("nextnextIntervalDiv").hidden=!show;
}