
function processComplete(result, completionNum) {
	console.log("complete result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		if(completionNum == 0) {
			alert("You chose: main choice. Choice has been finalized.")
		} else if (completionNum > 0 && completionNum < 6) {
			alert("You chose: alternative " + completionNum + ". Choice has been finalized.")
		} else {
			console.log("invalid completion number?");
		}	
		
		//refreshing page will disable the buttons
		wondow.location.refresh();
		
	} else {
		alert("'Mark As Chosen' request could not be completed");
		console.log("complete unexpected error: " + error);
	}
}

function handleCompleteClick(e, isAlt) {
	
	//confirm completion
	if (confirm("Are you sure you want to complete this choice?")) {
		
		var query = new URLSearchParams(window.location.search);
	
		var data = {};
		data["tID"] = query.get("tid");
		
		if (isAlt == "false") {
			completionNum = 0;
		} else {
			completionNum = query.get("alt");
		}
		
		data["choiceNum"] = completionNum;
	
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
				processComplete(xhr.responseText, completionNum);
			} else {
				processComplete("N/A", completionNum);
			}
  		};
	}
	
	
}


