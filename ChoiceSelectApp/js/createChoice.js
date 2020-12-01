

function process(result) {
	console.log("result: " + result);
	var js = JSON.parse(result);
	var status = js["httpCode"];
	var response = js["result"];
	var team = js["team"];
	
	if (status == 200) {
		console.log("create choice success");
		
		// get cid and desc from created choice
		var createdChoice = js["choice"];
		var cid = createdChoice["cid"];
		var desc = createdChoice["description"];
		
		//how to give data this to mainUI page??
		
	} else {
		console.log("unexpected error : create new choice")
	}
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
	
	var data = {}
	data["name"] = name;
	data["password"] = pw;
	data["choiceDesc"] = desc;
	data["teamSize"] = size;
	data["alts"] = [alt1, alt2, alt3, alt4, alt5];

	//make sure necessary inputs are present
	if ((name == "" || desc == "") || (alt1 == "" || alt2 == "") || (size == "" || size == "0")) {
		alert("Please login with a username. To create a choice you must include a description, team size, and at least 2 alternatives.")
	} else {
	
		//send out data to create new member
		var js = "";
		var xhr = new XMLHttpRequest();
		jsLogin = JSON.stringify(data);
		console.log("JS:" + js);
		xhr.open("POST", createTeam_url + "/" + name + "/" + password + "/" + desc + "/" + size, true);
		xhr.send(data);
			
		//processs results and handle HTML
		xhr.onloadend = function () {
	    console.log(xhr);
	    console.log(xhr.request);
	    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 	if (xhr.status == 200) {
	      	console.log ("XHR:" + xhr.responseText);
	      	process(xhr.responseText);
    	 	} else {
    		 	console.log("actual:" + xhr.responseText);
			  	var jsLogin = JSON.parse(xhr.responseText);
			  	var err = jsLogin["response"];
			  	alert (err);
    	 	}
    	} else {
      		process("N/A");
    	}
 	 	};
	
	}
}



