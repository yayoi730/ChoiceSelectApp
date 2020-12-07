package rando.randomness.app.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.DeleteChoiceRequest;
import choice.select.app.http.DeleteChoiceResponse;
import rando.randomness.app.demo.db.ChoiceDAO;

public class DeleteChoiceHandler  implements RequestHandler<DeleteChoiceRequest, DeleteChoiceResponse>{

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	String errMsg = "Report Creation Failed";
	
	@Override
	public DeleteChoiceResponse handleRequest(DeleteChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of DeleteChoiceHandler");
		logger.log(req.toString());
		
		DeleteChoiceResponse response;
		try {
			ChoiceDAO dao = new ChoiceDAO();
			dao.deleteChoice(req.gettID());
			response = new DeleteChoiceResponse();
		}
		catch(Exception e) {
			response = new DeleteChoiceResponse(400, errMsg);
		}
		
		logger.log(response.toString());
		
		return response;
	}
	

}
