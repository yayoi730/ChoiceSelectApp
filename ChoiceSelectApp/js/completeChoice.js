
function processCompleteChoice(result) {
	console.log("complete result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		alert("Choice/alternative successfully chosen");
	} else {
		alert("'Mark As Chosen' request could not be completed");
		console.log("complete unexpected error: " + error);
	}
}

function handleChooseChoice(e) {
	//if alt with desc == "", alert "choose valid alt"

	var data = {};
	data[""] = ;

	var js = JSON.stringify(data);
	console.log("JS: " + js);

	//send complete request
	var xhr = new XMLHttpRequest();
	xhr("POST", complete_url, true);
	xhr(js);
	console.log("complete req sent");

	//process and update html
	xhr = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processCompleteChoice(xhr.responseText);
		} else {
			processCompleteChoice("N/A");
		}
  	};
}


