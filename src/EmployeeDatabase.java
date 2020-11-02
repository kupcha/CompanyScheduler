
import java.sql.*;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;

public class EmployeeDatabase{
Connection conn =null;
/*	
Author: Kyle VanWageninge

Connects to the database
*/
public void connect() throws SQLException, ClassNotFoundException{
	
	
    String dbuser = "cs431";
    String dbpassw = "satisfaction";
   // String databasename = "java_cinemaTickets";
    String url = "jdbc:mysql://cs431db.cnzuynuyygsz.us-east-2.rds.amazonaws.com:3306/CompanyScheduler";
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection(url,dbuser,dbpassw);
	
}

/*	
Author: Kyle VanWageninge

Adds an employee into the employee table on the database
@params: Strings from GUI
*/	
public boolean add(String first_name, String last_name, String username, String password, String DOB, String email, 
		String mobile, String address, String city, String state, String zipcode ) 
		throws SQLException, ClassNotFoundException{
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

        return true;

}


   }