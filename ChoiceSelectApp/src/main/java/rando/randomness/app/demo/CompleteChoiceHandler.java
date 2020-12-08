package rando.randomness.app.demo;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import choice.select.app.http.CompleteChoiceRequest;
import choice.select.app.http.CompleteChoiceResponse;
import choice.select.app.http.CreateTeamResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Choice;


public class CompleteChoiceHandler implements RequestHandler<CompleteChoiceRequest, CompleteChoiceResponse> {
	LambdaLogger logger;
	
	@Override
	public CompleteChoiceResponse handleRequest(CompleteChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CompleteChoiceHandler");
		logger.log(req.toString());
		CompleteChoiceResponse response;
		ChoiceDAO dao = new ChoiceDAO();
		String tid = req.gettID();
		Choice c;
		try {
			c = dao.retrieveChoice(tid);
			c.setCompletionDate(newTimestamp);
			c.setFinalChoice(req.getChoiceNum());
			dao.completeChoice(c);
			response = new CompleteChoiceResponse();
		} catch (Exception e) {
			e.printStackTrace();
			response = new CompleteChoiceResponse(400,"");
		}
		return response;
	}

}