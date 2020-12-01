package rando.randomness.app.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import choice.select.app.http.CreateChoiceRequest;
import choice.select.app.http.CreateChoiceResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Team;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;

	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
		//initially assume not loaded and operation failed
		boolean loaded = false;
		boolean fail = true;
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CreateChoiceHandler");
		logger.log(req.toString());

		String failMessage = "";
		Choice loadedChoice = null;
		
		try {
			loadedChoice = loadChoiceFromRDS(req.getCid());
			loaded = true;
		} 
		catch (Exception e) {
			loaded = false;
			fail = true;
		}
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		CreateChoiceResponse response;
		if (fail) {
			response = new CreateChoiceResponse(400, failMessage);
		} 
		else if(loaded == false){
			
			try {
				ChoiceDAO dao =  new ChoiceDAO();
				Choice newChoice = new Choice(req.getDesc(), req.getCreationDate());
				dao.addChoice(newChoice , req.getCid());
				ArrayList<Alternative> alts = req.getAlts(); 
				for(int y = 0; y < alts.size(); y++){
					dao.addAlternative(alts.get(y), req.getCid());
				}
				response = new CreateChoiceResponse(newChoice);  // success
			} catch (Exception e) {
				response = new CreateChoiceResponse(400, failMessage);  //is this meant to be for unexpected error?
			}
		}
		else {response = new CreateChoiceResponse(400, "Choice Loaded");}	//UNSURE ABOUT STATUS CODE

		return response; 
	}


	private Choice loadChoiceFromRDS(String id) throws Exception {
		ChoiceDAO dao =  new ChoiceDAO();
		Choice ldChoice = null;
		ldChoice = dao.retrieveChoice(id);
		return ldChoice;
	}

}
