//This url is the end point for the API Gateway specific to our ChoiceSelectApp project
var base = "https://cal3lca3ve.execute-api.us-east-2.amazonaws.com/alpha/";

//format: loginUser/{choiceId}/{username}/{password}
//handles checking if user exists for the given choice or creating new
//member object is user does not exist yet
var login_url = base + "loginUser";											//POST

//format: createTeam/{name}/{password}/{choiceDesc}/{teamSize}
//handles creating new alternatives, choice, member, and team objects
var createTeam_url = base + "createTeam";									//POST

//format: getAlt/{altNum}
//returns alternative object with the given altNum (cid is already known)
var getAlt_url = base + "getAlt";											//GET

//format: addApprover/{name}
//handles adding given name to list of approvers
var addApprover_url = base + "addApprover";									//POST

//format: getChoice/{choiceID}
//returns choice object with the given cid
var getChoice_url = base + "getChoice";										//GET

