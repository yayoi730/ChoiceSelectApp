//

function handleAltClick(altNum) {
	//submit getAlt/{altNum} request
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getAlt_url + "/" + altNum, true);
	xhr.send();
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


//respond to server and replace 'altDesc' with actual description
function processResponse(result, altNum) {
	console.log("result: " + result);
	
	//for manipulating an html element
	var js = JSON.parse(result);
	
	//store element to be changed as a new var
	var altDescLabel = document.getElementById('altDesc');
	var desc = js["altDesc"];
	altDescLabel.innerHTML = "<div id = \"altDesc\" value = \"" + desc + "\" />";
}

