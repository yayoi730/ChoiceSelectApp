
function processGetReports(result, findOne) {
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		var reportsList = js["reports"];
		
		if (findOne == false) {
			//for each report in list, create and display new <p> tag
			var i;
			for (i = 0; i < reportsList.length; i++) {
				//get report with given index
				var current = reportsList[i];
				
				//grab each attribute of a report
				var cid = current["choiceID"];
				var creationDate = current["creationDate"];
				var completed = current["completed"];
				
				var report = document.createElement("p");
				report.innerHTML = "Choice ID: " + cid + ", Creation Date: " + creationDate.toString() + ", Completed: " + completed.toString();
				document.reportsDisplay.appendChild(report);
			}
		} else {
			for (i = 0; i < reportsList.length; i++) {
				var current = reportsList[i];
				var currentCid = current["choiceID"];
				var searchCid = document.getElementById('idInput').value;
				
				if (searchCid == currentCid) {
					var cid = current["choiceID"];
					var creationDate = current["creationDate"];
					var completed = current["completed"];
					
					//display desired report
					var singleReport = document.createElement("p");
					singleReport.innerHTML = "Choice ID: " + cid + ", Creation Date: " + creationDate.toString() + ", Completed: " + completed;
					document.reportsDisplay.appendChild(singleReport);
				}
			}
		}	

	} else {
		alert("Unexpected error getting reports");
		console.log("getReports error")
	}
		
	
}

function handleGetReports(e, findOne) {
	
	//set data to be sent
	var data = {}
	data["wantReports"] = true;
	var js = JSON.stringify(data);
	console.log("JS: " + js);
	
	//send getReports request
	var xhr = new XMLHttpRequest();
	xhr.open("POST", getReports_url, true);
	xhr.send(js);
	console.log("get reports request sent");
	
	//process results
	xhr.onloadend = function () {
		console.log(xhr.request);
		if (xhr.readyState == XMLHttpRequest.DONE) {
		console.log ("XHR:" + xhr.responseText);
			processGetReports(xhr.responseText, findOne);
		} else {
			processGetReports("N/A", findOne);
		}
	};
	
}