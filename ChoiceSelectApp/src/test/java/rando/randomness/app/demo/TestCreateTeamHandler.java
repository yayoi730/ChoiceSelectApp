package rando.randomness.app.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import choice.select.app.http.CreateTeamRequest;
import choice.select.app.http.CreateTeamResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Feedback;
import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;

public class TestCreateTeamHandler extends LambdaTest {
	
	void testSuccessInput(String incoming) throws IOException {
    	CreateTeamHandler handler = new CreateTeamHandler();
    	CreateTeamRequest req = new Gson().fromJson(incoming, CreateTeamRequest.class);
       
        CreateTeamResponse resp = handler.handleRequest(req, createContext("createTeam"));
        
        Assert.assertEquals(200, resp.httpCode);
    }
	
	@Test
	public void test1() {
		
		CreateTeamRequest ctr = new CreateTeamRequest();
		ctr.setName("Liliana");
		ctr.setPassword("myPass");
		ctr.setcDesc("some choice");
		ArrayList<String> altsString = new ArrayList<String>();
		altsString.add("yes");
		altsString.add("no");
		ctr.setAltDesc(altsString);
		ctr.setTeamSize(7);
		
		String SAMPLE_INPUT_STRING = new Gson().toJson(ctr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
		
	}
	
	@Test
	public void test2() {
		String s = "{\"name\" : \"team\",\"password\" : \"\",\"cDesc\" : \"a choice\",\"altDesc\" : [\"yes\", \"no\"],\"teamSize\" : 7}";
		
		 try {
	        	testSuccessInput(s);
	        } catch (IOException ioe) {
	        	Assert.fail("Invalid:" + ioe.getMessage());
	        }
	}

}
