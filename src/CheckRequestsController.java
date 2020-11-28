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

public class CheckRequestsController {

    @FXML
    private ListView<String> List = new ListView<String>();
    @FXML
    private Button Confirm;

    @FXML
    private Button Deny;

    @FXML
    private Button Back;

    
     
    ObservableList<String> items; 
    @FXML
    private Button Pop;
    EmployeeDatabase db = new EmployeeDatabase();
    
    public void Populate(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	
    	
    	
    	ArrayList<String> holder = new ArrayList<String>();
    	holder = db.getRequests();
    	//items = .getList();
    	items = FXCollections.observableArrayList(holder);
    	List.setItems(items);
    
	}
   
    
    
    
    @FXML
    void Confirm(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
    	
    	String selected = List.getSelectionModel().getSelectedItem();
    	int selectedId = List.getSelectionModel().getSelectedIndex();
    	if(!(selected.equals(""))){
    	db.acceptedRequest(selected);
    	
    	List.getItems().remove(selectedId);
    	}
    }

    @FXML
    void Deny(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
    	
    	//String selected = List.getSelectionModel().getSelectedItem();
    	
    	String selected = List.getSelectionModel().getSelectedItem();
    	int selectedId = List.getSelectionModel().getSelectedIndex();
    	if(!(selected.equals(""))){
    	selected = selected.concat(" Denied");
    	db.acceptedRequest(selected);	
    	
    	List.getItems().remove(selectedId);
    	}
   }

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

}



 
    


