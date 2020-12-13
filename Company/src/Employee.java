import java.util.Comparator;
/*
 * @author Patrick Kupcha
 * Describes the Employee class, and extension of User
 */
public class Employee extends User {
	int type; // 0 full-time OR 1 part-time

	public Employee(int type, String firstName, String lastName, String username, String password, String email,
			String phone, String DOB, String address) {
		super(firstName, lastName, username, password, email, phone, DOB, address);
		this.type = type;
		access = 1;
	}
	
	/*
	 * Prints information regarding employee
	 */
	public String getEmployee() {
		return username + " " + firstName + " " + lastName +  " " + address;
	}
	
	/*
	 * Used to change the type of an employee from full-time to part-time
	 * @return true when successful, false if inputting the same type
	 */
	boolean changeType(int type) {
		if (this.type == type || type > 1 || type < 0) {
			return false;
		}
		else
			this.type = type;
		return true;
	}

	/*
	 *	Returns String information on Employee -- used throughout testing
	 * 
	 */
	@Override
	public String toString() {
		String stringDescription = super.toString();
		if (type == 0)
			stringDescription += " Full-time employee.";
		else
			stringDescription += " Part-time employee.";
		return stringDescription;
	}
	
	/*
	 * Used in order to sort and compare employee types. This is called during createSchedule()
	 * so full time employees will be scheduled before part-timers
	 */
    public static Comparator<Employee> employeeType = new Comparator<Employee>() {

	public int compare(Employee e1, Employee e2) {

	   int emp1 = e1.type;
	   int emp2 = e2.type;

	   /*For ascending order*/
	   return emp1 - emp2;

	   /*For descending order*/
	   //return emp2 - emp1;
   }};

}