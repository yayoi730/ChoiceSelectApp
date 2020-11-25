package rando.randomness.app.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.GetAltRequest;
import choice.select.app.http.GetAltResponse;
import choice.select.app.http.GetChoiceRequest;
import choice.select.app.http.GetChoiceResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;

public class GetAltHandler implements RequestHandler<GetAltRequest, GetAltResponse> {
	
	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;

	@Override
	public GetAltResponse handleRequest(GetAltRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of GetChoiceHandler");
		logger.log(req.toString());
		
		ChoiceDAO dao =  new ChoiceDAO();
		
		
		boolean loaded = true;
		String failMessage = "unexpected error retriving choice";
		ArrayList<Alternative> loadedAlts = new ArrayList<Alternative>();
		
		try {
			loadedAlts = loadAltFromRDS(req.getAID());
			loaded = true;
		} 
		catch (Exception e) {
			loaded = false;
		}
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		GetAltResponse response;
		
		if(loaded == false){response = new GetAltResponse("The Choice does not exist",400, failMessage);
		}
		else {response = new GetAltResponse("operation successful");}

		return response; 
	}


	private ArrayList<Alternative> loadAltFromRDS(String id) throws Exception {
		ChoiceDAO dao =  new ChoiceDAO();
		ArrayList<Alternative> alts = dao.retrieveAlternatives(id);
		return alts;
	}
}
