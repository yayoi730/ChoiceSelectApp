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
	    	a1.setAltNumber(1);
	    	Alternative a2 = new Alternative("Alternative B");
	    	a2.setAltNumber(2);
	    	Alternative a3 = new Alternative("Alternative C");
	    	a3.setAltNumber(3);
	    	
	    	a1.addApprover("Rodrick");
	    	a2.addDisapprover("Cameron");
	    	a3.addApprover("Luthor");
	    	a3.addApprover("Cameron");
	    	a3.addDisapprover("Rodrick");
	    	Feedback f1 = new Feedback(timestamp, "This is some feedback", "Rodrick");
	    	Feedback f2 = new Feedback(timestamp, "This is some more feedback", "Luthor");
	    	
	    	a1.addFeedback(f1);
	    	a2.addFeedback(f2);
	    	a3.addFeedback(f1);
	        a3.addFeedback(f2);
	    	//Alternative a4 = new Alternative("Alternative 4");
	    	c.addAlternative(a1);
	    	c.addAlternative(a2);
	    	c.addAlternative(a3);
	    	
	    	ArrayList<Member> newMembers = new ArrayList<>();
	    	Member m1 = new Member("Luthor");
	    	Member m2 = new Member("Rodrick");
	    	Member m3 = new Member("Cameron");
	    	newMembers.add(m1);
	    	newMembers.add(m2);
	    	newMembers.add(m3);
	    	Team t = new Team(newMembers, c);
	    	t.setTeamSize(5);
	    	String id = UUID.randomUUID().toString(); 
	    	
	    	//Test adding team
	    	t = cd.addTeam(t);  
	    	System.out.println("Choice added correctly");
	    	
	    	String tID = t.getTID();
	    	
	    	System.out.println(tID);
	    	//Try retrieving the newly added team
	    	Team tCopy = cd.retrieveTeam(tID);
	    	System.out.println("Team Size: " + tCopy.getTeamSize());
	    	System.out.println(tCopy.getChoice().getDescription());
	    	for(Alternative a: tCopy.getChoice().getAlternativeList())
	    	{
	    		System.out.println("Alt Number: " + a.getAltNumber());
	    		System.out.println(a.getAID());
	    		System.out.println(a.getDescription());
	    		System.out.println("Feedback: ");
	    		for(Feedback f: a.getFeebackList())
	    		{
	    			System.out.println("Creator:" + f.getCreator());
	    			System.out.println("Description:" + f.getDescription());
	    		}
	    		System.out.println("Approved by:");
	    		for(String ap: a.getApprovers())
	    		{
	    			System.out.println(ap);
	    		}
	    		System.out.println("Disapproved by:");
	    		for(String di: a.getDispprovers())
	    		{
	    			System.out.println(di);
	    		}
	    	}
	    	
	    	//Test delete
	    	//Check database to see if it deleted
	    	cd.deleteTeam(tID);
	    	assertTrue(true);
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	//@Test
	//public void testFind() {
	//	String tID = "ad43679a-bc82-414e-8e92-e7f6f9034406";
	//    ChoiceDAO cd = new ChoiceDAO();
	//    try {
	//    	Team t = cd.retrieveTeam(tID);
	//    	System.out.println(t.getChoice().getDescription());
	//    	for(Alternative a: t.getChoice().getAlternativeList())
	//    	{
	//    		System.out.println(a.getAID());
	//    		System.out.println(a.getDescription());
	//    	}
	//    	assertTrue(true);
	//    } catch (Exception e) {
	//    	fail ("didn't work:" + e.getMessage());
	//    }
	//}
	

}
