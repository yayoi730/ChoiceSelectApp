package rando.randomness.app.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import choice.select.app.http.CompleteChoiceRequest;
import choice.select.app.http.CompleteChoiceResponse;
import choice.select.app.http.CreateReportRequest;
import choice.select.app.http.CreateTeamRequest;
import choice.select.app.http.CreateTeamResponse;
import choice.select.app.http.DeleteChoiceRequest;
import choice.select.app.http.DeleteChoiceResponse;

import java.io.IOException;
import java.util.ArrayList;


import com.google.gson.Gson;

class TestCompleteHandler extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
    	CompleteChoiceHandler handler = new CompleteChoiceHandler();
    	CompleteChoiceRequest req = new Gson().fromJson(incoming, CompleteChoiceRequest.class);
       
        CompleteChoiceResponse resp = handler.handleRequest(req, createContext("createTeam"));
        
        Assert.assertEquals(200, resp.httpCode);
    }
	
	@Test
	public void test1() {
		CompleteChoiceRequest rrq = new CompleteChoiceRequest();
		rrq.setChoiceNum(1);
		rrq.settID("118474d4-967b-4c36-8caa-f6c5dc3f1dcc");
		String s = new Gson().toJson(rrq);
		try{
			testSuccessInput(s);
		}
		catch(Exception e) {}
	}
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
