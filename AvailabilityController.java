
/*
 * @author Patrick Kupcha
 * This is the controller class for the View/Edit Availability page
 */
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
	public String username; // in the future this will be set when scene is launched
	public int employeeType = 0;
	public int openAvail; // 0 not open, 1 open availability
	public int mondayAvailability; // 0 open (9am-9pm), 1 morning shift (9am-5pm), 2 evening (1pm-9pm)
	public int tuesdayAvailability;
	public int wednesdayAvailability;
	public int thursdayAvailability;
	public int fridayAvailability;
	public Text employeeGreeting = new Text();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (employeeType == 0) {
			employeeGreeting
					.setText(username + ", you are a full-time employee and will be allocated 40 hours per week.");
		} else {
			employeeGreeting.setText(
					username + ", you are a part-time employee and will be allocated less than 40 hours per week.");
		}
	}

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

	public void handleMon9to5() {
		ToggleGroup toggle = new ToggleGroup();
		monday9to5.setToggleGroup(toggle);
		monday1to9.setToggleGroup(toggle);
		monday9to9.setToggleGroup(toggle);
		openAvailability.setToggleGroup(toggle);
		mondayAvailability = 1;
		openAvail = 0;
	}

	public void handleMon9to9() {
		ToggleGroup toggle = new ToggleGroup();
		monday9to5.setToggleGroup(toggle);
		monday1to9.setToggleGroup(toggle);
		openAvailability.setToggleGroup(toggle);
		monday9to9.setToggleGroup(toggle);
		mondayAvailability = 0;
		openAvail = 0;
	}

	public void handleTues1to9() {
		ToggleGroup tuesToggle = new ToggleGroup();
		tuesday9to5.setToggleGroup(tuesToggle);
		tuesday1to9.setToggleGroup(tuesToggle);
		tuesday9to9.setToggleGroup(tuesToggle);
		openAvailability.setToggleGroup(tuesToggle);
		tuesdayAvailability = 2;
		openAvail = 0;
	}

	public void handleTues9to5() {
		ToggleGroup tuesToggle = new ToggleGroup();
		tuesday9to5.setToggleGroup(tuesToggle);
		tuesday1to9.setToggleGroup(tuesToggle);
		tuesday9to9.setToggleGroup(tuesToggle);
		openAvailability.setToggleGroup(tuesToggle);
		tuesdayAvailability = 1;
		openAvail = 0;
	}

	public void handleTues9to9() {
		ToggleGroup tuesToggle = new ToggleGroup();
		tuesday9to5.setToggleGroup(tuesToggle);
		tuesday1to9.setToggleGroup(tuesToggle);
		tuesday9to9.setToggleGroup(tuesToggle);
		openAvailability.setToggleGroup(tuesToggle);
		tuesdayAvailability = 0;
		openAvail = 0;
	}

	public void handleWed1to9() {
		ToggleGroup wedToggle = new ToggleGroup();
		wednesday9to5.setToggleGroup(wedToggle);
		wednesday1to9.setToggleGroup(wedToggle);
		wednesday9to9.setToggleGroup(wedToggle);
		openAvailability.setToggleGroup(wedToggle);
		wednesdayAvailability = 2;
		openAvail = 0;
	}

	public void handleWed9to5() {
		ToggleGroup wedToggle = new ToggleGroup();
		wednesday9to5.setToggleGroup(wedToggle);
		wednesday1to9.setToggleGroup(wedToggle);
		wednesday9to9.setToggleGroup(wedToggle);
		openAvailability.setToggleGroup(wedToggle);
		wednesdayAvailability = 1;
		openAvail = 0;
	}

	public void handleWed9to9() {
		ToggleGroup wedToggle = new ToggleGroup();
		wednesday9to5.setToggleGroup(wedToggle);
		wednesday1to9.setToggleGroup(wedToggle);
		wednesday9to9.setToggleGroup(wedToggle);
		openAvailability.setToggleGroup(wedToggle);
		wednesdayAvailability = 0;
		openAvail = 0;
	}

	public void handleThurs1to9() {
		ToggleGroup thursToggle = new ToggleGroup();
		thursday9to5.setToggleGroup(thursToggle);
		thursday1to9.setToggleGroup(thursToggle);
		thursday9to9.setToggleGroup(thursToggle);
		openAvailability.setToggleGroup(thursToggle);
		thursdayAvailability = 2;
		openAvail = 0;
	}

	public void handleThurs9to5() {
		ToggleGroup thursToggle = new ToggleGroup();
		thursday9to5.setToggleGroup(thursToggle);
		thursday1to9.setToggleGroup(thursToggle);
		thursday9to9.setToggleGroup(thursToggle);
		openAvailability.setToggleGroup(thursToggle);
		thursdayAvailability = 1;
		openAvail = 0;
	}

	public void handleThurs9to9() {
		ToggleGroup thursToggle = new ToggleGroup();
		thursday9to5.setToggleGroup(thursToggle);
		thursday1to9.setToggleGroup(thursToggle);
		thursday9to9.setToggleGroup(thursToggle);
		openAvailability.setToggleGroup(thursToggle);
		thursdayAvailability = 0;
		openAvail = 0;
	}

	public void handleFri1to9() {
		ToggleGroup friToggle = new ToggleGroup();
		friday9to5.setToggleGroup(friToggle);
		friday1to9.setToggleGroup(friToggle);
		friday9to9.setToggleGroup(friToggle);
		openAvailability.setToggleGroup(friToggle);
		fridayAvailability = 2;
		openAvail = 0;
	}

	public void handleFri9to5() {
		ToggleGroup friToggle = new ToggleGroup();
		friday9to5.setToggleGroup(friToggle);
		friday1to9.setToggleGroup(friToggle);
		friday9to9.setToggleGroup(friToggle);
		openAvailability.setToggleGroup(friToggle);
		fridayAvailability = 1;
		openAvail = 0;
	}

	public void handleFri9to9() {
		ToggleGroup friToggle = new ToggleGroup();
		friday9to5.setToggleGroup(friToggle);
		friday1to9.setToggleGroup(friToggle);
		friday9to9.setToggleGroup(friToggle);
		openAvailability.setToggleGroup(friToggle);
		fridayAvailability = 0;
		openAvail = 0;
	}

	public void handleSubmitButton() {
		try {
			// create our mysql database connection
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://cs431db.cnzuynuyygsz.us-east-2.rds.amazonaws.com:3306/CompanyScheduler";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "cs431", "satisfaction");
			// our SQL SELECT query.
			String query;
			if (openAvail == 0) {
				query = "UPDATE `CompanyScheduler`.`availability` SET `monday` = '" + mondayAvailability
						+ "', `tuesday` = '" + tuesdayAvailability + "', `wednesday` = '" + wednesdayAvailability
						+ "', `thursday` = '" + thursdayAvailability + "', `friday` = '" + fridayAvailability
						+ "' WHERE (`username` = '" + username + "');";
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
