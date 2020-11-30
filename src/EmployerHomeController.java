import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

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
		try {

			BorderPane second = FXMLLoader.load(getClass().getResource("ViewEmployees.fxml"));
			Scene scene2 = new Scene(second, 600, 600);
			Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			secondStage.setTitle("View Employee");
			secondStage.setScene(scene2);
			secondStage.show();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Scene loginScene;
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
	void Logout(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "You are now logging out.");
		try {

			BorderPane second =  FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Scene scene2 = new Scene(second, 500, 500);
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
	

	public void goToCreateSchedule(ActionEvent event) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateSchedule.fxml"));
			Parent root = loader.load();
			Scene createScheduleScene = new Scene(root);
			CreateScheduleController controller = loader.getController();
			Stage employeeHomeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			employeeHomeStage.setScene(createScheduleScene);
			employeeHomeStage.setTitle("Create Next Schedule");
			employeeHomeStage.show();
	}
	
}