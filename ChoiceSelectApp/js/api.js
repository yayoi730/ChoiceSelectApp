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
var getTeam_url = base + "getTeam";						//GET


//idk if needed
var getAlt_url = base + "getAlt";						//GET
var addApprover_url = base + "addApprover";				//POST