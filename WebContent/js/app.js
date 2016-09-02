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
			if (countdown <= 0) {
				document.body.style.backgroundColor = "green";
			} else {
				document.body.style.backgroundColor = "red";
			}
	    }
	  };
	  xhttp.open("GET", "getData", true);
	  xhttp.send();
		  
	  //repeat update after 1 sec
	  setTimeout(function() { update(); }, 1000);
}