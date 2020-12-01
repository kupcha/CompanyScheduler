/*
 * @author Patrick Kupcha
 * This is the controller class for the Request Time Off page. Users are able to submit a start and end date for days they would like to be omitted from the work schedule. This request will be visible to any admin for approval/denial.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class requestTimeOffController implements Initializable {
	public TextField startDate;
	public TextField endDate;
	public Button submitButton;
	public Button returnButton;
	public String username;
	public int type;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public boolean checkValidInput(String input) {
		if (input.length() != 10) {
			return false;
		}else {
			for (int i = 0; i < input.length(); i++) {
				char curr = input.charAt(i);
				switch (i) {
				case 0: 
					if (!Character.isDigit(curr)) {
						return false;
					}
					continue;
				case 1:
					if (!Character.isDigit(curr)) {
						return false;
					}
					continue;
				case 2:
					if (!Character.isDigit(curr)) {
						return false;
					}
					continue;
				case 3:
					if (!Character.isDigit(curr)) {
						return false;
					}
					continue;
				case 4:
					if (curr != '-') {
						return false;
					}
					continue;
				case 5:
					if (!Character.isDigit(curr)) {
						return false;
					}
					continue;
				case 6:
					if (!Character.isDigit(curr)) {
						return false;
					}
					continue;
				case 7:
					if (curr != '-') {
						return false;
					}
					continue;
				case 8:
					if (!Character.isDigit(curr)) {
						return false;
					}
					continue;
				case 9:
					if (!Character.isDigit(curr)) {
						return false;
					}
					continue;
				default: continue;
				}
			}
		}
		return true;
	}
	
	
	/*
	 * Submits the current selected dates for a time off request for the current user.
	 */
	public void handleSubmitButton(ActionEvent event) throws IOException {
		if (startDate.getText().equals("") || endDate.getText().equals("")) {
			System.out.println("Must submit both a start date and end date for time off.");
		}
		else if (!checkValidInput(startDate.getText()) || !checkValidInput(endDate.getText())) {
			System.out.println("Please properly format input.");
			return;
		}
		else {
		    try
		    {
		      // create our mysql database connection
		      String myDriver = "com.mysql.cj.jdbc.Driver";
		      String myUrl = "jdbc:mysql://cs431db.cnzuynuyygsz.us-east-2.rds.amazonaws.com:3306/CompanyScheduler";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "cs431", "satisfaction");

		      // our SQL SELECT query.
		      // if you only need a few columns, specify them by name instead of using "*"
		      String query = "INSERT INTO `CompanyScheduler`.`timeoffRequests` (`username`, `startDate`, `endDate`) VALUES ('" + username + "', '" + startDate.getText() + "', '" + endDate.getText() + "');";
		      // create the java statement
		      Statement st = conn.createStatement();
		      st.execute(query);
		      st.close();
		      System.out.println("Request successfully submitted.");
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		}
		return;
	}
	
	/*
	 * Will return a user to the Employee Home Page
	 */
	public void handleReturnButton(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
        Parent root = loader.load();
        Scene employeeHomeScene = new Scene(root);
        EmployeeHomeController controller = loader.getController();
        controller.username = username;
        Stage employeeHomeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        employeeHomeStage.setScene(employeeHomeScene);
        employeeHomeStage.setTitle("Employee Home");
        employeeHomeStage.show();
		return;
	}

}
