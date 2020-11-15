
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDatabase {
	Connection conn = null;

	/*
	 * Author: Kyle VanWageninge
	 * 
	 * Connects to the database
	 */
	public void connect() throws SQLException, ClassNotFoundException {

		String dbuser = "cs431";
		String dbpassw = "satisfaction";
		// String databasename = "java_cinemaTickets";
		String url = "jdbc:mysql://cs431db.cnzuynuyygsz.us-east-2.rds.amazonaws.com:3306/CompanyScheduler";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, dbuser, dbpassw);

	}

	/*
	 * Author: Kyle VanWageninge
	 * 
	 * Adds an employee into the employee table on the database
	 * 
	 * @params: Strings from GUI
	 */
	public boolean add(String first_name, String last_name, String username, String password, String DOB, String email,
			String mobile, String address, String city, String state, String zipcode, int access, int type)
			throws SQLException, ClassNotFoundException {
		connect();
		// Statement st = conn.createStatement();

		String query = "INSERT INTO employees (Username, Password, Firstname, LastName, DateOfBirth, Email, Mobile, Address, City, State, ZipCode)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, first_name);
		ps.setString(4, last_name);
		ps.setDate(5, null);
		ps.setString(6, email);
		ps.setString(7, mobile);
		ps.setString(8, address);
		ps.setString(9, city);
		ps.setString(10, state);
		ps.setString(11, zipcode);

		ps.execute();

		conn.close();

		connect();
		
		String query2 = "INSERT INTO users (username, password, access, type)"
				+ "VALUES (?, ?, ?, ?)";

		PreparedStatement ps2 = conn.prepareStatement(query2);
		ps2.setString(1, username);
		ps2.setString(2, password);
		ps2.setInt(3, access);
		ps2.setInt(4, type);
		
		ps2.execute();
		
		conn.close();
		
		connect();
		String query3 = "INSERT INTO availability (username, monday, tuesday, wednesday, thursday, friday)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement ps3 = conn.prepareStatement(query3);
		ps3.setString(1, username);
		ps3.setInt(2, 0);
		ps3.setInt(3, 0);
		ps3.setInt(4, 0);
		ps3.setInt(5, 0);
		ps3.setInt(6, 0);
		
		ps3.execute();
		
		conn.close();
		return true;

	}

	public Staff creating() throws SQLException, ClassNotFoundException
	{
		Staff staffList = new Staff();
		connect();
		
		//String query = "SELECT * FROM employees";
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM employees");
		//Statement st = conn.createStatement();
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
		{
			String un = rs.getString(1);
			String pw = rs.getString(2);
			String fn = rs.getString(3);
			String ln = rs.getString(4);
			Date dateBirth = rs.getDate(5);
			String email = rs.getString(6);
			String phone = rs.getString(7);
			String add = rs.getString(8);
			String city = rs.getString(9);
			String state = rs.getString(10);
			String zip = rs.getString(11);
			
			int access = rs.getInt(12);
			int type = rs.getInt(13);
		
			System.out.println(un + " access " + access);
			//int number = Integer.parseInt(phone);
			
			
			if(access == 1)
			{
				Employee newE = new Employee(type, fn, ln, un, pw, email, phone, null, add);
				 staffList.addStaffMember(newE);
				
			}else if(access == 0) {
				
				Employer newR = new Employer(type, fn, ln, un, pw, email, phone, null, add);
				 staffList.addStaffMember(newR);
				
			}
			
			
			
			
		}
		conn.close();
		
		
		//Staff newStaffList = insertAvail(staffList);
		
		return staffList;
	}
		
	public ArrayList<User> setAvail(ArrayList<User> staffList) throws SQLException, ClassNotFoundException
	{
			connect();
			PreparedStatement pst = conn.prepareStatement("select monday, tuesday, wednesday, thursday, friday from CompanyScheduler.availability where username = ?;");
			
			for(int i = 0; i < staffList.size(); i++)
			{
			
				
			pst.setString(1, staffList.get(i).getUsername());
			//System.out.println(staffList.get(i).getUsername());
			ResultSet rs = pst.executeQuery();
			
			
			if (rs.next()) {
				int mon = rs.getInt(1);
				int tues = rs.getInt(2);
				int wed = rs.getInt(3);
				int thurs = rs.getInt(4);
				int fri = rs.getInt(5);
				//System.out.println(mon + " " + tues + " " + wed + " " + thurs + " " + fri);
				staffList.get(i).setAvailability(mon, tues, wed, thurs, fri);
				
				
			}
			
			}
			
		
		
		conn.close();
		return staffList;
	}
	

	
	}
	
	
