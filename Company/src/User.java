/**
 * @author Patrick Kupcha
 */
public class User {
	String firstName;
	String lastName;
	String username;
	String password;
	String email;
	String phone;
	String DOB; // format YYYY-MM-DD
	String address;
	int access;
	int mondayAvailability; // 0 = open availability, 1 = 9am-5pm, 2 = 1pm-9pm
	int tuesdayAvailability;
	int wednesdayAvailability;
	int thursdayAvailability;
	int fridayAvailability;

	// constructor
	public User(String firstName, String lastName, String username, String password, String email, String phone,
			String DOB, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.DOB = DOB;
		this.address = address;
		access = -1;
	}

	/**
	 * Change a user's password
	 * @param newPassword
	 * @return false if passing same password, true otherwise
	 */
	boolean changePassword(String newPassword) {
		if (password.equals(newPassword)) {
			System.out.println("Password must be different.");
			return false;
		}
		password = newPassword;
		return true;
	}

	/**
	 * Change a user's email address
	 * @param newEmail
	 * @return false if passing same email, true otherwise
	 */
	boolean changeEmail(String newEmail) {
		if (email.equals(newEmail)) {
			return false;
		} else {
			email = newEmail;
			return true;
		}
	}

	/**
	 * Change a user's address
	 * @param newAddress
	 * @return false if passing same address, true otherwise
	 */
	boolean changeAddress(String newAddress) {
		if (address.equals(newAddress)) {
			return false;
		} else {
			address = newAddress;
			return true;
		}
	}

	/**
	 * Change a user's phone number
	 * @param newPhone
	 * @return false if passing same phone number, true otherwise
	 */
	boolean changePhone(String newPhone) {
		if (newPhone.equals(phone)) {
			return false;
		} else {
			phone = newPhone;
			return true;
		}
	}

	/**
	 * Get a user's access type -- admin or employee
	 * @return
	 */
	int getAccess() {
		return access;
	}
	
	/**
	 * Get String of user's username
	 * @return
	 */
	String getUsername() {		
		return username;
	}
	
	/**
	 * Set a user's weekly availability
	 * @param monday
	 * @param tuesday
	 * @param wednesday
	 * @param thursday
	 * @param friday
	 */
	void setAvailability(int monday, int tuesday, int wednesday, int thursday, int friday) {
		mondayAvailability = monday;
		tuesdayAvailability = tuesday;
		wednesdayAvailability = wednesday;
		thursdayAvailability = thursday;
		fridayAvailability = friday;
	}

	/**
	 * @return String describing a User
	 */
	@Override
	public String toString() {
		String userDescription = "";
		userDescription += "USER: " + firstName + " " + lastName + ". USERNAME: " + username + ".";
		userDescription += " Mon Availability: " + mondayAvailability;
		userDescription += " Tues Availability: " + tuesdayAvailability;
		userDescription += " Wed Availability: " + wednesdayAvailability;				
		userDescription += " Thur Availability: " + thursdayAvailability;		
		userDescription += " Fri Availability: " + fridayAvailability;		
		return userDescription;
	}

}