package rando.randomness.app.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import choice.select.app.http.LoginRequest;
import choice.select.app.http.LoginResponse;

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
    	
    	LoginRequest rcr = new LoginRequest();
    	rcr.setCid("3f60ad5b-661e-43c3-a36b-24f202914a4e");
    	rcr.setName("Sandy");
    	rcr.setPassword("Optional");

    	// Doesn't work multiple times! or it does, you get multiple team members!!!
    	
        String SAMPLE_INPUT_STRING = new Gson().toJson(rcr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
}
