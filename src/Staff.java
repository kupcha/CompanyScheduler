/*
 * @author Patrick Kupcha
 * Describes th Staff class. This is used to maintain a list of the current staff members in the construction of the schedule.
 */
import java.util.ArrayList;

public class Staff{
	ArrayList<User> currentStaff;
	
	//constructor
	public Staff() {
		currentStaff = new ArrayList<User>();
	}
	
	/**
	 * This function adds a new User to the staff list
	 * @param User newStaff
	 * @return true if newStaff was added, false if they were already in list.
	 */
	boolean addStaffMember(User newStaff) {
		return currentStaff.add(newStaff);
	}
	
	/**
	 * This function removes a User from the staff list so they are no longer an active employee.
	 * @param User staffMember
	 * @return true if User was removed, false if they were not in list prior to calling function
	 */
	boolean removeStaffMember(User staffMember) {
		return currentStaff.remove(staffMember);
	}
	
	/**
	 * This function checks whether a User is part of the current staff
	 * @param staffMember
	 * @return true if User is in staff list, false otherwise
	 */
	boolean isStaff(User staffMember) {
		return currentStaff.contains(staffMember);
	}
	
	/**
	 * This function prints information about each User on the staff list
	 */
	void printStaffMembers() {
		for (int i = 0; i < currentStaff.size(); i++) {
			System.out.println(currentStaff.get(i).toString());
		}
	}
	
	
}