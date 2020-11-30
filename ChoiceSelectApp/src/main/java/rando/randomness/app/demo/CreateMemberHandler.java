package rando.randomness.app.demo;


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
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		CreateMemberResponse response;
		
		try {
			if(req.getPassword() == "" || req.getPassword() == null) {
				Member nM = createMember(req.getName(),req.getTID());
				response = new CreateMemberResponse(nM);
			}
			else {
				Member nM = createMember(req.getName(),req.getPassword(), req.getTID());
				response = new CreateMemberResponse(nM);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response = new CreateMemberResponse(400,"Member Creation ERR");
		}

		return response; 
	}
	
	Member createMember(String name, String pass, String tID) throws Exception{
		Member m = new Member(name, pass);
		ChoiceDAO dao =  new ChoiceDAO();
		dao.addMember(m, tID);
		return m;
	}
	Member createMember(String name, String tID) throws Exception{
		Member m = new Member(name);
		ChoiceDAO dao =  new ChoiceDAO();
		dao.addMember(m, tID);
		return m;
	}

}
