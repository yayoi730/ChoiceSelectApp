package rando.randomness.app.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import choice.select.app.http.CreateChoiceRequest;
import choice.select.app.http.CreateChoiceResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Choice;


public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;

	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CreateChoiceHandler");
		logger.log(req.toString());
		
		ChoiceDAO dao =  new ChoiceDAO();
		
		boolean fail = false;
		boolean loaded = true;
		String failMessage = "";
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
		CreateChoiceResponse response;
		if (fail) {
			response = new CreateChoiceResponse("",400, failMessage);
		} 
		else if(loaded == false){
			Choice newChoice = new Choice(req.getDescription(), req.getCreationDate());
			try {
				dao.addChoice(newChoice , req.getID());
				response = new CreateChoiceResponse("operation successful");  // success
			} catch (Exception e) {
				response = new CreateChoiceResponse("",400, failMessage);  // success
			}
		}
		else {response = new CreateChoiceResponse("Choice Loaded");}

		return response; 
	}


	private Choice loadChoiceFromRDS(String id) throws Exception {
		ChoiceDAO dao =  new ChoiceDAO();
		Choice ldChoice = null;
		ldChoice = dao.retrieveChoice(id);
		return ldChoice;
	}



}
