
function processComplete(result, isAlt) {
	console.log("complete result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		if(isAlt != true) {
			alert("You chose: main choice. Choice has been finalized.")
		} else {
			alert("You chose: alternative " + completionNum + ". Choice has been finalized.")
		}
		
		//refreshing page will disable the buttons
		window.location.reload();
		
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
		xhr.open("POST", complete_url, true);
		xhr.send(js);
		console.log("complete req sent");
	
		//process and update html
		xhr.onloadend = function () {
	    	if (xhr.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhr.responseText);
				processComplete(xhr.responseText, isAlt);
			} else {
				processComplete("N/A", isAlt);
			}
  		};
	}
	
	
}


