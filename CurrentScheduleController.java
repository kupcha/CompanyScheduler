/*
 * @author Patrick Kupcha
 * This is the controller class for the View Current Schedule page. There is no input on this page, it will show an employee their current week's schedule for each day of the week.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CurrentScheduleController implements Initializable {
	public String username = "kupcha"; // this will eventually get passed to controller
	public Text employeeGreeting = new Text();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employeeGreeting.setText(username + ", your schedule for the week is as follows:");
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
