
function processGetReports(result) {
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		var reportsList = js["reports"];
		
		
		//for each report in list, create and display new row
		var i;			
		for (i = 0; i < reportsList.length; i++) {
			//get report with given index
			var current = reportsList[i];
			
			//grab each attribute of a report
			var cid = current["choiceID"];
			var creationDate = current["creationDate"];				
			var completed = current["completed"];
			
			//display in table
			var table = document.getElementById("reportsTable");
			var currentRow = table.insertRow(1);
			currentRow.insertCell(0).innerHTML = cid;
			currentRow.insertCell(1).innerHTML = "choice desc";
			currentRow.insertCell(2).innerHTML = creationDate;
			currentRow.insertCell(3).innerHTML = completed;
		}
			
	} else {
		alert("Unexpected error getting reports");
		console.log("getReports error")
	}
		
	
}

function handleGetReports(e) {
	
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
			processGetReports(xhr.responseText);
		} else {
			processGetReports("N/A");
		}
	};
	
}