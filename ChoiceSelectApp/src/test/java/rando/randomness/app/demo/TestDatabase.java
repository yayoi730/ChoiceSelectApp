package rando.randomness.app.demo;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Choice;
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
	    	//Choice c = new Choice(id, "This is a description",timestamp);
	    	ArrayList<Member> newMembers = new ArrayList<>();
	    	Member m = new Member("Ryan", "pass1");
	    	newMembers.add(m);
	    	//Team t = new Team(newMembers, c);
	    	String id = UUID.randomUUID().toString(); // no more than 20 because of DB restrictions...
	    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    	
	    	//c.setID(cd.addChoice(c, id));  
	    	//System.out.println("add constant: " + b);
	    	
	    	// can retrieve it
	    	//Constant c2 = cd.getConstant(constant.name);
	    	//System.out.println("C2:" + c2.name);
	    	
	    	// can delete it
	    	//assertTrue (cd.deleteConstant(c2));
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}

}
