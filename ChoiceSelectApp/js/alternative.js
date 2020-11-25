//

function handleAltClick(altNum) {
	
	//retrieve alternative with given altNum
	//display alternative description on page
	
   var xhr = new XMLHttpRequest();
   xhr.open("GET", getAlt_url + "/" + altNum, true);
   xhr.send();
   
   console.log("sent");

  //process results and update html
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processResponse(xhr.responseText);
    } else {
      processResponse("N/A");
    }
  };
}

//use as ref to get descriptions from database (GET)

function processResponse(result) {
  console.log("res:" + result);
  var js = JSON.parse(result);
  var altDesc = document.getElementById('altDesc');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var constantJson = js.list[i];
    console.log(constantJson);
    
    var altDesc = constantJson["altDesc"];
    	output = ;
  }

  // Update computation result
  constList.innerHTML = output;
}