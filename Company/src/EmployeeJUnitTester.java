import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeeJUnitTester {

	
	Employee testEmployee = new Employee(0, "Kyle", "Van", "kvanw", "password123", "email@gmail.com",
			"7321231234", "01-02-1998", "Rutgers Ave");
	@Test
	public void testToString() {
		testEmployee.setAvailability(0, 1, 2, 0, 1);
		
		
		String test = "USER: Kyle Van. USERNAME: kvanw. Mon Availability: 0 Tues Availability: 1 Wed Availability: 2 Thur Availability: 0 Fri Availability: 1";
		test = test.concat(" Full-time employee.");
		
		assertEquals(test, testEmployee.toString());
		//fail("Not yet implemented");
	}

	@Test
	public void testGetEmployee() {
		
		String test = "Kyle Van Rutgers Ave";
		
		assertEquals(test, testEmployee.getEmployee());
		//fail("Not yet implemented");
	}

	@Test
	public void testChangeType() {
		
		
		assertEquals(false, testEmployee.changeType(0));
		//fail("Not yet implemented");
	}
	

}
