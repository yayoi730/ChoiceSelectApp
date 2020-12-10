package rando.randomness.app.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import choice.select.app.http.CreateReportResponse;
import choice.select.app.http.OpinionRequest;
import choice.select.app.http.OpinionResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Member;


public class OpinionHandler implements RequestHandler<OpinionRequest, OpinionResponse>{
	LambdaLogger logger;
	private AmazonS3 s3 = null;
	String errMsg = "Opinion Addition Failed";

	@Override
	public OpinionResponse handleRequest(OpinionRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of OpinionHandler");
		logger.log(req.toString());
		ChoiceDAO dao = new ChoiceDAO();
		OpinionResponse response;
		//How to check and update the database based on request to approve/disapprove
		Alternative alt = null;
		try {
			if(dao.retrieveChoice(req.getcID()).getCompleted()) {
				throw new Exception("Choice Complete");
			}
			dao.handleAppDisRequest(req.getApproves(), req.getaID(), req.getProver());
			alt = dao.retrieveAlternative(req.getaID());
			response = new OpinionResponse(alt);
			return response;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response = new OpinionResponse(400, errMsg);
			return response;
		}
		
	}
	

}
