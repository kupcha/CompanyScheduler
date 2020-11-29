import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableColumn;

	public class ViewEmployeesController {


	

	    @FXML
	    private ListView<String> EmployeeL = new ListView<String>();

	    @FXML
	    private Button Back;

	    @FXML
	    private Button Populate;
	    
	    
	    ObservableList<String> items; 
	    
	    EmployeeDatabase db = new EmployeeDatabase();

	    @FXML
	    void GoBack(ActionEvent event) {
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

	    @FXML
	    void Populate(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {

	    	ArrayList<Employee> holder = new ArrayList<Employee>();
	    	ArrayList<String> holder2 = new ArrayList<String>();
	    	
	    	
	    	holder = db.getEmployees();
	    	
	    	for(int i = 0; i < holder.size(); i ++)
	    	{
	    		holder2.add(holder.get(i).getEmployee());
	    		
	    	}
	    	
	    	//items = .getList();
	    	items = FXCollections.observableArrayList(holder2);
	    	EmployeeL.setItems(items);
	    	
	    	
	    }

	}

