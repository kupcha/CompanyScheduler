public class Employer extends User{
	int type; // 2 working employer (gets included on schedule) OR 3 does not get included on schedule
	
	public Employer(int type, String firstName, String lastName, String username, String password, String email, int phone, String DOB, String address) {
		super(firstName, lastName, username, password, email, phone, DOB, address);
		this.type = type;
		access = 0;
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
		if (type == 2)
			stringDescription += " Manager role.";
		else
			stringDescription += " Database administrator role.";
		return stringDescription;
	}
}