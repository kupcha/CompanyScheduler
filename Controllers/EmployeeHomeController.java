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

public class EmployeeHomeController implements Initializable{
	public Button checkScheduleButton;
	public Button availabilityButton;
	public Button logoutButton;
	public Button timeOffButton;
	public String username = "kupcha"; // eventually username will get passed to controller before launching scene
	public int employeeType = 0; // eventually this will be passed to controller based on sql query
	public Label welcomeLabel = new Label();
	public Text employeeStatus = new Text();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomeLabel.setText("Welcome, " + username + "!");
		if (employeeType == 0) {
			employeeStatus.setText("Employee Status: Full-Time Employee");
		}else {
			employeeStatus.setText("Employee Status: Part-Time Employee");
		}
		
	}
	
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
	
	
	public void handleLogout(ActionEvent event) throws IOException{
	    Stage stage = (Stage) logoutButton.getScene().getWindow();
	    stage.close();
	    return;
	}
	
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