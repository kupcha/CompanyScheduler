import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableColumn;

public class RemoveEmployeeController {

    @FXML
    private ListView<String> List = new ListView<String>();
    @FXML
    private Button Confirm;

   

    
     
    ObservableList<String> items; 
   
    EmployeeDatabase db = new EmployeeDatabase();
   /* 
    public void Populate(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	
    	
    	
    	ArrayList<String> holder = new ArrayList<String>();
    	holder = db.getRequests();
    	//items = .getList();
    	items = FXCollections.observableArrayList(holder);
    	List.setItems(items);
    
	}
   
    */
    
     void pop() throws ClassNotFoundException, SQLException, ParseException {
    	
    	 ArrayList<Employee> holder = new ArrayList<Employee>();
	    	ArrayList<String> holder2 = new ArrayList<String>();
	    	
	    	
	    	holder = db.getEmployees();
	    	
	    	for(int i = 0; i < holder.size(); i ++)
	    	{
	    		holder2.add(holder.get(i).getEmployee());
	    		
	    	}
	    	
	    	//items = .getList();
	    	items = FXCollections.observableArrayList(holder2);
	    	List.setItems(items);
    	
    }
    
    @FXML
	void initialize() throws ClassNotFoundException, SQLException, ParseException {
    	
    	pop();
	}
    
    @FXML
    void Confirm(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
    	
    	String selected = List.getSelectionModel().getSelectedItem();
    	int selectedId = List.getSelectionModel().getSelectedIndex();
    	if(!(selected.equals(""))){
    	db.remove(selected);
    	
    	List.getItems().remove(selectedId);
    	}
    }

   

   
    
  
    @FXML
	void AddEmployee(ActionEvent event) {

		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("AddEmployeeUpdate.fxml"));
			Scene scene2 = new Scene(second, 930, 565);
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

			AnchorPane second = FXMLLoader.load(getClass().getResource("ViewEmployeesUpdate.fxml"));
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
		
	}

}



 
    


