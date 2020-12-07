package rando.randomness.app.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import choice.select.app.http.CreateFeedbackRequest;
import choice.select.app.http.CreateFeedbackResponse;

public class CreateFeedbackHandler implements RequestHandler<CreateFeedbackRequest, CreateFeedbackResponse>{

	@Override
	public CreateFeedbackResponse handleRequest(CreateFeedbackRequest input, Context context) {
		
		return null;
	}

}
