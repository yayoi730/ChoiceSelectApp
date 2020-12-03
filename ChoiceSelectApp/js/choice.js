
function processGetTeam(result) {
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
		
		var query = new URLSearchParams(window.location.search);
		if(query.has("alt")) {
			var altNum = query.get("alt");
			var altList = choice["alternativeList"];
			var altObject = altList[altNum];
			var altDescription = altObject["description"];
			var displayedAltNum = Number(altNum);
			document.getElementById("altDesc").innerHTML = "Alternative " + displayedAltNum.toString() + " Description: " + altDescription;
		}
		
	} else {
		alert("Error loading choice; retry login");
		console.log("error getting choice information");
	}
	
}


function processGetAlt(result, altNum) {
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	//get alt description from result
	var altObj = js["alt"];
	var desc = altObj["description"];
	
	//set label with retrieved alt desc
	var altDescLabel = document.getElementById('altDesc');
	altDescLabel.innerHTML = "Alternative " + altNum + " Description :" + desc;
	
}



function initialize() {
	
	var urlParams = new URLSearchParams(window.location.search);	//grab current url
	currentId = urlParams.get("tid");								//grab cid
	currentAlt = urlParams.get("alt");

	if (urlParams.has("tid")) {
		console.log("TID retrieved: " + currentId);
		
		//format data as JSON
		var dataTeam = {};
		dataTeam["tid"] = currentId;
		var jsTeam = JSON.stringify(dataTeam);
		console.log("JS: " + jsTeam);	
		
		//submit getTeam request
		var xhrTeam = new XMLHttpRequest();
		xhrTeam.open("POST", getTeam_url, true)
		xhrTeam.send(jsTeam);
		console.log("getTeam req sent");
		
		//process and update html
		xhrTeam.onloadend = function () {
	    	if (xhrTeam.readyState == XMLHttpRequest.DONE) {
      			console.log ("XHR:" + xhrTeam.responseText);
      			processGetTeam(xhrTeam.responseText);
    		} else {
	      		processGetTeam("N/A");
    		}
  		};

	} else {
		alert("No choice specified; go back");
		console.log("error: no choice ID specified in url")
	}	
	
	
	if (urlParams.has("alt")) {
		console.log("ALT retrieved: " + currentAlt);
		
		//format data as JSON
		var dataAlt = {};
		dataAlt["altNumber"] = currentAlt;
		dataAlt["cID"] = currentId;
		var jsAlt = JSON.stringify(dataAlt);
		console.log("JS: " + jsAlt);
		
		//send request
		var xhrAlt = new XMLHttpRequest();
		xhrAlt.open("POST", getAlt_url, true);
		xhrAlt.send(jsAlt);
		console.log("getAlt req sent");
		
		//process and update html
		xhrAlt.onloadend = function () {
	    	if (xhrAlt.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhrAlt.responseText);
				processGetAlt(xhrAlt.responseText, currentAlt);
			} else {
				processGetAlt("N/A", "0");
			}
  		};

	} else {
		alert("No alternative specified; go back");
		console.log("error: no alt specified in url")
	}
	
}


