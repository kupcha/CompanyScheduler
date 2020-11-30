/**
 * @author Nikhil Dodley, Patrick Kupcha
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;

public class LogInController {

	Connection conn =null;
	PreparedStatement pst;
	ResultSet rs;
	
	@FXML
    public Button Login;

    @FXML
    public Button Exit;

    @FXML
    public TextField userNameTxt;

    @FXML
    public PasswordField passwordTxt;
    public String username;
    public String password;

    @FXML
    void ExitAction(ActionEvent event) {
	    
    	Stage stage = (Stage) Exit.getScene().getWindow();
	    stage.close();
	    return;
    }

    @FXML
    void LoginAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
    	
    	username = userNameTxt.getText();
    	password = passwordTxt.getText();
    	
        if(username.equals("") && password.equals("")) {
        	//JOptionPane.showMessageDialog(null, "UserName or Password Blank");
        	return;
        }
        else {
        	
        	try {
        		
        	    String dbuser = "cs431";
        	    String dbpassw = "satisfaction";
        	    String url = "jdbc:mysql://cs431db.cnzuynuyygsz.us-east-2.rds.amazonaws.com:3306/CompanyScheduler";
        	    Class.forName("com.mysql.cj.jdbc.Driver");
        	    //Class.forName("com.mysql.jdbc.Driver");
        	    conn = DriverManager.getConnection(url,dbuser,dbpassw);
        		
        	    pst = conn.prepareStatement("select access, type from CompanyScheduler.users where username = ? and password = ?;");
        	    pst.setString(1, username);
        	    pst.setString(2, password);
        	    
        	    rs = pst.executeQuery();
        	    
      	      	if (rs.next()) {
      	      		//JOptionPane.showMessageDialog(null, "Login Success");
      	      		int access = rs.getInt(1);
      	      		int type = rs.getInt(2);
      	      		
      	      		//0 means admin/employer
      	      		if (access == 0) {
      	      			//JOptionPane.showMessageDialog(null, "Login Success\n Welcome Admin");
      	      			FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployerGUI.fxml"));
      	      			Parent root = loader.load();
      	      			Scene employeeHomeScene = new Scene(root);
      	      			EmployerHomeController controller = loader.getController();
      	      			Stage employerHomeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      	      			employerHomeStage.setScene(employeeHomeScene);
      	      			employerHomeStage.setTitle("Employer Home");
      	      			employerHomeStage.show();
    		        	return;
      	      		}
      	      		else if (access == 1) {
      	      			//JOptionPane.showMessageDialog(null, "Login Success\n Welcome User");
      	      			FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
      	      			Parent root = loader.load();
      	      			Scene employeeHomeScene = new Scene(root);
      	      			EmployeeHomeController controller = loader.getController();
      	      			controller.username = username;
      	      			controller.type = type;
      	      			controller.setTextValues();
      	      			Stage employeeHomeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      	      			employeeHomeStage.setScene(employeeHomeScene);
      	      			employeeHomeStage.setTitle("Employee Home");
      	      			employeeHomeStage.show();
      	      		}
      	      	}
      	      	else {
      	      		//JOptionPane.showMessageDialog(null, "Login Failed");
        	    	userNameTxt.setText("");
        	    	passwordTxt.setText("");
        	    	userNameTxt.requestFocus();
      	      	}
        	    
        	}
        	catch(ClassNotFoundException ex) {
        		
        		Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);	
        	}
        	catch(SQLException ex) {
        		
        		Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        	}
        	
        }
    	
    }
    
}
