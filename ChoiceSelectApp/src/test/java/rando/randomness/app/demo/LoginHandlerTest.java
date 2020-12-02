package rando.randomness.app.demo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import choice.select.app.http.LoginRequest;
import choice.select.app.http.LoginResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;

public class LoginHandlerTest extends LambdaTest{
	
	void testSuccessInput(String incoming) throws IOException {
		LoginHandler handler = new LoginHandler();
		LoginRequest req = new Gson().fromJson(incoming, LoginRequest.class);
       
		LoginResponse resp = handler.handleRequest(req, createContext("registerChoice"));
        Assert.assertEquals(200, resp.httpCode);
    }

    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	ArrayList<Member> mems = new ArrayList<>();
    	Member mem1 = new Member("Sandy", "Optional");
    	mems.add(mem1);
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	Choice c = new Choice("A choice", timestamp);
    	Team t = new Team(mems, c);
    	t.setTeamSize(1);
    	ChoiceDAO dao = new ChoiceDAO();
    	try {
			t = dao.addTeam(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	LoginRequest rcr = new LoginRequest();
    	rcr.setCid(t.getTID());
    	rcr.setName("Richard");
    	rcr.setPassword("");

    	
        String SAMPLE_INPUT_STRING = new Gson().toJson(rcr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
}
