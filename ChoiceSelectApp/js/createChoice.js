

function processLogin(result) {
	console.log("result: " + result)
}

//called on by "Create Choice" button
function handleCreateChoice(e) {

	//get all inputs
	var name = document.registerform.username.value;
	var pw = document.registerform.password.value;
	var desc = document.createChoiceForm.choiceDesc.value;
	var size = document.createChoiceForm.teamSize.value;
	var alt1 = document.createChoiceForm.alt1.value;
	var alt2 = document.createChoiceForm.alt2.value;
	var alt3 = document.createChoiceForm.alt3.value;
	var alt4 = document.createChoiceForm.alt4.value;
	var alt5 = document.createChoiceForm.alt5.value;

	//make sure necessary inputs are present
	if ((name == "" || desc == "") || (alt1 == "" || alt2 == "") || (size == "" || size == "0")) {
		alert("Please login with a username. To create a choice you must include a description, team size, and at least 2 alternatives.")
	} else {
	
		//send out data to create new member
		var loginData = {};
		var jsLogin = "";
		var xhr = new XMLHttpRequest();
		loginData["username"] = name;
		if (pw != "") {
			loginData["password"] = pw;
			jsLogin = JSON.stringify(loginData);
			console.log("JS:" + jsLogin);
			xhr.open("POST", createMember_url + "/" + username + "/" + password, true);
			xhr.send(loginData);
		} else {
			jsLogin = JSON.stringify(loginData);
			console.log("JS:" + jsLogin);
			xhr.open("POST", createMember_url + "/" + username, true);
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
			  	var jsLogin = JSON.parse(xhr.responseText);
			  	var err = jsLogin["response"];
			  	alert (err);
    	 	}
    	} else {
      		processLogin("N/A");
    	}
 	 	};

		
		//create choice without alternatives
		var choice = {}
		choice["choiceDesc"] = desc;
		choice["teamSize"] = size;
		choice["alternatives"] = [alt1, alt2, alt3, alt4, alt5];
		
		console.log("JS:" + JSON.stringify(choice));
		var xhrChoice = new XMLHttpRequest();
		xhrChoice.open("POST", createChoice_url + "/" + choiceDesc + "/" + teamSize, true);
		//createChoice called with desc and team size in path, list of alts in body
		xhrChoice.send(choice);
		
		//processs results and handle HTML
		xhrChoice.onloadend = function () {
	    console.log(xhrChoice);
	    console.log(xhrChoice.request);
	    if (xhrChoice.readyState == XMLHttpRequest.DONE) {
    	 	if (xhrChoice.status == 200) {
	      	console.log ("XHR:" + xhrChoice.responseText);
	      	processLogin(xhrChoice.responseText);
    	 	} else {
    		 	console.log("actual:" + xhrChoice.responseText);
			  	var jsChoice = JSON.parse(xhrChoice.responseText);
			  	var err = jsChoice["response"];
			  	alert (err);
    	 	}
    	} else {
      		processLogin("N/A");
    	}
 	 	};
	
		//redirect to main interface page
		window.location = 'https://s3.us-east-2.amazonaws.com/choice.select.app/html/mainUI.html';

	
	}
}



