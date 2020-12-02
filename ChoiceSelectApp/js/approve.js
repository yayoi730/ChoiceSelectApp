
function handleLikeClick(e) {
	//get member object of current user
	//get alternative object currently being viewed
	//get name and add to approvers list
	//update approvers label on page
	
	var currentAlt = document.altLabelsForm.approversList
	
	//get current user
	var xhl = new XMLHttpRequest();
	xhl.open("GET", getAlt_url + "/" + altNum);
	xhl.send();
	console.log("get alt request send");
	
	//process and update html
	xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      process(xhr.responseText);
    } else {
      process("N/A", "0");
    }
  };
	
}

function process(result) {
	var js = JSON.parse(result);
	var status = js["httpCode"];
	var response = js["result"];
	var alt = js["alt"];
	//make sure alternative has both aid and an altNum based of the choice
	var altNum = alt["altNum"];
	
	
	
	//add current user's name to approvers list and increment num likes
}
