
function processResponse(result, altNum) {
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	//get alt description from result
	var js = JSON.parse(result);
	var desc = js["description"];
	
	//set label with retrieved alt desc
	var altDescLabel = document.getElementById('altDesc');
	altDescLabel.innerHTML = desc;
	
	//reload page with altNum in query
	var query = new URLSearchParams(window.location.search);
	query.set("altNum", altNum);
	query.set("cid", query.get("cid"));
	window.location.href = "https://s3.us-east-2.amazonaws.com/choice.select.app/html/mainUI.html" + query.toString();
	
}


function handleAltClick(e, altNum) {
	
	//get value of cid from query
	var urlParams = new URLSearchParams(window.location.search);
	currentId = urlParams.get("cid");
	console.log("CID retrieved: " + currentId);
	
	//store payload to be sent
	var data = {};
	data["altNumber"] = altNum;
	data["cid"] = currentId;
	var js = JSON.stringify(data);
	
	//send request
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getAlt_url, true);
	xhr.send(js);
	console.log("getAlt req sent");
	
	//process and update html
	xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processResponse(xhr.responseText, altNum);
    } else {
      processResponse("N/A", "0");
    }
  };
}


