package choice.select.app.http;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import rando.randomness.app.demo.CreateChoiceHandler;
import rando.randomness.app.demo.LambdaTest;

public class CreateChoiceHandlerTest extends LambdaTest{
	
	 void testSuccessInput(String incoming) throws IOException {
	    CreateChoiceHandler handler = new CreateChoiceHandler();
	    CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);
	       
	    //CreateChoiceResponse resp = handler.handleRequest(req, createContext("createChoice"));
	    //Assert.assertEquals(200, resp.httpCode);
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
