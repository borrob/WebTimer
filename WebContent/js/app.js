function update(){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("countdowntimeparagraph").innerHTML = xhttp.responseText;
			
			//change background color when count down is reached
			var countdown = parseInt(xhttp.responseText);
			if (countdown <= 0) {
				document.body.style.backgroundColor = "green";
			} else {
				document.body.style.backgroundColor = "red";
			}
	    }
	  };
	  xhttp.open("GET", "AjaxTimer", true);
	  xhttp.send();
	  
	  //repeat update after 1 sec
	  setTimeout(function() { update() }, 1000);
}