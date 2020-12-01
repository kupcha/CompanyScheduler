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

	boolean changePassword(String newPassword) {
		if (password == newPassword) {
			System.out.println("Password must be different.");
			return false;
		}
		password = newPassword;
		return true;
	}

	boolean changeEmail(String newEmail) {
		if (email == newEmail) {
			return false;
		} else {
			email = newEmail;
			return true;
		}
	}

	boolean changeAddress(String newAddress) {
		if (address == newAddress) {
			return false;
		} else {
			address = newAddress;
			return true;
		}
	}

	boolean changePhone(String newPhone) {
		if (newPhone.equals(phone)) {
			return false;
		} else {
			phone = newPhone;
			return true;
		}
	}

	int getAccess() {
		return access;
	}
	
	String getUsername() {		
		return username;
	}
	
	
	void setAvailability(int monday, int tuesday, int wednesday, int thursday, int friday) {
		mondayAvailability = monday;
		tuesdayAvailability = tuesday;
		wednesdayAvailability = wednesday;
		thursdayAvailability = thursday;
		fridayAvailability = friday;
	}

	@Override
	public String toString() {
		String userDescription = "";
		userDescription += "USER: " + firstName + " " + lastName + ". USERNAME: " + username + ".";	
		return userDescription;
	}

}