import static org.junit.Assert.*;

import org.junit.Test;

public class UserJUnitTest {

	
	
	User testUser = new User("Kyle", "Van", "kvanw", "password123", "email@gmail.com",
			"7321231234", "01-02-1998", "Rutgers Ave");
	@Test
	public void testChangePassword() {
		
		assertEquals(false, testUser.changePassword("password123"));
		assertEquals(true, testUser.changePassword("pass123"));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testChangeEmail() {
		
		assertEquals(false, testUser.changeEmail("email@gmail.com"));
		assertEquals(true, testUser.changeEmail("rutgers@gmail.com"));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testChangeAddress() {
		
		assertEquals(false, testUser.changeAddress("Rutgers Ave"));
		assertEquals(true, testUser.changeAddress("College Ave"));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testChangePhone() {
		
		assertEquals(false, testUser.changePhone("7321231234"));
		assertEquals(true, testUser.changePhone("7321234321"));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAccess() {
		
		assertEquals(-1, testUser.getAccess());
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetUsername() {
		assertEquals("kvanw", testUser.getUsername());
		
		//fail("Not yet implemented");
	}

	

	@Test
	public void testToString() {
		testUser.setAvailability(0, 1, 2, 0, 1);
		String test = "USER: Kyle Van. USERNAME: kvanw. Mon Availability: 0 Tues Availability: 1 Wed Availability: 2 Thur Availability: 0 Fri Availability: 1";
		
		assertEquals(test, testUser.toString());
		
		//fail("Not yet implemented");
	}

}
