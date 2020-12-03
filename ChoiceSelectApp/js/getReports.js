
function processGetReports(result) {
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var reportsList["reports"];

	//for each report object, add a div and diplay tid, 
	
}

function handleGetReports(e) {
	
	//set data to be sent
	var data = {}
	data["wantReports"] = true;
	var js = JSON.stringify(data);
	console.log("JS: " + js);
	
	//send getReports request
	var xhr = new XMLHttpRequest();
	xhr.open("POST", createTeam_url, true);
	xhr.send(js);
	console.log("create team request sent");
	
	//process results
	xhr.onloadend = function () {
		console.log(xhr.request);
		if (xhr.readyState == XMLHttpRequest.DONE) {
		console.log ("XHR:" + xhr.responseText);
			processGetReports(xhr.responseText);
		} else {
			processGetReports("N/A");
		}
	};
	
}