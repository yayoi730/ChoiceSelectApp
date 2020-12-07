//This url is the end point for the API Gateway specific to our ChoiceSelectApp project
var base = "https://cal3lca3ve.execute-api.us-east-2.amazonaws.com/alpha/";

//cid, name, password in payload
//returns Team object
var login_url = base + "loginUser";						//POST

//name, password, cDesc, teamSize, altDesc[] in payload
//returns Team object
var createTeam_url = base + "createTeam";				//POST

//cid in payload
//returns Team object
var getTeam_url = base + "getTeam";						//POST

//altNumber, cID in payload
//returns Alternative object
var getAlt_url = base + "getAlt";						//POST

//wantReports = true
//returns list of Report objects
var getReports_url = base + "getReports";				//POST

//aID, approver, approves in payload
//returns Alternative object
var createOpinion_url = base + "createOpinion";			//POST

//
//returns list of Report objects
var deleteReports_url = base + "";				//POST

//
//return Team object
var complete_url = base + "";					//POST

//tid, altNum, name, desc in payload
//returns Alternative object
var addFeedback_url = base + "";				//POST




