public class Employee extends User{
	int type; // 0 full-time OR 1 part-time
	
	public Employee(int type, String firstName, String lastName, String username, String password, String email, int phone, String DOB, String address) {
		super(firstName, lastName, username, password, email, phone, DOB, address);
		this.type = type;
		access = 1;
	}
	
	
	boolean changeType(int type) {
		if (this.type == type)
			return false;
		else
			this.type = type;
		return true;
	}
	
	@Override
	public String toString(){
		String stringDescription = super.toString();
		if (type == 0)
			stringDescription += " Full-time employee.";
		else
			stringDescription += " Part-time employee.";
		return stringDescription;
	}
	
}