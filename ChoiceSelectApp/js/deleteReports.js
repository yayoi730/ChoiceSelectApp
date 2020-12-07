
function processDeleteReports(result) {
	console.log("addFeedback result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		window.location.reload();
		alert("Click 'Get Reports' to see changes");
	} else {
		alert("Reports could not be deleted");
		console.log("deleteReports unexpected error: " + error);
	}
}


function handleDeleteReports (e) {

	var data = {};
	data[""] = document.getElementById("daysOldInput");
	
	var js = JSON.stringify(data);
	console.log("JS: " + js);

	//send addFeedback request
	var xhr = new XMLHttpRequest();
	xhr("POST", deleteReports_url, true);
	xhr(js);
	console.log("deleteReports req sent");

	//process and update html
	xhr = function () {
	    if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processDeleteReports(xhr.responseText);
		} else {
			processDeleteReports("N/A");
		}
  	};
}