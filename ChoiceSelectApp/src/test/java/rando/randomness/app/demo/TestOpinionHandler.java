package rando.randomness.app.demo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import choice.select.app.http.OpinionRequest;
import choice.select.app.http.OpinionResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;

public class TestOpinionHandler extends LambdaTest{

	void testSuccessInput(String incoming) throws IOException {
    	OpinionHandler handler = new OpinionHandler();
    	OpinionRequest req = new Gson().fromJson(incoming, OpinionRequest.class);
       
        OpinionResponse resp = handler.handleRequest(req, createContext("makeOpinion"));
        
        Assert.assertEquals(200, resp.httpCode);
    }
	
	@Test
	public void test1() {
		ArrayList<Member> mems = new ArrayList<>();
    	Member mem1 = new Member("Sandy", "Optional");
    	mems.add(mem1);
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	Choice c = new Choice("A choice", timestamp);
    	Alternative a1 = new Alternative("Alt 1");
    	a1.setAltNumber(1);
    	c.addAlternative(a1);
    	Alternative a2 = new Alternative("Alt 2");
    	a2.setAltNumber(2);
    	c.addAlternative(a2);
    	Team t = new Team(mems, c);
    	t.setTeamSize(5);
    	ChoiceDAO dao = new ChoiceDAO();
    	try {
			t = dao.addTeam(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OpinionRequest or1 = new OpinionRequest();
		or1.setaID(t.getChoice().getAlt(1).getAID());
		or1.setApproves(true);
		or1.setProver("Sandy");
		String s1 = new Gson().toJson(or1);
		
		OpinionRequest or2 = new OpinionRequest();
		or2.setaID(t.getChoice().getAlt(1).getAID());
		or2.setApproves(true);
		or2.setProver("Rupert");
		String s2 = new Gson().toJson(or2);
		
		try {
			testSuccessInput(s1);
		} catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
		
		try {
			testSuccessInput(s2);
		} catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
	}
}
