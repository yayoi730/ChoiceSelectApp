package rando.randomness.app.demo;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import choice.select.app.http.CreateFeedbackRequest;
import choice.select.app.http.CreateFeedbackResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Team;

public class FeedbackTest extends LambdaTest {
	
	void testSuccessInput(String incoming) throws IOException {
    	CreateFeedbackHandler handler = new CreateFeedbackHandler();
    	CreateFeedbackRequest req = new Gson().fromJson(incoming, CreateFeedbackRequest.class);
       
        CreateFeedbackResponse resp = handler.handleRequest(req, createContext("createFeedback"));
        
        Assert.assertEquals(200, resp.httpCode);
    }
	
	@Test
	public void test1() {		
		CreateFeedbackRequest cfr = new CreateFeedbackRequest();
		cfr.setCreator("Test");
		cfr.setDescription("myDesc");
		ChoiceDAO dao = new ChoiceDAO();
		ArrayList<Alternative> alts = new ArrayList<>();
		alts.add(new Alternative("testDesc"));
		Team t = new Team(null, new Choice(null, alts, null));
		try{
			dao.addTeam(t);
			cfr.setaID(alts.get(0).getAID());
		}
		catch(Exception e) {
			
		}
		
		String SAMPLE_INPUT_STRING = new Gson().toJson(cfr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
		
	}
	
	@Test
	public void test2() {
		
	}

}
