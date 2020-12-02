package rando.randomness.app.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import choice.select.app.http.CreateTeamRequest;
import choice.select.app.http.CreateTeamResponse;
import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Feedback;
import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;

public class TestCreateTeamHandler extends LambdaTest {
	
	void testSuccessInput(String incoming) throws IOException {
    	CreateTeamHandler handler = new CreateTeamHandler();
    	CreateTeamRequest req = new Gson().fromJson(incoming, CreateTeamRequest.class);
       
        CreateTeamResponse resp = handler.handleRequest(req, createContext("createTeam"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
	@Test
	public void test1() {
		//creating params for creating a new team
		Timestamp ts = new Timestamp(0);
		Member m = new Member("person");
		Alternative a1 = new Alternative("yes");
		a1.addApprover("person");
		Alternative a2 = new Alternative("no");
		a2.addFeedback(new Feedback(new Timestamp(0), "change this", "person"));
		ArrayList<Alternative> alts = new ArrayList<Alternative>();
		alts.add(a1);
		alts.add(a2);
		Choice c = new Choice("choose one", alts, ts);
		Team team = new Team(m, c, 5);
		
		ChoiceDAO dao = new ChoiceDAO();
		try {
			dao.addTeam(team);
		} catch (Exception e) {
			fail ("add team to dao error: " + e.getMessage());
		}
		
		assertEquals(team.getChoice().getDescription(), "choose one");
		assertEquals(team.getChoice().getAlt(1).getDescription(), "yes");
		assertEquals(team.getChoice().getAlt(1).getFeebackList().get(0).getDescription(), "change this");
		
		ArrayList<String> apps = new ArrayList<String>();
		apps.add("person");
		assertEquals(team.getChoice().getAlt(1).getApprovers(), apps);
		
	}

}
