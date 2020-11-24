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
	
	
	var choiceData = {}
	choiceData["choiceDesc"] = form.choiceDesc.value;
	choiceData["alt1"] = form.alt1.value;
	choiceData["alt2"] = form.alt2.value;
	choiceData["alt3"] = form.alt3.value;
	choiceData["alt4"] = form.alt4.value;
	choiceData["alt5"] = form.alt5.value;
	
	//create choice without alternatives
	var choice = {}
	choice["choiceDesc"] = form.choiceDesc.value;
	console.log("JS:" + JSON.stringify(choice));
	var xhrChoice = new XMLHttpRequest();
	xhrLogin.open("POST", createChoice_url + choiceDesc, true);
	xhrLogin.send(jsChoice);
	
	//create alternatives
	
	
	//make new team with logged in member
	//create choice with given description and alternatives
	//add choice to team
	
	
	
	
	//redirect to mainUI.html
	
}