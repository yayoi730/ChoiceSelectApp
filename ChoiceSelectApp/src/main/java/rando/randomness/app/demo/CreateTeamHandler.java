package rando.randomness.app.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.CreateMemberRequest;
import choice.select.app.http.CreateMemberResponse;
import choice.select.app.http.CreateTeamRequest;
import choice.select.app.http.CreateTeamResponse;
import choice.select.app.http.GetAltResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;

public class CreateTeamHandler implements RequestHandler<CreateTeamRequest, CreateTeamResponse>{
LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	
	@Override
	public CreateTeamResponse handleRequest(CreateTeamRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CreateMemberHandler");
		logger.log(req.toString());
		
		ChoiceDAO dao =  new ChoiceDAO();
		
		boolean created = false;
		String failMessage = "";
		Choice loadedChoice = null;
		
		try {
			Team t = new Team(req.getMembers(),req.getChoice());
			dao.addTeam(t);
			created = true;
		} 
		catch (Exception e) {
			created = false;
		}
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		CreateTeamResponse response;
		
		if(created == false){response = new CreateTeamResponse("The was does not exist",400, failMessage);
		}
		else {response = new CreateTeamResponse("operation successful");}

		return response; 
	}
	
	private Choice loadChoiceFromRDS(String id) throws Exception {
		ChoiceDAO dao =  new ChoiceDAO();
		Choice ldChoice = null;
		ldChoice = dao.retrieveChoice(id);
		return ldChoice;
	}
}
