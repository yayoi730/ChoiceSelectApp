

function handleAltClick(e, altNum) {
	
	var query = new URLSearchParams(window.location.search);
	query.set("alt", Number(altNum));
	query.set("tid", query.get("tid"));
	query.set("user", query.get("user"));
	window.location.href = "https://s3.us-east-2.amazonaws.com/choice.select.app/html/mainUI.html" + "?" + query.toString();

}


