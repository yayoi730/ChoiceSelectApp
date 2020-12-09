function processFeedback(result) {
	console.log("addFeedback result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		//reloading page will update the labels, no need for redundnacy
		window.location.reload();
	} else {
		alert("Feedback could not be processed");
		console.log("addFeedback unexpected error: " + error);
	}
}


function processGetAltFeedback(result) {
	console.log("getAlt for feedback result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		console.log("about to process feedback request")
		
		//get alt ID from response
		var altObj = js["alt"];
		var aid = altObj["AID"];
		
		query = new URLSearchParams(window.location.search);

		var dataFb = {};
		dataFb["aID"] = aid;
		dataFb["creator"] = query.get("user");
		
		//make sure feedback is not empty
		if (document.getElementById("feedbackInput").value == null || document.getElementById("feedbackInput").value == "") {
			alert("Please enter a valid feedback description");
		} else {
			dataFb["description"] = document.getElementById("feedbackInput").value;
		}
		
		var jsFb = JSON.stringify(dataFb);
		console.log("JS: " + jsFb);
	
		//send addFeedback request
		var xhrFb = new XMLHttpRequest();
		xhrFb.open("POST", addFeedback_url, true);
		xhrFb.send(jsFb);
		console.log("addFeedback req sent");
	
		//process and update html
		xhrFb.onloadend = function () {
	    	if (xhrFb.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhrFb.responseText);
				processFeedback(xhrFb.responseText);
			} else {
				processFeedback("N/A");
			}
  		};
	} else {
		alert("Alternative could not be retrieved for adding feedback");
		console.log("getAlt for feeedback unexpected error: " + error);
	}
	
	
}


function handleFeedbackClick(e) {
	
	//make sure current alternative is valid
	if (document.getElementById("altDesc").value == "" || document.getElementById("altDesc").value == null || document.getElementById("feedbackInput").value == "") {
		alert("Make sure alternative is feedback is a valid input.");
	} else {
	
		var urlParams = new URLSearchParams(window.location.search);	//grab current url
		currentAlt = urlParams.get("alt");								//grab alt number
		currentId = urlParams.get("tid");								//grab tid
		
		console.log("retrieved: alt=" + currentAlt + ", tid=" + currentId);
	
		//format data as JSON
		var dataAlt = {};
		dataAlt["altNumber"] = currentAlt;
		dataAlt["cID"] = currentId;
		var jsAlt = JSON.stringify(dataAlt);
		console.log("JS: " + jsAlt);
		
		//send getAlt request
		var xhrAlt = new XMLHttpRequest();
		xhrAlt.open("POST", getAlt_url, true);
		xhrAlt.send(jsAlt);
		console.log("getAlt req sent for creating feedback");
		
		//process and update html
		xhrAlt.onloadend = function () {
	    	if (xhrAlt.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhrAlt.responseText);
				processGetAltFeedback(xhrAlt.responseText);
			} else {
				processGetAltFeedback("N/A");
			}
  		};

	}
}