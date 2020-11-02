/*
 * @author Patrick Kupcha
 * This is the controller class for the Employee Home Page. From here a logged in employee can access pages to complete a few different tasks.
 */

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EmployeeHomeController{
	public Button checkScheduleButton;
	public Button availabilityButton;
	public Button logoutButton;
	public Button timeOffButton;
	public String username; // eventually username will get passed to controller before launching scene
	public int type; // eventually this will be passed to controller based on sql query
	public Label welcomeLabel = new Label();
	public Text employeeStatus = new Text();

	public void setTextValues() {
		welcomeLabel.setText("Welcome, " + username + "!");
		if (type == 0) {
			employeeStatus.setText("Employee Status: Full-Time Employee");
		}else {
			employeeStatus.setText("Employee Status: Part-Time Employee");
		}
	}
	/*
	 * Will take user to the View Schedule page where they may see their currently scheduled shifts to work.
	 */
	public void handleCheckSchedule(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentSchedule.fxml"));
        Parent root = loader.load();
        Scene currentScheduleScene = new Scene(root);
        CurrentScheduleController controller = loader.getController();
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
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestTimeOff.fxml"));
        Parent root = loader.load();
        Scene requestTimeOffScene = new Scene(root);
        requestTimeOffController controller = loader.getController();
        Stage requestTimeOffStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        requestTimeOffStage.setScene(requestTimeOffScene);
        requestTimeOffStage.setTitle("Request Time Off");
        requestTimeOffStage.show();
		return;
	}
	
	/*
	 * Will log the user out of the program and close.
	 */
	public void handleLogout(ActionEvent event) throws IOException{
	    Stage stage = (Stage) logoutButton.getScene().getWindow();
	    stage.close();
	    return;
	}
	
	/*
	 * Will take the user to a page where they can submit their availability to work for the given week.
	 */
	public void handleAvailability(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Availability.fxml"));
        Parent root = loader.load();
        Scene availabilityScene = new Scene(root);
        AvailabilityController controller = loader.getController();
        Stage availabilityStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        availabilityStage.setScene(availabilityScene);
        availabilityStage.setTitle("Request Time Off");
        availabilityStage.show();
		return;
	}



}