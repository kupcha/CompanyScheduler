import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Patrick Kupcha
 * Describes the Schedule class. A Schedule will be created each week and distributed to employees.
 */
public class Schedule{
	int totalHours;
	ArrayList<User> currentEmployees;
	ArrayList<Shift> monday;
	ArrayList<Shift> tuesday;
	ArrayList<Shift> wednesday;
	ArrayList<Shift> thursday;
	ArrayList<Shift> friday;
	
	public Schedule(ArrayList<User> currentStaff, int hoursToSchedule){
		totalHours = hoursToSchedule;
		currentEmployees = currentStaff;
		monday = new ArrayList<Shift>();
		tuesday = new ArrayList<Shift>();
		wednesday = new ArrayList<Shift>();
		thursday = new ArrayList<Shift>();
		friday = new ArrayList<Shift>();
	}
	/**
	 * Add a shift for a certain employee to the schedule
	 * @param shiftToAdd
	 * @return false if shift already exists in the schedule, true otherwise
	 */
	boolean addShift(Shift shiftToAdd) {
		int day = shiftToAdd.dayOfTheWeek;
		switch(day) {
		case 1: 
			if (this.monday.contains(shiftToAdd)){
				return false;
			}else {
				return monday.add(shiftToAdd);
			}
		case 2: 			
			if (this.tuesday.contains(shiftToAdd)){
				return false;
			}else {
				return tuesday.add(shiftToAdd);
			}
		case 3: 
			if (this.wednesday.contains(shiftToAdd)){
				return false;
			}else {
				return wednesday.add(shiftToAdd);
			}
		case 4: 
			if (this.thursday.contains(shiftToAdd)){
				return false;
			}else {
				return thursday.add(shiftToAdd);
			}
		case 5: 
			if (this.friday.contains(shiftToAdd)){
				return false;
			}else {
				return friday.add(shiftToAdd);
			}
		default: return false;		
		}
	}
	/**
	 * Removes a shift from the schedule.
	 * @param shiftToRemove
	 * @return false if shift was not currently in the schedule, true otherwise
	 */
	boolean removeShift(Shift shiftToRemove) {
		int day = shiftToRemove.dayOfTheWeek;
		switch(day) {
		case 1: return monday.remove(shiftToRemove);
		case 2: return tuesday.remove(shiftToRemove);
		case 3: return wednesday.remove(shiftToRemove);
		case 4: return thursday.remove(shiftToRemove);
		case 5: return friday.remove(shiftToRemove);
		default: return false;		
		}
	}
	
	
	// place holder for future build schedule algo
	void buildSchedule() {
		
	}
	
	/**
	 * Shows how many employees are currently schedulde on a certain day and time
	 * @param dayOfTheWeek
	 * @param hours
	 * @return number of employees already scheduled at a current date and time, to be used in the build schedule algorithim.
	 */
	int howManyScheduled(int dayOfTheWeek, int hours) 
{
		if (hours < 0 || hours > 2) {
			return -1;
		}else if (dayOfTheWeek < 1 || dayOfTheWeek > 5) {
			return -1;
		}
		int employeesCurrentlyScheduled = 0;
		switch(dayOfTheWeek) {
		case 1:
			for (int i = 0; i < monday.size(); i++) {
				if (monday.get(i).hours == hours) {
					employeesCurrentlyScheduled++;
				}
			}
			return employeesCurrentlyScheduled;
		case 2:
			for (int i = 0; i < tuesday.size(); i++) {
				if (tuesday.get(i).hours == hours) {
					employeesCurrentlyScheduled++;
				}
			}
			return employeesCurrentlyScheduled;
		case 3:
			for (int i = 0; i < wednesday.size(); i++) {
				if (wednesday.get(i).hours == hours) {
					employeesCurrentlyScheduled++;
				}
			}
			return employeesCurrentlyScheduled;
		case 4:
			for (int i = 0; i < thursday.size(); i++) {
				if (thursday.get(i).hours == hours) {
					employeesCurrentlyScheduled++;
				}
			}
			return employeesCurrentlyScheduled;
		case 5:
			for (int i = 0; i < monday.size(); i++) {
				if (monday.get(i).hours == hours) {
					employeesCurrentlyScheduled++;
				}
			}
			return employeesCurrentlyScheduled;
		default: return 0;
		}
		
	}
	/**
	 * Function used in testing to output a schedule 
	 */
	void printSchedule() {
		int totalHours = 0;
		for (int i = 0; i < monday.size(); i++) {
			Shift currShift = monday.get(i);
			int day = currShift.dayOfTheWeek;
			User user = currShift.employee;
			totalHours += 8;
			System.out.println(user.username + " working " + day + ", " + currShift.hours + " shift.");
		}
		for (int i = 0; i < tuesday.size(); i++) {
			Shift currShift = tuesday.get(i);
			int day = currShift.dayOfTheWeek;
			User user = currShift.employee;
			totalHours += 8;
			System.out.println(user.username + " working " + day + ", " + currShift.hours + " shift.");
		}
		for (int i = 0; i < wednesday.size(); i++) {
			Shift currShift = wednesday.get(i);
			int day = currShift.dayOfTheWeek;
			User user = currShift.employee;
			totalHours += 8;
			System.out.println(user.username + " working " + day + ", " + currShift.hours + " shift.");
		}
		for (int i = 0; i < thursday.size(); i++) {
			Shift currShift = thursday.get(i);
			int day = currShift.dayOfTheWeek;
			User user = currShift.employee;
			totalHours += 8;
			System.out.println(user.username + " working " + day + ", " + currShift.hours + " shift.");
		}
		for (int i = 0; i < friday.size(); i++) {
			Shift currShift = friday.get(i);
			int day = currShift.dayOfTheWeek;
			User user = currShift.employee;
			totalHours += 8;
			System.out.println(user.username + " working " + day + ", " + currShift.hours + " shift.");
		}
		System.out.println(this.totalHours + " hours available; " + totalHours + " scheduled.");
	}
	
	boolean alreadyScheduled(String username, int workDay) {
		switch(workDay) {
		case 1: 
			for (int i = 0; i < this.monday.size(); i++) {
				if (this.monday.get(i).employee.username.equals(username)) {
					return true;
				}
			}
			return false;
		case 2: 
			for (int i = 0; i < this.tuesday.size(); i++) {
				if (this.tuesday.get(i).employee.username.equals(username)) {
					return true;
				}
			}
			return false;
		case 3: 
			for (int i = 0; i < this.wednesday.size(); i++) {
				if (this.wednesday.get(i).employee.username.equals(username)) {
					return true;
				}
			}
			return false;
		case 4: 
			for (int i = 0; i < this.thursday.size(); i++) {
				if (this.thursday.get(i).employee.username.equals(username)) {
					return true;
				}
			}
			return false;
		case 5: 			
			for (int i = 0; i < this.friday.size(); i++) {
				if (this.friday.get(i).employee.username.equals(username)) {
					return true;
				}
			}
		return false;
		default: return false;		
		}
	}
	
}