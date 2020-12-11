package choice.select.app.http;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Test;

import rando.randomness.app.demo.db.ChoiceDAO;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;

public class CreateMemberTest {

	@Test
	public void test() {
		
		Member m1 = new Member("Lil", "");
		
		Timestamp ts = new Timestamp(0);
		Alternative a1 = new Alternative("yes");
		Alternative a2 = new Alternative("no");
		ArrayList<Alternative> alts = new ArrayList<Alternative>();
		alts.add(a1);
		alts.add(a2);
		Choice c = new Choice("choose one", alts, ts);
		Team team = new Team(m1, c, 5);
		
		ChoiceDAO dao = new ChoiceDAO();
		try {
			dao.addMember(m1, team.getTID());
		} catch (Exception e) {
			fail ("create member error: " + e.getMessage());
		}
	}

}
