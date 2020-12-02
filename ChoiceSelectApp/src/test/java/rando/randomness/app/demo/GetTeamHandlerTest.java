package rando.randomness.app.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import choice.select.app.http.GetTeamRequest;
import choice.select.app.http.GetTeamResponse;

public class GetTeamHandlerTest extends LambdaTest {
	
	void testSuccessInput(String incoming) throws IOException {
    	GetTeamHandler handler = new GetTeamHandler();
    	GetTeamRequest req = new Gson().fromJson(incoming, GetTeamRequest.class);
        GetTeamResponse resp = handler.handleRequest(req, createContext("getTeam"));
        
        Assert.assertEquals(200, resp.httpCode);
    }

	@Test
	public void test1() {
		String s = "{\"tid\" : \"37bbb073-4f3d-448c-a440-bcf09af8a948\"}";

		try {
			testSuccessInput(s);
		} catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
	}
	
	@Test
	public void test2() {
		GetTeamRequest gtr = new GetTeamRequest();
		gtr.setTid("37bbb073-4f3d-448c-a440-bcf09af8a948");
		
		String s = new Gson().toJson(gtr);

		try {
			testSuccessInput(s);
		} catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
	}

}
