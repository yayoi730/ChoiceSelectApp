//

function handleCreateChoiceClick(e) {
	
	var form = document.createChoiceForm;
	var loginData = {}
	loginData["username"] = form.username.value;
	
	//no password provided
	if (form.password.value == "") {
		var jsLogin = JSON.stringify(loginData);
		console.log("JS:" + jsLogin);
		var xhrLogin = new XMLHttpRequest();
		var specified = "/" + username;
	} else {
		//password provided
		loginData["password"] = form.password.value;
		var jsLogin = JSON.stringify(loginData);
		console.log("JS:" + jsLogin);
		var xhrLogin = new XMLHttpRequest();
		var specified = "/" + username + "/" + password;
	}
	xhrLogin.open("POST", createMember_url + specified, true);
	xhrLogin.send(jsLogin);
	
	//create new team with new Member object
	
	//How can I retrieve Member object created to add to a Team?
	
	//create choice without alternatives
	var choice = {}
	choice["choiceDesc"] = form.choiceDesc.value;
	choice["teamSize"] = form.teamSize.value;
	console.log("JS:" + JSON.stringify(choice));
	var xhrChoice = new XMLHttpRequest();
	xhrLogin.open("POST", createChoice_url + "/" + choiceDesc, true);
	xhrLogin.send(jsChoice);
	
	//add each alternative 
	var xhrCreateAlt = new XMLHttpRequest();
	xhrCreateAlt.open("POST", createAlternative_url + "/" + "1" + "/" + alt1, true);
	xhrCreateAlt.open("POST", createAlternative_url + "/" + "2" + "/" + alt2, true);
	xhrCreateAlt.open("POST", createAlternative_url + "/" + "3" + "/" + alt3, true);
	xhrCreateAlt.open("POST", createAlternative_url + "/" + "4" + "/" + alt4, true);
	xhrCreateAlt.open("POST", createAlternative_url + "/" + "5" + "/" + alt5, true);
	
	//How can I create then retrieve these Alternative objects and then add to a Choice?
	//The new Choice object is created with an empty set of Alternatives (line 35)
	//the format is /addAlt/{aid}/{altDesc} for the API, where the Choice object is already known

	//add Choice to Team
	
	
	//redirect to mainUI.html
	
}