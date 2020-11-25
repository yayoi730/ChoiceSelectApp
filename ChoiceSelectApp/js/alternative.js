//

function handleAltClick(altNum) {
	
	//retrieve alternative with given altNum
	//display alternative description on page
	
   var xhr = new XMLHttpRequest();
   xhr.open("GET", getAlts_url + "/" + altNum, true);
   xhr.send();
   
   console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processListResponse(xhr.responseText);
    } else {
      processListResponse("N/A");
    }
  };
}