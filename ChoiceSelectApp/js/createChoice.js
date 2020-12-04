

//process results from createTeam
function process(result) {
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		console.log("create choice success");
		
		var team = js["team"];					//get team object from response
		var choice = team["choice"];			//get choice from team
		var teamSize = team["teamSize"];		//get team size from team
		var members = team["members"];			//get members from team
		
		var firstMem = members[0].name;			//get name of user
		var choiceDesc = choice["description"];	//get choice desc from choice
		var cid = choice["ID"];					//get cid from choice
		var alts = choice["alternativeList"];	//get alts from choice
		
		/*
		//display values on same page (testing purposes)
		document.getElementById("cidLabel").innerHTML = cid;
		document.getElementById("descLabel").innerHTML = choiceDesc;
		document.getElementById("sizeLabel").innerHTML = teamSize;
		document.getElementById("usernameLabel").innerHTML = JSON.stringify(members);
		document.getElementById("altsLabel").innerHTML = JSON.stringify(alts);
		*/
		
		//redirect to main inferface page with cid in query
		var urlParams = new URLSearchParams();
		urlParams.set("tid", cid);
		urlParams.set("user", firstMem);
		urlParams.set("alt", "1");
		window.location.href = "https://s3.us-east-2.amazonaws.com/choice.select.app/html/mainUI.html" + "?" + urlParams.toString();
		console.log("query: " + urlParams.toString());
		
	} else {
		alert("Error creating new choice; reload page");
		console.log("error creating choice");
	}
	
}

function handleCreateClick(e) {
	
	//get all inputs
	var name = document.getElementById("username").value;
	var pw = document.getElementById("password").value;
	
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
	data["cDesc"] = desc;
	data["teamSize"] = size;
	data["altDesc"] = [alt1, alt2, alt3, alt4, alt5];
	

	//make sure necessary inputs are present
	if ((name == "" || desc == "") || (alt1 == "" || alt2 == "") || (size == "" || size == "0")) {
		alert("Please login with a username. To create a choice you must include a description, team size, and at least 2 alternatives.");
		} else {
			//convert to json format
			var js = JSON.stringify(data);
			console.log("JS:" + js);
			
			//send createTeam request
			var xhr = new XMLHttpRequest();
			xhr.open("POST", createTeam_url, true);
			xhr.send(js);
			console.log("create team request sent");
			
			//process results
			xhr.onloadend = function () {
				console.log(xhr.request);
				if (xhr.readyState == XMLHttpRequest.DONE) {
					console.log ("XHR:" + xhr.responseText);
					process(xhr.responseText);
				} else {
					process("N/A");
				}
			};
			
		}
	
}

