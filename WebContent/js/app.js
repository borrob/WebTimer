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
			var data = xhttp.responseText.split("_");
			document.getElementById("countdowntimeparagraph").innerHTML = data[0];
			document.getElementById("timersparagraph").innerHTML = data[1];
			document.getElementById("timersparagraph2").innerHTML = data[2];
			document.getElementById("userComments").innerHTML = data[3];
			
			//change background color when count down is reached
			var countdown = parseInt(data[0]);
			var interval = parseInt(data[1]);
			var interval2 = parseInt(data[2]);
			showOrHideInterval(interval!=999999);
			showOrHideInterval2(interval2!=999999);
			if (countdown > 0 || alreadyFlashed){
				document.body.style.backgroundColor = "green";
				if (alreadyFlashed){
					document.body.style.backgroundColor = "red"
				}
			}
			else {
				flash();
				if (interval == 999999){
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