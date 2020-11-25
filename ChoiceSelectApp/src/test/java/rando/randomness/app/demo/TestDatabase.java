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
	    	Choice c = new Choice("This is a description",timestamp);
	    	Alternative a1 = new Alternative("This is an alternative");
	    	Alternative a2 = new Alternative("This is also an alternative");
	    	c.addAlternative(a1);
	    	c.addAlternative(a2);
	    	ArrayList<Member> newMembers = new ArrayList<>();
	    	Member m1 = new Member("Ryan", "pass1");
	    	Member m2 = new Member("Erin", "pass2");
	    	newMembers.add(m1);
	    	newMembers.add(m2);
	    	Team t = new Team(newMembers, c);
	    	
	    	String id = UUID.randomUUID().toString(); // no more than 20 because of DB restrictions...
	    	
	    	
	    	t = cd.addTeam(t);  
	    	assertTrue(true);
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}

}
