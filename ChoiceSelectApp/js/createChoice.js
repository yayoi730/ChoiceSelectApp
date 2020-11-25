//

function handleCreateChoice(e) {
	
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
	
	var alts = {}
	alts["alt1"] = form.alt1.value;
	alts["alt2"] = form.alt2.value;
	alts["alt3"] = form.alt3.value;
	alts["alt4"] = form.alt4.value;
	alts["alt5"] = form.alt5.value;
	choice["alts"] = alts;
	
	console.log("JS:" + JSON.stringify(choice));
	var xhrChoice = new XMLHttpRequest();
	xhrLogin.open("POST", createChoice_url + "/" + choiceDesc + "/" + teamSize, true);
	xhrLogin.send(jsChoice);
	
	//add Choice to Team

	
}