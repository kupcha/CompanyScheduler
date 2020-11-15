import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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

public class RemoveEmployeeController {

    @FXML
    private Button Return;

    @FXML
    void goBack(ActionEvent event) {
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
