package rando.randomness.app.demo.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestAlternative {
	@Test
	public void testAddApprover() {
		Member m = new Member("Erin", "passowrd");
		Alternative a = new Alternative("sample alternative");
		a.addApprover(m.getName());
		assertTrue(m.getName().contentEquals("Erin"));
	}

}
