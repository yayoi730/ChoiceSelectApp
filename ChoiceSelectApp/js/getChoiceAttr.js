
function getCID() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", list_url, true);
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

function getChoiceDesc() {
	
}

function getAltDesc(altNum) {
	
}

