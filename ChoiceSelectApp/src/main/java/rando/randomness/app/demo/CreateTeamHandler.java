package rando.randomness.app.demo;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.CreateTeamRequest;
import choice.select.app.http.CreateTeamResponse;
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
		

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		CreateTeamResponse response;
		try {
			Choice c = createChoice(req.getChoice().getDescription(), req.getChoice().getAlternativeList());
			Team t = createTeam(c, req.getTeamSize(), req.getMembers());
			response = new CreateTeamResponse(t);
		}
		catch (Exception e) {
			e.printStackTrace();
			response = new CreateTeamResponse(400,"");
		}

		return response; 
	}
	
	Team createTeam(Choice c, int teamSize, ArrayList<Member> m) throws Exception{
		ChoiceDAO dao = new ChoiceDAO();
		Team t = new Team(m, c);
		dao.addTeam(t);
		return t;
	}
	
	Choice createChoice(String description, ArrayList<Alternative> alternatives) throws Exception { 
		if (logger != null) { logger.log("in createConstant"); }	
		java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		Choice c = new Choice(description, alternatives, ts);
		for (Alternative a : alternatives) {
			c.addAlternative(a);
		}
		return c;
	}
}
