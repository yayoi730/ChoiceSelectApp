
function process(result, name) {
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
		
		//redirect to main interface page
		var urlParams = new URLSearchParams();
		urlParams.set("tid", choiceId);
		urlParams.set("user", name);
		urlParams.set("alt", "1");
		window.location.href = "https://s3.us-east-2.amazonaws.com/choice.select.app/html/mainUI.html" + "?" + urlParams.toString(); 
		console.log("query: " + urlParams.toString());
		
	} else {
		alert("Error loading choice. Choice ID does not exist or username is taken. Please try again. Make sure the team is not already full.")
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
		xhr.open("POST", login_url, true);
		xhr.send(js);
		console.log("login request sent");
		
		//process results
		xhr.onloadend = function () {
			console.log(xhr.request);
			if (xhr.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhr.responseText);
				process(xhr.responseText, name);				
			} else {
				process("N/A", name);				
			}
		};
	}
}

