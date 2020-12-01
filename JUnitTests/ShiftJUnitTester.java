import static org.junit.Assert.*;

import org.junit.Test;

public class ShiftJUnitTester {
	
	Employee testEmployee = new Employee(0, "Kyle", "Van", "kvanw", "password123", "email@gmail.com",
			"7321231234", "01-02-1998", "Rutgers Ave");
	Employee nonEmployee = new Employee(0, "Joe", "Donuts", "jdonuts", "letmein", "rutgerstest@gmail.com",
			"7321231234", "01-02-1998", "123 Main St");
	//monday shift from 9am-5pm
	Shift testShift = new Shift(testEmployee, 1, 1);

	@Test
	public void testChangeHours() {
		//change from morning to evening shift
		assertEquals(true, testShift.changeHours(2));
		//attempt to change to the current assigned hours
		assertEquals(false, testShift.changeHours(2));
		
	}
	
	@Test
	public void testChangeDay() {
		//change from monday to tuesday shift
		assertEquals(true, testShift.changeWorkDay(2));
		//attempt to change to the day already assigned
		assertEquals(false, testShift.changeWorkDay(2));
	}
	
	@Test
	public void testWhoIsWorking() {
		//correct employee
		assertEquals(testEmployee, testShift.whoIsWorking());
		//different employee
		assertNotSame(nonEmployee, testShift.whoIsWorking());
		
	}
	

}
