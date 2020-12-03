package rando.randomness.app.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import choice.select.app.http.CreateReportRequest;
import choice.select.app.http.CreateReportResponse;


public class TestReportHandler extends LambdaTest {
	
	void testSuccessInput(String incoming) throws IOException {
		CreateReportHandler handler = new CreateReportHandler();
		CreateReportRequest req = new Gson().fromJson(incoming, CreateReportRequest.class);
		CreateReportResponse resp = handler.handleRequest(req, createContext(""));
        Assert.assertEquals(200, resp.httpCode);
    }

	@Test
	public void test1() {
		CreateReportRequest rrq = new CreateReportRequest();
		rrq.setWantReports(true);
		String s = new Gson().toJson(rrq);
		try{
			testSuccessInput(s);
		}
		catch(Exception e) {}
	}
	
	//@Test
	public void test2() {
		
	}
}
