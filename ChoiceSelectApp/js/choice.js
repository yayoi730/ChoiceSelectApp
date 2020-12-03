
function processResponse(result) {
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		console.log("team retrieved successfully");
		
		var team = js["team"];					//get team object from response
		var choice = team["choice"];			//get choice from team
		var teamSize = team["teamSize"];		//get team size from team
		var choiceDesc = choice["description"];	//get choice desc from choice
		var cid = choice["ID"];					//get cid from choice
		
		document.getElementById("cidLabel").innerHTML = "Choice ID: " + cid;
		document.getElementById("descLabel").innerHTML = "Choice Description: " + choiceDesc;
		document.getElementById("sizeLabel").innerHTML = "Max Members: " + teamSize;
		
	} else {
		alert("Error loading choice; retry login");
		console.log("error getting choice information");
	}
	
}


function initialize() {
	
	var urlParams = new URLSearchParams(window.location.search);	//grab current url
	if (urlParams.has("cid")) {
		currentId = urlParams.get("cid");							//grab cid from url query
		console.log("CID retrieved: " + currentId);
		
		//format data as JSON
		var data = {};
		data["cid"] = currentId;
		var js = JSON.stringify(data);
		console.log("JS: " + js);	
		
		//submit getTeam request
		var xhr = new XMLHttpRequest();
		xhr.open("GET", getTeam_url, true)
		xhr.send(js);
		console.log("getTeam req sent");
		
		//process and update html
		xhr.onloadend = function () {
	    	if (xhr.readyState == XMLHttpRequest.DONE) {
      			console.log ("XHR:" + xhr.responseText);
      			processResponse(xhr.responseText);
    		} else {
	      		processResponse("N/A");
    		}
  		};

	} else {
		alert("No choice specified; go back");
		console.log("error: no choice ID specified in url")
	}	
	
}


