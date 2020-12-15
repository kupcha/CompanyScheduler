import static org.junit.Assert.*;

import org.junit.Test;

public class StaffJUnitTester {
	
	Employee testEmployee = new Employee(0, "Kyle", "Van", "kvanw", "password123", "email@gmail.com",
			"7321231234", "01-02-1998", "Rutgers Ave");
	Staff testStaff = new Staff();
	
	String staffString = "USER: Kyle Van. USERNAME: kvanw. Mon Availability: 0 Tues Availability: 0 Wed Availability: 0 Thur Availability: 0 Fri Availability: 0 Full-time employee.";
	
	@Test
	public void testAddStaff() {
		//trying to add valid employee
		assertEquals(true, testStaff.addStaffMember(testEmployee));
		//attempting to add an employee already on staff
		assertEquals(false, testStaff.addStaffMember(testEmployee));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testRemoveStaff() {
		//attempt to remove an employee not on staff
		assertEquals(false, testStaff.removeStaffMember(testEmployee));
		testStaff.addStaffMember(testEmployee);
		//remove a valid employee on staff
		assertEquals(true, testStaff.removeStaffMember(testEmployee));

	}
	
	@Test
	public void testIsStaff() {
		//check is someone who is not staff is on the staff
		assertEquals(false, testStaff.isStaff(testEmployee));
		//add test employee to staff
		testStaff.addStaffMember(testEmployee);
		//check if current employee isStaff
		assertEquals(true, testStaff.isStaff(testEmployee));
	}
	
	@Test
	public void testPrintStaff() {
		testStaff.addStaffMember(testEmployee);
		assertEquals(staffString, testStaff.printStaffMembers());
	}

}
