package rando.randomness.app.demo;


import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import choice.select.app.http.CreateTeamRequest;
import choice.select.app.http.CreateTeamResponse;

public class LambdaTest {
	
	/**
	 * Helper method that creates a context that supports logging so you can test lambda functions
	 * in JUnit without worrying about the logger anymore.
	 * 
	 * @param apiCall      An arbitrary string to identify which API is being called.
	 * @return
	 */
	Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

	@Test
	public void testCreateTeamHandler() {
		String SAMPLE_INPUT_STRING = "{\"arg1\": \"17\", \"arg2\": \"19\"}";
		String RESULT = "36.0";
		ArrayList<String> alts = new ArrayList<String>();
		alts.add("alt1");
		alts.add("alt2");
		alts.add("alt3");
		
		CreateTeamRequest req = new CreateTeamRequest("Ryan", "", "Description",alts,4);
		CreateTeamHandler handler = new CreateTeamHandler();
		CreateTeamResponse res = handler.handleRequest(req, createContext("string"));
		
		assertEquals(res.httpCode, 200);
		
	}
}
