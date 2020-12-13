
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class EmployerViewScheduleController implements Initializable{
	
	
	@FXML
    private TextArea monText;

    @FXML
    private TextArea tuesText;

    @FXML
    private TextArea wedText;

    @FXML
    private TextArea thursText;

    @FXML
    private TextArea friText;
    
	@FXML
	private Button Add;

	@FXML
	private Button ChangeE;

	@FXML
	private Button CheckR;

	@FXML
	private Button RemoveE;

	@FXML
	private Button CreateS;

	@FXML
	private Button Logout;
	
	
	/*
	 * 
	 * Goes to the Add Employee GUI
	 */
	@FXML
	void AddEmployee(ActionEvent event) {

		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("AddEmployeeUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Add Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*
	 * 
	 * Goes to the View Employee GUI
	 */
	@FXML
	void ChangeEmployee(ActionEvent event) {
		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("ViewEmployeesUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("View Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * Goes to the Check Requests GUI
	 */
	@FXML
	void CheckRequest(ActionEvent event) {
		
		
		
		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("CheckRequestsUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Check Requests");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goToCreateSchedule(ActionEvent event) throws IOException {
		
}
	
	/*
	 * 
	 * Goes to the Log-In GUI
	 */
	@FXML
	void Logout(ActionEvent event) {
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
	 * 
	 * Goes to the Remove Employee GUI
	 */
	@FXML
	void RemoveEmployee(ActionEvent event) {
		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("RemoveEmployeeUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("View Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 * Populates the schedule on the GUI side from what is in the database
	 */
	void showSchedule() throws ClassNotFoundException, SQLException {
		EmployeeDatabase db = new EmployeeDatabase();
		db.connect();		
		PreparedStatement mon = db.conn.prepareStatement("select monday, FirstName, LastName from schedule, employees where schedule.username = employees.username order by monday;");
		PreparedStatement tues = db.conn.prepareStatement("select tuesday, FirstName, LastName from schedule, employees where schedule.username = employees.username order by tuesday;");
		PreparedStatement wed = db.conn.prepareStatement("select wednesday, FirstName, LastName from schedule, employees where schedule.username = employees.username order by wednesday;");
		PreparedStatement thurs = db.conn.prepareStatement("select thursday, FirstName, LastName from schedule, employees where schedule.username = employees.username order by thursday;");
		PreparedStatement fri = db.conn.prepareStatement("select friday, FirstName, LastName from schedule, employees where schedule.username = employees.username order by friday;");
		ResultSet monRS = mon.executeQuery();
		ResultSet tuesRS, wedRS, thursRS, friRS;
		String mondayList = "", tuesdayList = "", wednesdayList = "", thursdayList = "", fridayList = "";
		while(monRS.next()) {
			int hours = monRS.getInt(1);
			if (hours < 1 || hours > 2) {
				continue;
			}
			else if (hours == 1) {
				mondayList += "9AM-5PM\n" + monRS.getString(2) + " " + monRS.getString(3) + "\n";
			}
			else if (hours == 2) {
				mondayList += "1-9PM\n" + monRS.getString(2) + " " + monRS.getString(3) + "\n";
			}
			mondayList += "\n";
		}
		monText.setText(mondayList);
		tuesRS = tues.executeQuery();
		while(tuesRS.next()) {
			int hours = tuesRS.getInt(1);
			if (hours < 1 || hours > 2) {
				continue;
			}
			else if (hours == 1) {
				tuesdayList += "9AM-5PM\n" + tuesRS.getString(2) + " " + tuesRS.getString(3) + "\n";
			}
			else if (hours == 2) {
				tuesdayList += "1-9PM\n" + tuesRS.getString(2) + " " + tuesRS.getString(3) + "\n";
			}
			tuesdayList += "\n";
		}
		tuesText.setText(tuesdayList);	
		wedRS = wed.executeQuery();
		while(wedRS.next()) {
			int hours = wedRS.getInt(1);
			if (hours < 1 || hours > 2) {
				continue;
			}
			else if (hours == 1) {
				wednesdayList += "9AM-5PM\n" + wedRS.getString(2) + " " + wedRS.getString(3) + "\n";
			}
			else if (hours == 2) {
				wednesdayList += "1-9PM\n" + wedRS.getString(2) + " " + wedRS.getString(3) + "\n";
			}
			wednesdayList += "\n";
		}
		wedText.setText(wednesdayList);
		thursRS = thurs.executeQuery();
		while(thursRS.next()) {
			int hours = thursRS.getInt(1);
			if (hours < 1 || hours > 2) {
				continue;
			}
			else if (hours == 1) {
				thursdayList += "9AM-5PM\n" + thursRS.getString(2) + " " + thursRS.getString(3) + "\n";
			}
			else if (hours == 2) {
				thursdayList += "1-9PM\n" + thursRS.getString(2) + " " + thursRS.getString(3) + "\n";
			}
			thursdayList += "\n";
		}
		thursText.setText(thursdayList);
		friRS = fri.executeQuery();
		while(friRS.next()) {
			int hours = friRS.getInt(1);
			if (hours < 1 || hours > 2) {
				continue;
			}
			else if (hours == 1) {
				fridayList += "9AM-5PM\n" + friRS.getString(2) + " " + friRS.getString(3) + "\n";
			}
			else if (hours == 2) {
				fridayList += "1-9PM\n" + friRS.getString(2) + " " + friRS.getString(3) + "\n";
			}
			fridayList += "\n";
		}
		friText.setText(fridayList);
	}


	public void handleReturn(ActionEvent event) {
		try {
			VBox root = (VBox) FXMLLoader.load(getClass().getResource("EmployerHomePage.fxml"));
			Scene scene = new Scene(root, 600, 600);

			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setTitle("Employer Home Page");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*
	 * 
	 * Show the schedule upon initialization
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			showSchedule();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
