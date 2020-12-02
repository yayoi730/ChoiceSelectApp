//GET choice 
function initialize() {
	
	var urlParams = new URLSearchParams(window.location.search);	//grab current url
	currentId = urlParams.get("cid");								//grab cid as string from url query
	
	//format data as JSON
	var data = {}
	data["cid"] = currentId;
	var js = JSON.stringify(data);
	console.log("JS: " + js);	
	
	//submit getChoice request
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getTeam_url, true)
	xhr.send(js);
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
	console.log("result:" + result);
	var js = JSON.parse(result);
	
	var status = js["httpCode"];
	var error = js["error"];
	
	if (status == 200) {
		console.log("create choice success");
		
		var team = js["team"];					//get team object from response
		var choice = team["choice"];			//get choice from team
		var teamSize = team["teamSize"];		//get team size from team
		var choiceDesc = choice["description"];	//get choice desc from choice
		var cid = choice["ID"];					//get cid from choice
		var alts = choice["alternativeList"];	//get alts from choice
		
		document.getElementById("cidLabel").innerHTML = ;
		document.getElementById("descLabel").innerHTML = ;
		
	} else {
		alert("Error loading choice; retry login");
		console.log("error getting choice information");
	}
	
}

