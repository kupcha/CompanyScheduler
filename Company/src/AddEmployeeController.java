import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

public class AddEmployeeController {
	@FXML
    private Button CreateS;

    @FXML
    private Button Add;
    
    @FXML
    private Button AddEmployee;
    
    @FXML
    private Button CheckR;

    @FXML
    private Button ChangeE;

    @FXML
    private Button RemoveE;

    @FXML
    private Button Logout;
	@FXML
	private TextField First_Name;

	@FXML
	private RadioButton Full_Time;

	@FXML
	private RadioButton Part_Time;

	@FXML
	private TextField Last_Name;

	@FXML
	private DatePicker DOB;

	@FXML
	private TextField Email_Name;

	@FXML
	private TextField PhoneNumber;


	@FXML
	private Button Exit;

	@FXML
	private TextField UserName;

	@FXML
	private TextField Password;

	@FXML
	private TextField Street_Name;

	@FXML
	private TextField Zip_Name;

	@FXML
	private TextField State_Name;

	@FXML
	private TextField City_Name;

	String first_name;
	String last_name;
	String username;
	String password;
	String Dob;
	String email;
	String mobile;
	String address;
	String city;
	String state;
	String zipcode;

	EmployeeDatabase db = new EmployeeDatabase();

	
	
	
	
	/*
	 * Author: Kyle VanWageninge
	 * 
	 * Turns all the text from the TextFields into Strings
	 */
	private void getAllText() {

		first_name = First_Name.getText();
		last_name = Last_Name.getText();
		username = UserName.getText();
		password = Password.getText();
		Dob = null;
		email = Email_Name.getText();
		mobile = PhoneNumber.getText();
		address = Street_Name.getText();
		city = City_Name.getText();
		state = State_Name.getText();
		zipcode = Zip_Name.getText();

	}

	/*
	 * Author: Kyle VanWageninge
	 * 
	 * Clears all the text out of the TextFields
	 */
	private void clearAllText() {
		First_Name.clear();
		Last_Name.clear();
		UserName.clear();
		Password.clear();
		Email_Name.clear();
	//	DOB.setValue(null);
		PhoneNumber.clear();
		Street_Name.clear();
		City_Name.clear();
		State_Name.clear();
		Zip_Name.clear();
		
		
	}

	public boolean checkText() {
		
		if(first_name.equals("") || last_name.equals("") ||username.equals("") || password.equals("") ||
				email.equals("") || mobile.equals("") || address.equals("") || city.equals("") || state.equals("") 
				|| zipcode.equals("")) {
			
			return false;
			
		}else {
			
			return true;
		}
		
		
		
		
		
	}
	
	/*
	 * Author: Kyle VanWageninge
	 * 
	 * Add an employee into the database, first get all the text then call the db
	 * class to connect to the database and send in the info Clear text and go back
	 * one gui
	 */
	@FXML
	void AddEmployeeDB(ActionEvent event) {

		getAllText();
		
		if(!(checkText())){
			JOptionPane.showMessageDialog(null, "ERROR\nInput left blank\nPlease try again");
			return;
		
		}else {
		try {
			int type;
			if(Part_Time.isSelected())
			{
				type = 1;
				
			}else
			{
				type = 0;
				
			}
			
			db.add(first_name, last_name, username, password, Dob, email, mobile, address, city, state, zipcode, 1, type);
		} catch (Exception e) {
			System.err.println("EXCEPTION!");
			System.err.println(e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "Registration Complete\n New Employee added");


		clearAllText();
		
		}
	}
	
	@FXML
	void AddEmployee1(ActionEvent event) {
	
		
	}
	
	
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
	public Scene loginScene;
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
	
	
	public void goToCreateSchedule(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateScheduleUpdate.fxml"));
		Parent root = loader.load();
		Scene createScheduleScene = new Scene(root);
		CreateScheduleController controller = loader.getController();
		Stage employeeHomeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		employeeHomeStage.setScene(createScheduleScene);
		employeeHomeStage.setTitle("Create Next Schedule");
		employeeHomeStage.show();
}
	

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


}
