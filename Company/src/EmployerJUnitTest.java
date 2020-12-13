import static org.junit.Assert.*;

import org.junit.Test;

public class EmployerJUnitTest {

	
	
	Employer testEmployer = new Employer(2, "Kyle", "Van", "kvanw", "password123", "email@gmail.com",
			"7321231234", "01-02-1998", "Rutgers Ave");
	@Test
	public void testToString() {
		testEmployer.setAvailability(0, 1, 2, 0, 1);
		String test = "USER: Kyle Van. USERNAME: kvanw. Mon Availability: 0 Tues Availability: 1 Wed Availability: 2 Thur Availability: 0 Fri Availability: 1";
		test = test.concat(" Manager role.");
		//test the employer to string with the test employer
		assertEquals(test, testEmployer.toString());
	}

	

	@Test
	public void testChangeType() {
		
		//test changing an employers type
		assertEquals(true, testEmployer.changeType(3));
	}

}
