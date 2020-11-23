package rando.randomness.app.demo;

import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import choice.select.app.http.CreateChoiceRequest;
import choice.select.app.http.CreateChoiceResponse;

import rando.randomness.app.demo.model.Choice;

public class ChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createChoice(String id) throws Exception { 
		if (logger != null) { logger.log("in createChoice"); }
		//ChoiceDAO dao = new ChoiceDAO();
		
		// check if present
		//Choice exist = dao.getChoice(id);
		//Choice constant = new Choice (name, value);
		//if (exist == null) {
			//return dao.addConstant(constant);
		//} else {
			return false;
		//}
	}
	
	/** Create S3 bucket
	 * 
	 * @throws Exception 
	 */
	boolean createSystemConstant(String name, double value) throws Exception {
		if (logger != null) { logger.log("in createSystemConstant"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			logger.log("attach to S3 succeed");
		}

		//String folder = BucketManager.getConstantsFolder() + "/";
		
		byte[] contents = ("" + value).getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		// makes the object publicly visible
		//PutObjectResult res = s3.putObject(new PutObjectRequest(BucketManager.bucket, folder + name, bais, omd)
		//		.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return true;
	}
	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of ChoiceHandler");
		logger.log(req.toString());

		boolean fail = false;
		boolean loaded = true;
		String failMessage = "";
		Choice loadedChoice = null;
		
		try {
			loadedChoice = loadChoice(req.getID());
		} 
		catch (Exception e) {
			req.getID();
			loaded = false;
		}
		
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		CreateChoiceResponse response;
		if (fail) {
			response = new CreateChoiceResponse("",400, failMessage);
		} 
		else if(loaded == false){
			response = new CreateChoiceResponse("New Choice Created");  // success
		}
		else {response = new CreateChoiceResponse("Choice Loaded");}

		return response; 
	}

	private Choice loadChoice(String id) {
		Choice ldChoice = null;
		try {
			ldChoice = loadChoiceFromRDS(id);
			return ldChoice;
		} 
		catch (Exception e) {
			return getChoiceFromBucket(id);
		}
	}

	private Choice getChoiceFromBucket(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	private Choice loadChoiceFromRDS(String id) {
		// TODO Auto-generated method stub
		return null;
	}



}
