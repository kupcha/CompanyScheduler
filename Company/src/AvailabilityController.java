/*
 * @author Patrick Kupcha
 * This is the controller class for the View/Edit Availability page
 */
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AvailabilityController implements Initializable {
	public RadioButton openAvailability;
	public RadioButton monday9to5;
	public RadioButton monday1to9;
	public RadioButton monday9to9;
	public RadioButton tuesday9to5;
	public RadioButton tuesday1to9;
	public RadioButton tuesday9to9;
	public RadioButton wednesday9to5;
	public RadioButton wednesday1to9;
	public RadioButton wednesday9to9;
	public RadioButton thursday9to5;
	public RadioButton thursday1to9;
	public RadioButton thursday9to9;
	public RadioButton friday9to5;
	public RadioButton friday1to9;
	public RadioButton friday9to9;
	public String username; // set when scene is launched == used for personalization
	public int type;
	public int employeeType = 0;
	public int openAvail; // 0 not open, 1 open availability
	public int mondayAvailability; // 0 open (9am-9pm), 1 morning shift (9am-5pm), 2 evening (1pm-9pm)
	public int tuesdayAvailability;
	public int wednesdayAvailability;
	public int thursdayAvailability;
	public int fridayAvailability;
	public Text employeeGreeting = new Text();
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
    
    
    @FXML
    private Button submitButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	/*
	 * This function is called on launching of scene to personally describe the user logging in
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
	 * This function is called on launching of scene to personally describe the user logging in
	 */
	public void setTextValues() {
		if (employeeType == 0) {
			employeeGreeting.setText(username + ", you are a full-time employee and will be allocated 40 hours per week.");
		} else {
			employeeGreeting.setText(
					username + ", you are a part-time employee and will be allocated less than 40 hours per week.");
		}
	}

	/*
	 * Handles a user selecting open availability
	 */
	public void handleOpenAvailability() {
		ToggleGroup availabilityToggle = new ToggleGroup();
		openAvailability.setToggleGroup(availabilityToggle);
		monday9to5.setToggleGroup(availabilityToggle);
		monday1to9.setToggleGroup(availabilityToggle);
		monday9to9.setToggleGroup(availabilityToggle);
		tuesday9to5.setToggleGroup(availabilityToggle);
		tuesday1to9.setToggleGroup(availabilityToggle);
		tuesday9to9.setToggleGroup(availabilityToggle);
		wednesday9to5.setToggleGroup(availabilityToggle);
		wednesday1to9.setToggleGroup(availabilityToggle);
		wednesday9to9.setToggleGroup(availabilityToggle);
		thursday9to5.setToggleGroup(availabilityToggle);
		thursday1to9.setToggleGroup(availabilityToggle);
		thursday9to9.setToggleGroup(availabilityToggle);
		friday9to5.setToggleGroup(availabilityToggle);
		friday1to9.setToggleGroup(availabilityToggle);
		friday9to9.setToggleGroup(availabilityToggle);
		openAvail = 1;
		return;
	}

	public void handleMon1to9() {
		ToggleGroup toggle = new ToggleGroup();
		monday9to5.setToggleGroup(toggle);
		monday1to9.setToggleGroup(toggle);
		monday9to9.setToggleGroup(toggle);
		openAvailability.setToggleGroup(toggle);
		mondayAvailability = 2;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleMon9to5() {
		ToggleGroup toggle = new ToggleGroup();
		monday9to5.setToggleGroup(toggle);
		monday1to9.setToggleGroup(toggle);
		monday9to9.setToggleGroup(toggle);
		openAvailability.setToggleGroup(toggle);
		mondayAvailability = 1;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleMon9to9() {
		ToggleGroup toggle = new ToggleGroup();
		monday9to5.setToggleGroup(toggle);
		monday1to9.setToggleGroup(toggle);
		openAvailability.setToggleGroup(toggle);
		monday9to9.setToggleGroup(toggle);
		mondayAvailability = 0;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleTues1to9() {
		ToggleGroup tuesToggle = new ToggleGroup();
		tuesday9to5.setToggleGroup(tuesToggle);
		tuesday1to9.setToggleGroup(tuesToggle);
		tuesday9to9.setToggleGroup(tuesToggle);
		openAvailability.setToggleGroup(tuesToggle);
		tuesdayAvailability = 2;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleTues9to5() {
		ToggleGroup tuesToggle = new ToggleGroup();
		tuesday9to5.setToggleGroup(tuesToggle);
		tuesday1to9.setToggleGroup(tuesToggle);
		tuesday9to9.setToggleGroup(tuesToggle);
		openAvailability.setToggleGroup(tuesToggle);
		tuesdayAvailability = 1;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleTues9to9() {
		ToggleGroup tuesToggle = new ToggleGroup();
		tuesday9to5.setToggleGroup(tuesToggle);
		tuesday1to9.setToggleGroup(tuesToggle);
		tuesday9to9.setToggleGroup(tuesToggle);
		openAvailability.setToggleGroup(tuesToggle);
		tuesdayAvailability = 0;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleWed1to9() {
		ToggleGroup wedToggle = new ToggleGroup();
		wednesday9to5.setToggleGroup(wedToggle);
		wednesday1to9.setToggleGroup(wedToggle);
		wednesday9to9.setToggleGroup(wedToggle);
		openAvailability.setToggleGroup(wedToggle);
		wednesdayAvailability = 2;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleWed9to5() {
		ToggleGroup wedToggle = new ToggleGroup();
		wednesday9to5.setToggleGroup(wedToggle);
		wednesday1to9.setToggleGroup(wedToggle);
		wednesday9to9.setToggleGroup(wedToggle);
		openAvailability.setToggleGroup(wedToggle);
		wednesdayAvailability = 1;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleWed9to9() {
		ToggleGroup wedToggle = new ToggleGroup();
		wednesday9to5.setToggleGroup(wedToggle);
		wednesday1to9.setToggleGroup(wedToggle);
		wednesday9to9.setToggleGroup(wedToggle);
		openAvailability.setToggleGroup(wedToggle);
		wednesdayAvailability = 0;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleThurs1to9() {
		ToggleGroup thursToggle = new ToggleGroup();
		thursday9to5.setToggleGroup(thursToggle);
		thursday1to9.setToggleGroup(thursToggle);
		thursday9to9.setToggleGroup(thursToggle);
		openAvailability.setToggleGroup(thursToggle);
		thursdayAvailability = 2;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleThurs9to5() {
		ToggleGroup thursToggle = new ToggleGroup();
		thursday9to5.setToggleGroup(thursToggle);
		thursday1to9.setToggleGroup(thursToggle);
		thursday9to9.setToggleGroup(thursToggle);
		openAvailability.setToggleGroup(thursToggle);
		thursdayAvailability = 1;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleThurs9to9() {
		ToggleGroup thursToggle = new ToggleGroup();
		thursday9to5.setToggleGroup(thursToggle);
		thursday1to9.setToggleGroup(thursToggle);
		thursday9to9.setToggleGroup(thursToggle);
		openAvailability.setToggleGroup(thursToggle);
		thursdayAvailability = 0;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleFri1to9() {
		ToggleGroup friToggle = new ToggleGroup();
		friday9to5.setToggleGroup(friToggle);
		friday1to9.setToggleGroup(friToggle);
		friday9to9.setToggleGroup(friToggle);
		openAvailability.setToggleGroup(friToggle);
		fridayAvailability = 2;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleFri9to5() {
		ToggleGroup friToggle = new ToggleGroup();
		friday9to5.setToggleGroup(friToggle);
		friday1to9.setToggleGroup(friToggle);
		friday9to9.setToggleGroup(friToggle);
		openAvailability.setToggleGroup(friToggle);
		fridayAvailability = 1;
		openAvail = 0;
	}

	/*
	 * Handles a user selecting availability option
	 */
	public void handleFri9to9() {
		ToggleGroup friToggle = new ToggleGroup();
		friday9to5.setToggleGroup(friToggle);
		friday1to9.setToggleGroup(friToggle);
		friday9to9.setToggleGroup(friToggle);
		openAvailability.setToggleGroup(friToggle);
		fridayAvailability = 0;
		openAvail = 0;
	}

	/*
	 * Handles a user submitting their availability choices -- database is updated with selected
	 * values.
	 */
	public void handleSubmitButton() {
		try {
			// create our mysql database connection
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://cs431db.cnzuynuyygsz.us-east-2.rds.amazonaws.com:3306/CompanyScheduler";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "cs431", "satisfaction");
			// our SQL SELECT query.
			String query = "UPDATE `CompanyScheduler`.`availability` SET";
			int daysSelected = 0;
			if (openAvail == 0) {
				if (monday9to9.isSelected() || monday9to5.isSelected() || monday1to9.isSelected()) {
					query += " `monday` = '" + mondayAvailability + "'";
					daysSelected++;
				}
				if (tuesday9to9.isSelected() || tuesday9to5.isSelected() || tuesday1to9.isSelected()) {
					if (daysSelected > 0) {
						query += ", ";
					}
					query += "`tuesday` = '" + tuesdayAvailability + "'";
					daysSelected++;
				}
				if (wednesday9to9.isSelected() || wednesday9to5.isSelected() || wednesday1to9.isSelected()) {
					if (daysSelected > 0) {
						query += ", ";
					}
					query += "`wednesday` = '" + wednesdayAvailability + "'";
					daysSelected++;
				}
				if (thursday9to9.isSelected() || thursday9to5.isSelected() || thursday1to9.isSelected()) {
					if (daysSelected > 0) {
						query += ", ";
					}
					query += "`thursday` = '" + thursdayAvailability + "'";
					daysSelected++;
				}
				if (friday9to9.isSelected() || friday9to5.isSelected() || friday1to9.isSelected()) {
					if (daysSelected > 0) {
						query += ", ";
					}
					query += "`friday` = '" + fridayAvailability + "'";
				}
				query += "WHERE (`username` = '" + username + "');";
			} else {
				query = "UPDATE `CompanyScheduler`.`availability` SET `monday` = '0', `tuesday` = '0', `wednesday` = '0', `thursday` = '0', `friday` = '0' WHERE (`username` = '"
						+ username + "');";
			}
			// create the java statement
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			System.out.println("Availability successfully updated!");
			// close connection
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	/*
	 * Takes a user to their own "View Schedule" scene
	 */
public void handleCheckSchedule(ActionEvent event) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentScheduleUpdate.fxml"));
    Parent root = loader.load();
    Scene currentScheduleScene = new Scene(root);
    CurrentScheduleController controller = loader.getController();
    controller.username = this.username;
    controller.type = type;
    controller.setTextValues();
    controller.setTextValues2();
    Stage currentScheduleStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    currentScheduleStage.setScene(currentScheduleScene);
    currentScheduleStage.setTitle("Viewing Current Schedule");
    currentScheduleStage.show();
	return;
	}
	
	/*
	 * Will take the user to the page where they may submit a request for time off.
	 */
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
    	
	}
}