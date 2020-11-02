
/** 
@author Nikhil Dodley

Describes the Main class. This classes launches the GUI and will allow a user to login
 */
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Main extends Application{
	public Stage window;
	public Scene loginScene;
	
	public static void main(String [] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
	    Parent root;
		try {
			root = loader.load();
		    LogInController myController = loader.getController();
		    loginScene = new Scene(root);
		    primaryStage.setScene(loginScene);
		    primaryStage.setTitle("Login");
		    primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}