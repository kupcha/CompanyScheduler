
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;



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

	public void acceptedRequest(String request) throws SQLException, ClassNotFoundException, ParseException
	{
		
		
		//Statement st = conn.createStatement();
		//ResultSet rs = pst.executeQuery();
		
		
		String[] splitRequest = request.split(" ");
		
		
		DateFormat df = new SimpleDateFormat("yyy-MM-dd");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		String username = splitRequest[0];
		splitRequest[1] = splitRequest[1].concat(" 00:00:00");
		Date startDate = dateFormat.parse(splitRequest[1]);
		Date startDate2 = startDate;
		Date endDate = df.parse(splitRequest[3]);
		
		
		Calendar cal = Calendar.getInstance();
		
		//int currentday = startDate.getDay();
		
		cal.setTime(startDate2);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		
		
		System.out.println(startDate);
		System.out.println(df.format(cal.getTime()));
		java.sql.Date date1 = new java.sql.Date(startDate.getTime());
		java.sql.Date datew = new java.sql.Date((df.parse(df.format(cal.getTime()))).getTime());
		java.sql.Date date2 = new java.sql.Date(endDate.getTime());
		
		String startD = dateFormat.format(startDate);
		String endD = dateFormat.format(df.parse(splitRequest[3]));
		
		
		
		
		
		String week = dateFormat.format(cal.getTime());
		if(!(splitRequest[splitRequest.length-1]).equals("Denied"))
		{	
		
		connect();
		String query = "INSERT INTO AcceptedRequests (username, weekOf, startDate, endDate, prevAvail)"
				+ "VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(query);
		
		pst.setString(1, username);
		pst.setDate(2, datew);		
		pst.setDate(3, date1);
		pst.setDate(4, date2 );
		pst.setInt(5, -1);
		
		
		pst.execute();
		conn.close();
		}
		
		connect();
		String query2 = "DELETE FROM timeoffRequests WHERE username = ? AND endDate = ?";
		
		PreparedStatement pst2 = conn.prepareStatement(query2);
		
		System.out.println(date1);
		
		pst2.setString(1, username);	
		//pst2.setDate(2, date1);
		pst2.setString(2, df.format(date2));
	
		
		
		pst2.execute();
		conn.close();
		
		
		
		}
		
		
		
		
		
	
	
	
	
	public ArrayList getRequests() throws SQLException, ClassNotFoundException
	{
		
		ArrayList<String> request = new ArrayList<String>();

		connect();
		
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM timeoffRequests");
		//Statement st = conn.createStatement();
		ResultSet rs = pst.executeQuery();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		while(rs.next())
		{
			
			String un = rs.getString(1);
			Date firstDate = new Date();
			firstDate = rs.getDate(2);
			String strFirst = dateFormat.format(firstDate);
			
			Date secondDate = new Date();
			secondDate = rs.getDate(3);
			String strSecond = dateFormat.format(secondDate);
			
			String fullRequest = un;
			
			fullRequest = fullRequest.concat(" " + strFirst + " - " + strSecond);
			
			
			request.add(fullRequest);
			
			
			
		}
		
		
		conn.close();
		
		
		
		
		return request;
		
	}
	public int setPrev(String username, int days) throws SQLException, ClassNotFoundException
	{
		
ArrayList<String> weekDays = new ArrayList<String>();
		
		weekDays.add("monday");
		weekDays.add("tuesday");
		weekDays.add("wednesday");
		weekDays.add("thursday");
		weekDays.add("friday");
		
		String day = "";
		
		
			day = weekDays.get(days);
		
			
		
		connect();
		String query3 = "SELECT " + day + " FROM CompanyScheduler.availability WHERE username = ?";

		PreparedStatement ps3 = conn.prepareStatement(query3);
		ps3.setString(1, username);
		ResultSet rs = ps3.executeQuery();
		int prev = -1;
		
		if(rs.next()) {
		prev = rs.getInt(1);
		}
		
		
		conn.close();
		
		return prev;
	}
	public ArrayList timeoffCheck() throws SQLException, ClassNotFoundException
	{
		
		ArrayList<Request> requestList = new ArrayList<Request>();
		ArrayList<Request> added = new ArrayList<Request>();
		connect();
		
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM AcceptedRequests");
		//Statement st = conn.createStatement();
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
		String user = rs.getString(1);
		Date weekOf = rs.getDate(2);
		Date start = rs.getDate(3);
		Date end  = rs.getDate(4);
		int prevAvail = rs.getInt(5);
		Request request = new Request(user, weekOf, start, end, prevAvail);
		
		requestList.add(request);
		
		
		}
		
		conn.close();
		
		
		connect();
		
		PreparedStatement pst2 = conn.prepareStatement("SELECT * FROM CurrentWeek");
		//Statement st = conn.createStatement();
		ResultSet rs2 = pst2.executeQuery();
		
		Date currentWeek = null;
		if(rs2.next()) {
			currentWeek= rs2.getDate(1);
		}
		
		
		conn.close();
		
		
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
		
		//LocalDate date1 = LocalDate.parse(start.toString(), dtf);
		//LocalDate date2 = LocalDate.parse(currentWeek.toString(), dtf);
		
		
		//long daysBetween = Duration.between(date1, date2).toDays();
		
		for(int i = 0; i < requestList.size(); i++)
		{
			
			if(requestList.get(i).getWeekOf().equals(currentWeek))
		{
				added.add(requestList.get(i));
				
				long diff = requestList.get(i).getStart().getTime() - currentWeek.getTime();
				long diff2 = requestList.get(i).getEnd().getTime() - requestList.get(i).getStart().getTime();
				
				
		long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		long timeOff = TimeUnit.DAYS.convert(diff2, TimeUnit.MILLISECONDS);
		
		ArrayList<String> weekDays = new ArrayList<String>();
		
		weekDays.add("monday");
		weekDays.add("tuesday");
		weekDays.add("wednesday");
		weekDays.add("thursday");
		weekDays.add("friday");
		
		String day = "";
		
		
		day = weekDays.get((int)days-1);
			requestList.get(i).setDiff((int)days-1);
				requestList.get(i).setPrevAvail();

		connect();
		PreparedStatement pst3 = conn.prepareStatement("UPDATE availability SET " + day + "= ? WHERE username = ?");
		//Statement st = conn.createStatement();
		
		pst3.setInt(1, -1);
		pst3.setString(2, requestList.get(i).getUser());
		pst3.executeUpdate();
		
		conn.close();
		}
		
		}
		
		
		return added;
		
		
		
	}
	public void setBack(ArrayList<Request> added, int days) throws SQLException, ClassNotFoundException {
		
		
		
ArrayList<String> weekDays = new ArrayList<String>();
		
		weekDays.add("monday");
		weekDays.add("tuesday");
		weekDays.add("wednesday");
		weekDays.add("thursday");
		weekDays.add("friday");
		
		String day = "";
		for(int i = 0; i < added.size(); i++)
		{
			day = weekDays.get(added.get(i).getDay());
			
		connect();
		PreparedStatement pst3 = conn.prepareStatement("UPDATE availability SET " + day + "= ? WHERE username = ?");
		//Statement st = conn.createStatement();
		
		pst3.setInt(1, added.get(i).getPrev());
		pst3.setString(2, added.get(i).getUser());
		pst3.executeUpdate();
		
		conn.close();
		}
		
		for(int i = 0; i < added.size(); i++)
		{
		connect();
		PreparedStatement pst = conn.prepareStatement("DELETE FROM AcceptedRequests WHERE username = ? AND weekOf = ? AND startDate = ? AND endDate = ?");
		//Statement st = conn.createStatement();
		Date weekDate = added.get(i).getWeekOf();
		Date startDate = added.get(i).getStart();
		Date endDate = added.get(i).getEnd();
		java.sql.Date date1 = new java.sql.Date(weekDate.getTime());
		java.sql.Date date2 = new java.sql.Date(startDate.getTime());
		java.sql.Date date3 = new java.sql.Date(endDate.getTime());
		
		
		pst.setString(1, added.get(i).getUser());
		pst.setDate(2, date1);
		pst.setDate(3, date2);
		pst.setDate(4, date3);
		
		pst.execute();
		
		conn.close();
		}
		
		
		
		
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
	

	public int setSchedule(Schedule sched) throws SQLException, ClassNotFoundException {
		connect();
		PreparedStatement clear = conn.prepareStatement("UPDATE `CompanyScheduler`.`schedule` SET `monday` = '-1', `tuesday` = '-1', `wednesday` = '-1', `thursday` = '-1', `friday` = '-1';");
		clear.execute();
		for (int i = 0; i < sched.monday.size(); i++) {
			PreparedStatement pst = conn.prepareStatement("UPDATE `CompanyScheduler`.`schedule` SET `monday` = '" + sched.monday.get(i).hours + "' WHERE (`username` = '" + sched.monday.get(i).employee.username + "');");
			pst.execute();
		}
		for (int i = 0; i < sched.tuesday.size(); i++) {
			PreparedStatement pst = conn.prepareStatement("UPDATE `CompanyScheduler`.`schedule` SET `tuesday` = '" + sched.tuesday.get(i).hours + "' WHERE (`username` = '" + sched.tuesday.get(i).employee.username + "');");
			pst.execute();
		}
		for (int i = 0; i < sched.wednesday.size(); i++) {
			PreparedStatement pst = conn.prepareStatement("UPDATE `CompanyScheduler`.`schedule` SET `wednesday` = '" + sched.wednesday.get(i).hours + "' WHERE (`username` = '" + sched.wednesday.get(i).employee.username + "');");
			pst.execute();
		}
		for (int i = 0; i < sched.thursday.size(); i++) {
			PreparedStatement pst = conn.prepareStatement("UPDATE `CompanyScheduler`.`schedule` SET `thursday` = '" + sched.thursday.get(i).hours + "' WHERE (`username` = '" + sched.thursday.get(i).employee.username + "');");
			pst.execute();
		}
		for (int i = 0; i < sched.friday.size(); i++) {
			PreparedStatement pst = conn.prepareStatement("UPDATE `CompanyScheduler`.`schedule` SET `friday` = '" + sched.friday.get(i).hours + "' WHERE (`username` = '" + sched.friday.get(i).employee.username + "');");
			pst.execute();
		}
		conn.close();
		return 1;
	}
}
	
	
	
