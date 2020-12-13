/**
 * 
 * @author Patrick
 *	Describes the Employer class, an extension of user
 */
public class Employer extends User {
	int type; // 2 working employer (gets included on schedule) OR 3 does not get included on
				// schedule

	//constructor
	public Employer(int type, String firstName, String lastName, String username, String password, String email,
			String phone, String DOB, String address) {
		super(firstName, lastName, username, password, email, phone, DOB, address);
		this.type = type;
		access = 0;
	}

	/*
	 * Change employee from full-time to part-time or vice-versa
	 * @returns true if input is a new type, false if you input their current type
	 */
	boolean changeType(int type) {
		if (this.type == type)
			return false;
		else
			this.type = type;
		return true;
	}

	/*
	 * Output String describing the current employer
	 */
	@Override
	public String toString() {
		String stringDescription = super.toString();
		if (type == 2)
			stringDescription += " Manager role.";
		else
			stringDescription += " Database administrator role.";
		return stringDescription;
	}
}