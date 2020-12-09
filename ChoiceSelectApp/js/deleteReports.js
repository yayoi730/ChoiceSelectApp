
function processDeleteReports(result) {
	console.log("addFeedback result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		alert("Click 'Get Reports' to see changes");
		window.location.reload();
	} else {
		alert("Reports could not be deleted");
		console.log("deleteReports unexpected error: " + error);
	}
}


function handleDeleteReports(e) {
	
	//confirm deletion
	if (confirm("Are you sure you want to delete the old reports?")) {
		
		if (Number.isInteger(Number(document.getElementById("daysOldInput").value))) {
			var data = {};
			data["daysOld"] = document.getElementById("daysOldInput").value;
			
			var js = JSON.stringify(data);
			console.log("JS: " + js);
		
			//send addFeedback request
			var xhr = new XMLHttpRequest();
			xhr.open("POST", deleteReports_url, true);
			xhr.send(js);
			console.log("deleteReports req sent");
		
			//process and update html
			xhr.onloadend = function () {
		    	if (xhr.readyState == XMLHttpRequest.DONE) {
					console.log ("XHR:" + xhr.responseText);
					processDeleteReports(xhr.responseText);
				} else {
					processDeleteReports("N/A");
				}
  			};
		} else {
			alert("Value entered must be an integer");
		}
		
	}

}