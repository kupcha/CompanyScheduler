import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class requestTimeOffController implements Initializable {
	private TextField startDate;
	private TextField endDate;
	private Button submitButton;
	private Button returnButton;
	private String username = "kupcha";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	
	public void handleSubmitButton(ActionEvent event) throws IOException {
		if (startDate.getText().equals("") || endDate.getText().equals("")) {
			System.out.println("Must submit both a start date and end date for time off.");
		}
		else {
		    try
		    {
		      // create our mysql database connection
		      String myDriver = "com.mysql.cj.jdbc.Driver";
		      String myUrl = "jdbc:mysql://cs431db.cnzuynuyygsz.us-east-2.rds.amazonaws.com:3306/CompanyScheduler";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "cs431", "satisfaction");

		      // our SQL SELECT query.
		      // if you only need a few columns, specify them by name instead of using "*"
		      String query = "INSERT INTO `CompanyScheduler`.`timeoffRequests` (`username`, `startDate`, `endDate`) VALUES ('" + username + "', '" + startDate + "', '" + endDate + "');";
		      // create the java statement
		      Statement st = conn.createStatement();
		      st.executeQuery(query);
		      st.close();
		      System.out.println("Request successfully submitted.");
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		}
		
		
		
		return;
	}
	
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
