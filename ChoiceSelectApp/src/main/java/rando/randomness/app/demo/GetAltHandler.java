package rando.randomness.app.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.GetAltRequest;
import choice.select.app.http.GetAltResponse;
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
		String failMessage = "unexpected error retriving alternative";
	
		Alternative loadedAlt = null;
		GetAltResponse response;

		try {
			loadedAlt = loadAlt(req.getAltNumber(), req.getcID());
			if(loadedAlt.getDescription().equals(""))
			{
				throw new Exception();
			}
			response = new GetAltResponse(loadedAlt);
		} 
		catch (Exception e) {
			response = new GetAltResponse(400, "Could not retrieve alternative");		
		}
		
		return response; 
	}

	private Alternative loadAlt(int altNumber, String cid) throws Exception{
		Alternative a = null;
		ArrayList<Alternative> loadedAlts = loadAltFromRDS(cid);
		for(Alternative alt : loadedAlts)
		{
			if(alt.getAltNumber() == altNumber) {
				a = alt;
			}
		}
		return a;
	}

	private ArrayList<Alternative> loadAltFromRDS(String cid) throws Exception {
		ChoiceDAO dao =  new ChoiceDAO();
		ArrayList<Alternative> alts = dao.retrieveAlternatives(cid);
		return alts;
	}
}
