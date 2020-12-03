package rando.randomness.app.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.CreateReportResponse;
import choice.select.app.http.OpinionRequest;
import choice.select.app.http.OpinionResponse;


public class OpinionHandler implements RequestHandler<OpinionRequest, OpinionResponse>{
	LambdaLogger logger;
	private AmazonS3 s3 = null;
	String errMsg = "Opinion Addition Failed";

	@Override
	public OpinionResponse handleRequest(OpinionRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of OpinionHandler");
		logger.log(req.toString());
		
		OpinionResponse response;
		try {
			response = new OpinionResponse(createReports(req.getWantReports()));
		}
		catch(Exception e) {
			response = new OpinionResponse(400, errMsg);
		}
		
		return response;
	}
	

}
