/*
 * @author Patrick Kupcha
 * This is the controller class for the View Current Schedule page. There is no input on this page, it will show an employee their current week's schedule for each day of the week.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CurrentScheduleController implements Initializable{
	public String username; // this will eventually get passed to controller
	public Text employeeGreeting;
	public TextArea monText;
	public TextArea tuesText;
	public TextArea wedText;
	public TextArea thursText;
	public TextArea friText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	public void setTextValues(String username) {
		employeeGreeting.setText(username + ", your schedule for the week is as follows:");
		EmployeeDatabase db = new EmployeeDatabase();
		try {
			db.connect();
			PreparedStatement ps = db.conn.prepareStatement("SELECT monday, tuesday, wednesday, thursday, friday FROM CompanyScheduler.schedule where username = \"" + username + "\";");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int mon = rs.getInt(1);
				if (mon == 1) {
					monText.setText("9AM to 5 PM");
				}else if (mon == 2) {
					monText.setText("1PM to 9PM");
				}
				int tues = rs.getInt(2);
				if (tues == 1) {
					tuesText.setText("9AM to 5 PM");
				}else if (mon == 2) {
					tuesText.setText("1PM to 9PM");
				}
				int wed = rs.getInt(3);
				if (wed == 1) {
					wedText.setText("9AM to 5 PM");
				}else if (wed == 2) {
					wedText.setText("1PM to 9PM");
				}
				int thurs = rs.getInt(4);
				if (thurs == 1) {
					thursText.setText("9AM to 5 PM");
				}else if (mon == 2) {
					thursText.setText("1PM to 9PM");
				}
				int fri = rs.getInt(5);
				if (fri == 1) {
					friText.setText("9AM to 5 PM");
				}else if (mon == 2) {
					friText.setText("1PM to 9PM");
				}
				
				
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("EXCEPTION with SQL query.");
			e.printStackTrace();
		}	
		
		
		
	}
	
	
	/*
	 * Returns back to the employee home page
	 */
	public void handleReturnButton(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
        Parent root = loader.load();
        Scene employeeHomeScene = new Scene(root);
        EmployeeHomeController controller = loader.getController();
        Stage employeeHomeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        employeeHomeStage.setScene(employeeHomeScene);
        employeeHomeStage.setTitle("Employee Home");
        employeeHomeStage.show();
		return;
	}
	
	
	
}
