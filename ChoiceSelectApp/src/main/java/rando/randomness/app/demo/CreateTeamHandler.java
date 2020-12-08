package rando.randomness.app.demo;

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
		logger.log("Loading Java Lambda handler of CreateTeamHandler");
		logger.log(req.toString());
		

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		CreateTeamResponse response;
		try {			
			Choice c = createChoice(req.getcDesc(), createAlts(req.getAltDesc()));
			Team t = createTeam(c, req.getTeamSize(), new Member(req.getName(),req.getPassword()));
			response = new CreateTeamResponse(t);
		}
		catch (Exception e) {
			e.printStackTrace();
			response = new CreateTeamResponse(400,"");
		}

		return response; 
	}
	
	Team createTeam(Choice c, int teamSize, Member m) throws Exception{
		ChoiceDAO dao = new ChoiceDAO();	
		Team t = new Team(m, c, teamSize);
		t = dao.addTeam(t);
		return t;
	}
	
	Choice createChoice(String description, ArrayList<Alternative> alternatives) throws Exception { 
		if (logger != null) { logger.log("in createConstant"); }	
		java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		Choice c = new Choice(description, alternatives, ts);
		if (logger != null) {
			logger.log("d = " + description);
		}
		return c;
	}
	
	ArrayList<Alternative> createAlts(ArrayList<String> descs) {
		ArrayList<Alternative> alts = new ArrayList<Alternative>();
		for(int i = 0; i < descs.size(); i++) {
			Alternative a = new Alternative(descs.get(i));
			a.setAltNumber(i+1);
			alts.add(a);
		}
		return alts;
	}
	

}
