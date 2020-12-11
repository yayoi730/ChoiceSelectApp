package rando.randomness.app.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import choice.select.app.http.GetAltRequest;
import choice.select.app.http.GetAltResponse;

public class TestGetAltHandler extends LambdaTest{
	void testSuccessInput(String incoming) throws IOException {
    	GetAltHandler handler = new GetAltHandler();
    	GetAltRequest req = new Gson().fromJson(incoming, GetAltRequest.class);
        GetAltResponse resp = handler.handleRequest(req, createContext("getAlt"));
        
        Assert.assertEquals(200, resp.httpCode);
    }

	@Test
	public void test1() {
		GetAltRequest gar = new GetAltRequest();
		gar.setAltNumber(1);
		gar.setcID("37bbb073-4f3d-448c-a440-bcf09af8a948");
		
		String s = new Gson().toJson(gar);
		
		try {
			testSuccessInput(s);
		} catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
	}
	
	@Test
	public void test2() {
		GetAltRequest gar = new GetAltRequest();
		gar.setAltNumber(5);
		gar.setcID("37bbb073-4f3d-448c-a440-bcf09af8a948");
		
		String s = new Gson().toJson(gar);

		try {
			testSuccessInput(s);
		} catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
	}
}