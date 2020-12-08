

function processCreateOpinion(result) {
	
	console.log("createOpinion result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		//reloading page will update the labels, no need for redundnacy
		window.location.reload();
	} else {
		alert("Like/Dislike could not be processed");
		console.log("create Opinion unexpected error: " + error);
	}
	
}

function processGetAltOpinion(result, isApproval, currentUser) {
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		console.log("about to process opinion req")
		
		//get alt ID from response
		var altObj = js["alt"];
		var aid = altObj["AID"];
		
		//prepare createOpinion request
		var dataOp = {};
		dataOp["aID"] = aid;
		dataOp["prover"] = currentUser;
		dataOp["approves"] = isApproval;
		var jsOp = JSON.stringify(dataOp);
		
		//send request
		var xhrOp = new XMLHttpRequest();
		xhrOp.open("POST", createOpinion_url, true);
		xhrOp.send(jsOp);
		console.log("createOpinion req sent");
		
		//process and update html
		xhrOp.onloadend = function () {
	    	if (xhrOp.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhrOp.responseText);
				processCreateOpinion(xhrOp.responseText);
			} else {
				processCreateOpinion("N/A");
			}
  		};
		
	} else {
		alert("alt could not be retrieved");
		console.log("getAltObject : unexpected error");
	}
	
	
	
}

function handleOpinionClick(e, isApproval) {
	
	var urlParams = new URLSearchParams(window.location.search);	//grab current url
	currentAlt = urlParams.get("alt");								//grab alt number
	currentUser = urlParams.get("user");							//grab user's name
	currentId = urlParams.get("tid");								//grab tid
	
	console.log("retrieved from url: alt=" + currentAlt + ", tid=" + currentId);

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
	console.log("getAlt req sent for creating opinion");
	
	//process and update html
	xhrAlt.onloadend = function () {
	    if (xhrAlt.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhrAlt.responseText);
			processGetAltOpinion(xhrAlt.responseText, isApproval, currentUser);
		} else {
			processGetAltOpinion("N/A", isApproval, currentUser);
		}
  	};

}


