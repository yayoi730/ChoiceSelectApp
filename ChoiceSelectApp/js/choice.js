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
	
	//store element to be changed as a new var
	var choiceDescLabel = document.getElementById('choiceDesc');
	var choiceIdLabel = document.getElementById('choiceId')
	
	var desc = js["choiceDesc"];
	var cid = js["choiceId"];
	
	choiceDescLabel.innerHTML = "<div id = \"choiceDesc\" value = \"" + desc + "\" />";
	choiceIdLabel.innerHTML = "<div id = \"choiceId\" value = \"" + cid + "\" />";
}

