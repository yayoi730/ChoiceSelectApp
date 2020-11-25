function processLogin(result) {
  console.log("result:" + result);
}


function handleLogin(e) {
	
	//for the choiceId check if member already exists
	//if not, create new member with those credentials
	//then bring user to main interface page
	
	//get inputs
	var name = document.getElementById('username').value;
	var pw = document.getElementById('password').value;
	var cid = document.getElementById('choiceId').value;
	
	//make sure necessary inputs are present
	if (name == "" || cid == "") {
		alert("Please enter a username and choice ID");
	} else {
	
		//send out data to login
		var loginData = {};
		var js = "";
		var xhr = new XMLHttpRequest();
		loginData["username"] = name;
		loginData["choiceId"] = cid;
		if (pw != "") {
			loginData["password"] = pw;
			js = JSON.stringify(loginData);
			console.log("JS:" + js);
			xhr.open("POST", login_url + "/" + cid + "/" + username + "/" + password, true);
			xhr.send(loginData);
		} else {
			js = JSON.stringify(loginData);
			console.log("JS:" + js);
			xhr.open("POST", login_url + "/" + cid + "/" + username, true);
			xhr.send(loginData);
		}
	
		
		//processs results and handle HTML
		xhr.onloadend = function () {
	    console.log(xhr);
	    console.log(xhr.request);
	    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 	if (xhr.status == 200) {
	      	console.log ("XHR:" + xhr.responseText);
	      	processLogin(xhr.responseText);
    	 	} else {
    		 	console.log("actual:" + xhr.responseText);
			  	var js = JSON.parse(xhr.responseText);
			  	var err = js["response"];
			  	alert (err);
    	 	}
    	} else {
      		processLogin("N/A");
    	}
 	 	};

		window.location.href = 'https://s3.us-east-2.amazonaws.com/choice.select.app/html/mainUI.html'
	}
}



function handleAdminLogin(e) {
	
	//if login matches admin, bring to admin page
	//otherwise show error message and stay on page
	
	//get inputs, disregard choiceId input
	var form = document.loginForm;
	var data = {};
	data["username"] = form.username.value;
	data["password"] = form.password.value;
	
	//convert data to JSON
	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();

	var specified = "/" + username + "/" + password;
	xhr.open("POST", login_url + specified, true)
	
	//send to API as JSON
	xhr.send(js)
	
	
	//switch to admin page

	location.href = "adminPage.html";
	
	//processs results and handle HTML
	xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processLogin(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processLogin("N/A");
    }
  };
}