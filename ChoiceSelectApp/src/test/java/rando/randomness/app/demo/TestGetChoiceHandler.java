package rando.randomness.app.demo;

import org.junit.Assert;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import choice.select.app.http.GetChoiceRequest;
import choice.select.app.http.GetChoiceResponse;

public class TestGetChoiceHandler {
	
	Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }
	void testSuccessInput(String incoming) {
		GetChoiceHandler handler = new GetChoiceHandler();
		GetChoiceRequest req = new Gson().fromJson(incoming, GetChoiceRequest.class);
	       
		GetChoiceResponse resp = handler.handleRequest(req, createContext("registerChoice"));
        Assert.assertEquals(200, resp.httpCode);
	}

}
