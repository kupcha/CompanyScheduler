import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ScheduleJUnitTester {
	
	Employee testEmployee = new Employee(0, "Kyle", "Van", "kvanw", "password123", "email@gmail.com",
			"7321231234", "01-02-1998", "Rutgers Ave");
	Employee fullTime = new Employee(0, "Joe", "Donuts", "jdonuts", "letmein", "rutgerstest@gmail.com",
			"7321231234", "01-02-1998", "123 Main St");
	Employee partTime = new Employee(1, "Patrick", "Kupcha", "kup", "thisismypassword", "kupcha@gmail.com",
			"6092347110", "09-11-1991", "52 Home St");
	Staff testStaff = new Staff();
	Shift testMon = new Shift(testEmployee, 1, 1);
	Shift testTues = new Shift(testEmployee, 2, 1);
	Shift testWed = new Shift(testEmployee, 3, 1);
	Shift testThurs = new Shift(testEmployee, 4, 1);
	Shift testFri = new Shift(testEmployee, 5, 1);
	Shift fullMon = new Shift(fullTime, 1, 2);
	Shift fullTues = new Shift(fullTime, 2, 2);
	Shift fullWed = new Shift(fullTime, 3, 2);
	Shift fullThurs = new Shift(fullTime, 4, 2);
	Shift fullFri = new Shift(fullTime, 5, 2);
	Shift partMon = new Shift(partTime, 1, 1);
	Shift partTues = new Shift(partTime, 2, 2);
	Shift partWed = new Shift(partTime, 3, 1);
	Shift partThurs = new Shift(partTime, 4, 2);
	Shift partFri = new Shift(partTime, 5, 1);
	ArrayList<User> testList = new ArrayList<User>();
	
	@Test
	public void testAddShift() {
		testList.add(testEmployee);
		Schedule testSchedule = new Schedule(testList, 120);
		//add valid shift
		assertEquals(true, testSchedule.addShift(testMon));
		//add duplicate shift
		assertEquals(false, testSchedule.addShift(testMon));
	}
	
	@Test
	public void testRemoveShift() {
		testList.add(testEmployee);
		Schedule testSchedule = new Schedule(testList, 120);
		testSchedule.addShift(testMon);
		//remove currently assigned shift
		assertEquals(true, testSchedule.removeShift(testMon));
		//remove shift not in schedule
		assertEquals(false, testSchedule.removeShift(testMon));
	}
	
	@Test
	public void testHowManyScheduled() {
		testList.add(testEmployee);
		testList.add(fullTime);
		testList.add(partTime);
		Schedule testSchedule = new Schedule(testList, 120);
		testSchedule.addShift(testMon);
		testSchedule.addShift(testTues);
		testSchedule.addShift(testWed);
		testSchedule.addShift(testThurs);
		testSchedule.addShift(testFri);
		testSchedule.addShift(fullMon);
		testSchedule.addShift(fullTues);
		testSchedule.addShift(fullWed);
		testSchedule.addShift(fullThurs);
		testSchedule.addShift(fullFri);
		testSchedule.addShift(partMon);
		testSchedule.addShift(partTues);
		testSchedule.addShift(partWed);
		testSchedule.addShift(partThurs);
		testSchedule.addShift(partFri);
		//check number scheduled in the morning
		assertEquals(2, testSchedule.howManyScheduled(1, 1));
		
		assertEquals(1, testSchedule.howManyScheduled(2, 1));
	}

}
