package rando.randomness.app.demo;


import java.sql.Timestamp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.CompleteChoiceRequest;
import choice.select.app.http.CompleteChoiceResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Choice;


public class CompleteChoiceHandler implements RequestHandler<CompleteChoiceRequest, CompleteChoiceResponse> {
	LambdaLogger logger;
	private AmazonS3 s3 = null;
	
	@Override
	public CompleteChoiceResponse handleRequest(CompleteChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CompleteChoiceHandler");
		logger.log(req.toString());
		CompleteChoiceResponse response;
		Timestamp completionDate = new Timestamp(System.currentTimeMillis());
		ChoiceDAO dao = new ChoiceDAO();
		String tid = req.gettID();
		Choice c;
		try {
			c = dao.retrieveChoice(tid);
			c.setCompletionDate(completionDate);
			c.setFinalChoice(req.getChoiceNum());
			dao.completeChoice(c);
			response = new CompleteChoiceResponse(completionDate.toString());
		} catch (Exception e) {
			e.printStackTrace();
			response = new CompleteChoiceResponse(400,"");
		}
		return response;
	}

}