package rando.randomness.app.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import choice.select.app.http.AddApproverRequest;
import choice.select.app.http.AddApproverResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Team;

//NOTE: team ID and choice ID are interchangeable

public class AddApproverHandler implements RequestHandler<AddApproverRequest, AddApproverResponse>{

	LambdaLogger logger;
	
	@Override
	public AddApproverResponse handleRequest(AddApproverRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of AddApproverHandler");
		logger.log(req.toString());
		
		AddApproverResponse response;
		
		try {
			Team t = findTeam(req.getCid());
			response = new AddApproverResponse(t);		//successful
		} catch (Exception e) {
			e.printStackTrace();
			response = new AddApproverResponse(400, "add approver error: " + e.toString());
		}
		
		return response;
	}
	
	Team findTeam(String cid) {
		ChoiceDAO dao = new ChoiceDAO();
		Team t;
		try {
			t = dao.retrieveTeam(cid);
		} catch (Exception e) {
			e.printStackTrace();
			t = null;
		}
		return t;
	}

}
