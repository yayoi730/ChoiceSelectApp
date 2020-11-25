package rando.randomness.app.demo;


import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.CreateMemberRequest;
import choice.select.app.http.CreateMemberResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Member;

public class CreateMemberHandler implements RequestHandler<CreateMemberRequest, CreateMemberResponse> {
LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	
	@Override
	public CreateMemberResponse handleRequest(CreateMemberRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CreateMemberHandler");
		logger.log(req.toString());
		
		ChoiceDAO dao =  new ChoiceDAO();
		
		boolean fail = false;
		boolean loaded = true;
		String failMessage = "";
		Member loadedMember = null;
		
		try {
			loadedMember = loadMemberFromRDS(req.getTID(), req.getName());
			loaded = true;
		} 
		catch (Exception e) {
			loaded = false;
		}
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		CreateMemberResponse response;
		if (fail) {
			response = new CreateMemberResponse("",400, failMessage);
		} 
		else if(loaded == false){
			Member newMember = new Member(req.getName(), req.getPassword()); 			
			try {
				dao.addMember(newMember, req.tID);
				response = new CreateMemberResponse("operation successful");  // success
			} catch (Exception e) {
				response = new CreateMemberResponse("",400, failMessage);  // success
			}
		}
		else {response = new CreateMemberResponse("Member Already Exist",400, failMessage);}

		return response; 
	}


	private Member loadMemberFromRDS(String tID, String name) {
		ChoiceDAO dao =  new ChoiceDAO();
		try {
			ArrayList<Member> members =  dao.retrieveMembers(tID);
			Member possibleMember = new Member(name);
			for(int m = 0; m < members.size(); m++) {
				if(members.get(m).equals(possibleMember)) {
					return members.get(m);
				}
			}
			return null;
		}
		catch(Exception e) {
			return null;
		}
		
	}


}
