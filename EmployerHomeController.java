import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class EmployerHomeController {

	EmployeeDatabase db = new EmployeeDatabase();
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

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
	 * Author: Kyle VanWageninge goes to the Add Employee GUI
	 */
	@FXML
	void AddEmployee(ActionEvent event) {

		try {

			BorderPane second = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
			Scene scene2 = new Scene(second, 600, 600);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Add Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void ChangeEmployee(ActionEvent event) {

	}

	@FXML
	void CheckRequest(ActionEvent event) {
		try {

			VBox second = (VBox) FXMLLoader.load(getClass().getResource("CheckRequests.fxml"));
			Scene scene2 = new Scene(second, 600, 600);
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
	void CreateSchedule(ActionEvent event) {
			
			Staff staffList;
			try {
			staffList = db.creating();
			staffList.setAvail();
			staffList.printStaffMembers();	
			}catch(Exception e) {
				System.err.println("EXCEPTION!");
				System.err.println(e.getMessage());
			}
				
						
			
			
		}

	@FXML
	void Logout(ActionEvent event) {

	}

	@FXML
	void RemoveEmployee(ActionEvent event) {
		try {

			VBox second = (VBox) FXMLLoader.load(getClass().getResource("RemoveEmployee.fxml"));
			Scene scene2 = new Scene(second, 600, 600);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("Remove Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {

	}
}