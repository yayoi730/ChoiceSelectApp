

//process results from createTeam
function process(result) {
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var response = js["result"];
	
	if (status == 200) {
		console.log("login success");
		
		var team = js["team"];						//get team object from response
		var choice = team["choice"];				//get choice from team
		var teamSize = team["teamSize"];			//get team size from team
		var members = team["members"];				//get members from team
		var choiceDesc = choice["description"];		//get choice desc from choice
		var choiceId = choice["ID"];				//get cid from choice
		var alts = choice["alternativeList"];		//get alts from choice
		
		//display values on same page (testing purposes)
		document.getElementById("cidLabel").innerHTML = choiceId;
		document.getElementById("descLabel").innerHTML = choiceDesc;
		document.getElementById("sizeLabel").innerHTML = teamSize;
		document.getElementById("usernameLabel").innerHTML = members;
		document.getElementById("altsLabel").innerHTML = alts;
	} else {
		console.log("error loading choice");
	}
}

function handleLoginClick(e) {
	
	//get all inputs
	var name = document.getElementById("username").value;
	var pw = document.getElementById("password").value;
	var cid = document.getElementById("cid").value;
	
	var data = {}
	data["cid"] = cid; 
	data["name"] = name;
	data["password"] = pw;
	
	//make sure necessary inputs are present
	if (name == "" || cid == "") {
		alert("Please enter a username and choice ID");
	} else {
		//convert to json format
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		
		//send login request
		var xhr = new XMLHttpRequest();
		var url = login_url;
		xhr.open("POST", url, true);
		xhr.send(js);
		console.log("login request sent");
		
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

