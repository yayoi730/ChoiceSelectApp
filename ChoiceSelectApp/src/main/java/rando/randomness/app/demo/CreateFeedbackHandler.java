package rando.randomness.app.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.CreateFeedbackRequest;
import choice.select.app.http.CreateFeedbackResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Feedback;


public class CreateFeedbackHandler implements RequestHandler<CreateFeedbackRequest, CreateFeedbackResponse>{

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	String errMsg = "Report Creation Failed";
	
	@Override
	public CreateFeedbackResponse handleRequest(CreateFeedbackRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CreateFeedbackHandler");
		logger.log(req.toString());
		
		CreateFeedbackResponse response;
		try {
			response = new CreateFeedbackResponse(createFeedback(req.getaID(), req.getCreator(), req.getDescription()));
		}
		catch(Exception e) {
			response = new CreateFeedbackResponse(400, errMsg);
		}
		
		logger.log(response.toString());
		
		return response;
	}
	
	public Alternative createFeedback(String aID, String creator, String desc) throws Exception{
		ChoiceDAO dao = new ChoiceDAO();
		Feedback f = new Feedback(creator, desc);
		dao.addFeedback(f, aID, creator);
		return dao.retrieveAlternative(aID);
	}

}
