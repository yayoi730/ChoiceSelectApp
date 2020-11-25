package rando.randomness.app.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.LoginRequest;
import choice.select.app.http.LoginResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Member;

public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse> {

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	
	@Override
	public LoginResponse handleRequest(LoginRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CreateMemberHandler");
		logger.log(req.toString());
		
		ChoiceDAO dao =  new ChoiceDAO();
		
		boolean fail = false;
		boolean login = false;
		String failMessage = "";
		Member loadedMember = null;		

		try {
			loadedMember = loadMemberFromRDS(req.getTID(), req.getName());
			if(req.getPassword() == "") {
				if(loadedMember.getPassword() == null || loadedMember.getPassword() == "") {
					login = true;
				}
			}
			else if(loadedMember.getPassword() == req.getPassword())
			{
				login = true; 
			}
		} 
		catch (Exception e) {
			login = false;
		}
		
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		LoginResponse response;
		if (fail) {
			response = new LoginResponse("",400, failMessage);
		} 
		else if(login){
			response = new LoginResponse("operation successful"); 
		}
		else {response = new LoginResponse("Member Does Not Exist",400, failMessage);}

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