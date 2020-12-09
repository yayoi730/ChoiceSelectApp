
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
		
		/*
		//hide alt buttons if no alt exists
		var listAlts = team["alternativeList"];
		for (var i = 0; i < listAlts.length; i++) {
			
			var currentAlt = listAlts[i];
			
			if (currentAlt["description"] == "") {
				var eleName = "alt" + currentAlt["altNumber"] + "Btn";
				document.getElementById(eleName).style.display = "none";
			}
		}
		*/
		
		//hide buttons if choice is finalized
		if (choice["finalChoice"] != -1) {
			document.getElementById('feedbackButton').style.display = "none";
			document.getElementById('feedbackInput').style.display = "none";
			document.getElementById('likeButton').style.display = "none";
			document.getElementById('dislikeButton').style.display = "none";
			document.getElementById('markAlt').style.display = "none";
			document.getElementById('markChoice').style.display = "none";
			
			//dislpay choice is complete
			var completionMsg = document.createElement("p");
			completionMsg.innerHTML = "Choice was completed on" + choice["completionDate"].toString() 
				+ "<br>No more changes can be made.";
			document.choiceLabelForm.append(completionMsg);
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
	
	if (status == 200) {
		//get alt description from result
		var altObj = js["alt"];
		var desc = altObj["description"];
		
		//set alt description label
		var altDescLabel = document.getElementById('altDesc');
		altDescLabel.innerHTML = "Alternative " + altNum + " Description :" + desc;
		
		//get alt approvers and disapprovers lists
		var listAp = altObj["approvers"];
		var listDisap = altObj["dispprovers"];
		
		//set list labels
		var apLabel = document.getElementById('approversList');
		var disapLabel = document.getElementById('disapproversList');
		apLabel.innerHTML = listAp.length + " Approvers: " + listAp.toString();
		disapLabel.innerHTML = listDisap.length + " Disapprovers: " + listDisap.toString();
		
		//get feedback from alt
		var listFeedback = altObj["feedback"];

		//display feedback
		for (var i = 0; i < listFeedback.length; i++) {
			//get feedback of current index
			var current = listFeedback[i];

			//get each attribute
			var when = current["timestamp"];
			var creator = current["creator"];
			var desc = current["description"];
			
			//display in table
			var table = document.getElementById("feedbackTable");
			var currentRow = table.insertRow(1);
			currentRow.insertCell(0).innerHTML = desc;
			currentRow.insertCell(1).innerHTML = creator;
			currentRow.insertCell(2).innerHTML = when;
		}
		
	} else {
		console.log("getAlt error: " + error);
	}
	
}



function initialize() {
	
	var urlParams = new URLSearchParams(window.location.search);	//grab current url
	
	if (urlParams.has("tid")) {
		currentId = urlParams.get("tid");							//grab cid
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
		currentAlt = urlParams.get("alt");							//grab alt number
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


