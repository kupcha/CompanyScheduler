/*
 * @author Patrick Kupcha
 * This is the controller class for the Employee Home Page. From here a logged in employee can access pages to complete a few different tasks.
 */

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class EmployeeHomeController{
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
	
	
	
	public String username; // username passed from login
	public int type; // eventually this will be passed to controller based on sql query
	


	public void setTextValues() {
		welcomeLabel.setText(username);
		if (type == 0) {
			employeeStatus.setText("Full-Time Employee");
		}else {
			employeeStatus.setText("Part-Time Employee");
		}
	}
	/*
	 * Will take user to the View Schedule page where they may see their currently scheduled shifts to work.
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
	@FXML
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
	@FXML
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