//GET choice 
function updateChoiceDesc() {
	//submit getChoice request
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getChoice_url, true)
	xhr.send();
	console.log("getChoice req sent");
	
	//process and update html
	xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processResponse(xhr.responseText);
    } else {
      processResponse("N/A");
    }
  };
}


//respond to server and replace 'choiceDesc' with actual description
function processResponse(result) {
	console.log("result: " + result);
	
	//for manipulating an html element
	var js = JSON.parse(result);
	
	//elements to be changed
	var descLabel = document.choiceLabelForm.choiceDesc;
	var idLabel = document.choiceLabelForm.choiceId;
	
	//retrieve data returned
	var desc = js["description"];
	var cid = js["cid"];
	
	//change 
	descLabel.innerHTML = "<div id = \"choiceDesc\" value = \"Choice Description: " + desc + "\" />";
	idLabel.innerHTML = "<div id = \"choiceId\" value = \"Choice ID: " + cid + "\" />";
}

