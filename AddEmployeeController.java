import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AddEmployeeController {

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
	private Button Add;

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
		PhoneNumber.clear();
		Street_Name.clear();
		City_Name.clear();
		State_Name.clear();
		Zip_Name.clear();

	}

	/*
	 * Author: Kyle VanWageninge
	 * 
	 * Add an employee into the database, first get all the text then call the db
	 * class to connect to the database and send in the info Clear text and go back
	 * one gui
	 */
	@FXML
	void AddEmployee(ActionEvent event) {

		getAllText();

		try {
			db.add(first_name, last_name, username, password, Dob, email, mobile, address, city, state, zipcode, 1, 0);
		} catch (Exception e) {
			System.err.println("EXCEPTION!");
			System.err.println(e.getMessage());
		}

		clearAllText();
		try {
			VBox root = (VBox) FXMLLoader.load(getClass().getResource("EmployerGUI.fxml"));
			Scene scene = new Scene(root, 600, 600);

			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setTitle("Employer Home Page");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Author: Kyle VanWageninge
	 * 
	 * Go back to employerGUI
	 */
	@FXML
	void ExitAction(ActionEvent event) {
		try {
			VBox root = (VBox) FXMLLoader.load(getClass().getResource("EmployerGUI.fxml"));
			Scene scene = new Scene(root, 600, 600);

			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setTitle("Employer Home Page");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
