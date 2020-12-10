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
import rando.randomness.app.demo.model.Team;

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
		
		boolean login = false;
		boolean newUser = false;
		String failMessage = "Login Failed";
		Member loadedMember = null;	
		Member newMember = null;	
		Team loadedTeam = null;
		

		try {
			loadedMember = loadMemberFromRDS(req.getCid(), req.getName());
			loadedTeam = loadTeamFromRDS(req.getCid());
			if(loadedTeam.getChoice().getCompleted())
			{
				//throw new Exception("Choice Complete");
			}
			if(req.getPassword().equals("")) {
				if(loadedMember.getPassword().equals("")) {
					login = true;
				}
			}
			else if(loadedMember.getPassword().equals(req.getPassword()))
			{
				login = true;
			}
		} 
		catch (Exception e) {
			login = false;
			try {
				loadedTeam = dao.retrieveTeam(req.getCid());
				if(loadedTeam.getChoice().getCompleted())
				{
					//throw new Exception("Choice Complete");
				}
				if(loadedTeam.getMembers().size() < loadedTeam.getTeamSize()){
					newMember = new Member(req.getName(), req.getPassword());
					dao.addMember(newMember, req.getCid());
					newUser = true;		
				}
			}
			catch (Exception b) {
				login = false;
				newUser = false;
			}
		}		
		
		
		LoginResponse response;
		if(login){
			response = new LoginResponse(loadedMember, loadedTeam); 
		}
		else if(newUser) {
			response = new LoginResponse(newMember, loadedTeam);
		}
		else {response = new LoginResponse(400, failMessage);}

		return response; 
	}

	private Member loadMemberFromRDS(String tID, String name) throws Exception{
		ChoiceDAO dao =  new ChoiceDAO();
		ArrayList<Member> members =  dao.retrieveMembers(tID);
		Member possibleMember = new Member(name);
		for(int m = 0; m < members.size(); m++) {
			if(members.get(m).getName().equals(possibleMember.getName())) {
				return members.get(m);
			}
		}
		return null;	
	}
	
	private Team loadTeamFromRDS(String tID) throws Exception{
		ChoiceDAO dao =  new ChoiceDAO();
		Team t = null;
		t = dao.retrieveTeam(tID);
		return t;
	}

}