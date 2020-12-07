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


function handleFeedbackClick(e) {
	query = new URLSearchParams(window.location.search);

	var data = {};
	data[""] = query.get("tid");
	data[""] = query.get("alt");
	data[""] = query.get("user");
	
	if (document.getElementById("feedbackInput").value == "") {
		alert("Please enter a valid feedback description");
	} else {
		data[""] = document.getElementById("feedbackInput").value;
	}
	
	var js = JSON.stringify(data);
	console.log("JS: " + js);

	//send addFeedback request
	var xhr = new XMLHttpRequest();
	xhr("POST", addFeedback_url, true);
	xhr(js);
	console.log("addFeedback req sent");

	//process and update html
	xhr = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processFeedback(xhr.responseText);
		} else {
			processFeedback("N/A");
		}
  	};
}