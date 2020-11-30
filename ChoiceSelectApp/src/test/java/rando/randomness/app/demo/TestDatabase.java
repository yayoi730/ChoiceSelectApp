package rando.randomness.app.demo;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Feedback;
import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;

public class TestDatabase {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCreateChoice() {
	    ChoiceDAO cd = new ChoiceDAO();
	    try {
	    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    	Choice c = new Choice("Go to office hourse",timestamp);
	    	Alternative a1 = new Alternative("Alternative A");
	    	Alternative a2 = new Alternative("Alternative B");
	    	Alternative a3 = new Alternative("Alternative C");
	    	//Alternative a4 = new Alternative("Alternative 4");
	    	c.addAlternative(a1);
	    	c.addAlternative(a2);
	    	c.addAlternative(a3);
	    	
	    	ArrayList<Member> newMembers = new ArrayList<>();
	    	Member m1 = new Member("Luthor");
	    	//Member m2 = new Member("Rodrick");
	    	//Member m3 = new Member("Cameron");
	    	newMembers.add(m1);
	    	//newMembers.add(m2);
	    	//newMembers.add(m3);
	    	Team t = new Team(newMembers, c);
	    	
	    	String id = UUID.randomUUID().toString(); // no more than 20 because of DB restrictions...
	    	
	    	
	    	t = cd.addTeam(t);  
	    	assertTrue(true);
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	@Test
	public void testFind() {
		String tID = "ad43679a-bc82-414e-8e92-e7f6f9034406";
	    ChoiceDAO cd = new ChoiceDAO();
	    try {
	    	Team t = cd.retrieveTeam(tID);
	    	System.out.println(t.getChoice().getDescription());
	    	for(Alternative a: t.getChoice().getAlternativeList())
	    	{
	    		System.out.println(a.getAID());
	    		System.out.println(a.getDescription());
	    	}
	    	assertTrue(true);
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	

}
