package rando.randomness.app.demo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAlternative {
	@Test
	void testAddApprover() {
		Member m = new Member("Erin", "passowrd");
		Alternative a = new Alternative("sample alternative");
		a.addApprover(m);
		assertEquals(m.getName(), "Erin");
	}

}
