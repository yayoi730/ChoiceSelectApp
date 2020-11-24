package rando.randomness.app.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.GetChoiceRequest;
import choice.select.app.http.GetChoiceResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Choice;

public class GetChoiceHandler implements RequestHandler<GetChoiceRequest, GetChoiceResponse> {
	
	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;

	@Override
	public GetChoiceResponse handleRequest(GetChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of GetChoiceHandler");
		logger.log(req.toString());
		
		ChoiceDAO dao =  new ChoiceDAO();
		
		boolean fail = false;
		boolean loaded = true;
		String failMessage = "unexpected error retriving choice";
		Choice loadedChoice = null;
		
		try {
			loadedChoice = loadChoiceFromRDS(req.getID());
			loaded = true;
		} 
		catch (Exception e) {
			loaded = false;
		}
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		GetChoiceResponse response;
		if (fail) {
			response = new GetChoiceResponse("",400, failMessage);
		} 
		else if(loaded == false){response = new GetChoiceResponse("",400, failMessage);  // success
		}
		else {response = new GetChoiceResponse("operation successful");}

		return response; 
	}


	private Choice loadChoiceFromRDS(String id) throws Exception {
		ChoiceDAO dao =  new ChoiceDAO();
		Choice ldChoice = null;
		ldChoice = dao.retrieveChoice(id);
		return ldChoice;
	}
}
