package rando.randomness.app.demo;

import java.sql.Timestamp;
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
	
	Choice createChoice(String description, ArrayList<Alternative> alternatives) throws Exception { 
		if (logger != null) { logger.log("in createConstant"); }
		ChoiceDAO dao = new ChoiceDAO();
		Timestamp time = new Timestamp(0);
		Choice c = new Choice(description, time);
		for (Alternative a : alternatives) {
			c.addAlternative(a);
		}
	
		//if (dao.addChoice(c)) {
	//		return c;
		//} else {
			return null;
		//}
	}

	@Override 
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		CreateChoiceResponse response;
		try {
			Choice ch = createChoice(req.getDescription(), req.getAlternatives());
			response = new CreateChoiceResponse(ch);
		} catch (Exception e) {
			e.printStackTrace();
			response = new CreateChoiceResponse(400, e.getMessage());
		}

		return response;
	}

}
