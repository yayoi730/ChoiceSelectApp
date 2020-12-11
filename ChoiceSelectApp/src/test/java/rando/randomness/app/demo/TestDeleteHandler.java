package rando.randomness.app.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import choice.select.app.http.DeleteChoiceRequest;
import choice.select.app.http.DeleteChoiceResponse;

class TestDeleteHandler extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
    	DeleteChoiceHandler handler = new DeleteChoiceHandler();
    	DeleteChoiceRequest req = new Gson().fromJson(incoming, DeleteChoiceRequest.class);
       
        DeleteChoiceResponse resp = handler.handleRequest(req, createContext("createTeam"));
        
        Assert.assertEquals(200, resp.httpCode);
    }
	
	@Test
	public void test1() {
		DeleteChoiceRequest rrq = new DeleteChoiceRequest();
		rrq.daysOld = 3;
		String s = new Gson().toJson(rrq);
		try{
			testSuccessInput(s);
		}
		catch(Exception e) {}
	}
	
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
