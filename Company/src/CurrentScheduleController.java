/*
 * @author Patrick Kupcha
 * This is the controller class for the View Current Schedule page. There is no input on this page, it will show an employee their current week's schedule for each day of the week.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CurrentScheduleController{
	public String username; // this will eventually get passed to controller
	public int type;
	public Text employeeGreeting;
	public TextArea monText;
	public TextArea tuesText;
	public TextArea wedText;
	public TextArea thursText;
	public TextArea friText;
	
	
	@FXML
    private Label welcomeLabel;

    @FXML
    private Label employeeStatus;

    @FXML
    private Button checkScheduleButton;

    @FXML
    private Button availabilityButton;

    @FXML
    private Button timeOffButton;

    @FXML
    private Button Logout;
	
    /*
     * Sets personal message based on employee currently logged in
     */
	public void setTextValues2() {
		welcomeLabel.setText(username);
		if (type == 0) {
			employeeStatus.setText("Full-Time Employee");
		}else {
			employeeStatus.setText("Part-Time Employee");
		}
	}
	
	/*
	 * Initializes text based on current schedule 
	 */
	public void setTextValues() {
		//employeeGreeting.setText(username + ", your schedule for the week is as follows:");
		EmployeeDatabase db = new EmployeeDatabase();
		try {
			String dbuser = "cs431";
			String dbpassw = "satisfaction";
			// String databasename = "java_cinemaTickets";
			String url = "jdbc:mysql://cs431db.cnzuynuyygsz.us-east-2.rds.amazonaws.com:3306/CompanyScheduler";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbuser, dbpassw);
			PreparedStatement ps = conn.prepareStatement("SELECT monday, tuesday, wednesday, thursday, friday FROM CompanyScheduler.schedule where username = \"" + username + "\";");
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
				}else if (tues == 2) {
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
				}else if (thurs == 2) {
					thursText.setText("1PM to 9PM");
				}
				int fri = rs.getInt(5);
				if (fri == 1) {
					friText.setText("9AM to 5 PM");
				}else if (fri == 2) {
					friText.setText("1PM to 9PM");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("EXCEPTION with SQL query.");
			e.printStackTrace();
		}		
	}
	
	
	public void handleCheckSchedule(ActionEvent event) throws IOException {
    	
	}
	
	/*
	 * Will take the user to the page where they may submit a request for time off.
	 */
	@FXML
	public void handleRequestTimeOff(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestTimeOffUpdate.fxml"));
        Parent root = loader.load();
        Scene requestTimeOffScene = new Scene(root);
        requestTimeOffController controller = loader.getController();
        controller.username = username;
        controller.type = type;
        controller.setTextValues2();
        Stage requestTimeOffStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        requestTimeOffStage.setScene(requestTimeOffScene);
        requestTimeOffStage.setTitle("Request Time Off");
        requestTimeOffStage.show();
		return;
	}
	
	/*
	 * Will log the user out of the program and close.
	 */
	public void Logout(ActionEvent event) throws IOException{
		JOptionPane.showMessageDialog(null, "You are now logging out.");
		try {

			AnchorPane second =  FXMLLoader.load(getClass().getResource("LogInUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Login");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	/*
	 * Will take the user to a page where they can submit their availability to work for the given week.
	 */
	public void handleAvailability(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AvailabilityUpdate.fxml"));
        Parent root = loader.load();
        Scene availabilityScene = new Scene(root);
        AvailabilityController controller = loader.getController();
        controller.username = username;
        controller.type = type;
        controller.setTextValues();
        controller.setTextValues2();
        Stage availabilityStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        availabilityStage.setScene(availabilityScene);
        availabilityStage.setTitle("Request Time Off");
        availabilityStage.show();
		return;
	}
	
	
	
}
