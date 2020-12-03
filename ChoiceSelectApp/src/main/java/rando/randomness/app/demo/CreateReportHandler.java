package rando.randomness.app.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.CreateReportRequest;
import choice.select.app.http.CreateReportResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Report;

public class CreateReportHandler implements RequestHandler<CreateReportRequest, CreateReportResponse>{

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	String errMsg = "Report Creation Failed";
	
	@Override
	public CreateReportResponse handleRequest(CreateReportRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CreateReportHandler");
		logger.log(req.toString());
		
		CreateReportResponse response;
		try {
			response = new CreateReportResponse(createReports(req.getWantReports()));
		}
		catch(Exception e) {
			response = new CreateReportResponse(400, errMsg);
		}
		
		logger.log(response.toString());
		
		return response;
	}
	
	ArrayList<Report> createReports(boolean wantReports) throws Exception{
		ArrayList<Report> reports = new ArrayList<Report>();
		ChoiceDAO dao = new ChoiceDAO();
		if(wantReports) {
			ArrayList<Choice> choices = dao.retrieveAllChoices();
			for(Choice c : choices) {
				reports.add(new Report(c.getID(), c.getCreationDate().toString(), c.getCompleted()));
			}
		}
		return reports;
	}

}
