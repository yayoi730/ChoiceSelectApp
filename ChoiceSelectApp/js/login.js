
function handleLoginClick(e) {
	
	//for the choiceId check if member already exists
	//if not, create new member with those credentials
	//then bring user to main interface page
	
	//get inputs
	var form = document.loginForm;
	var data = {};
	data["username"] = form.username.value;
	data["password"] = form.password.value;
	data["choiceId"] = form.choiceId.value;

	
	//convert data to JSON
	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();

	var specified = "/" + choiceId + "/" + username + "/" + password;
	xhr.open("POST", login_url + specified, true)
	
	//send to API as JSON
	xhr.send(js)

	location.href = "mainUI.html";	
	
	//processs results and handle HTML
	xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processCreateResponse(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processCreateResponse("N/A");
    }
  };
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
	      processCreateResponse(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processCreateResponse("N/A");
    }
  };
}