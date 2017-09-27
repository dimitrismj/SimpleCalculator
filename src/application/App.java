package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class App extends Application {
	
	 //controller  = new Controller();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Read file fxml and draw interface.
			FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/application/View.fxml"));
			
			
            Parent root = loader.load();
            Controller controller = loader.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Image icon = new Image(getClass().getResourceAsStream("/application/Assets/App.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("JavaFX Calculator by Dimitris Baltas");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);         
            primaryStage.show();
            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                	String value = event.getCode().toString();
                	
	                	 	switch (value) {
			     		   		case "NUMPAD0" :
			     		   			value = "0";
			     		   			break;
			     		   		case "NUMPAD1" :
			     		   			value = "1";
			     		   			break;
			     		   		case "NUMPAD2" :
			     		   			value = "2";
			     		   			break;
			     		   		case "NUMPAD3" :
			     		   			value = "3";
			     		   			break;
			     		   		case "NUMPAD4" :
			     		   			value = "4";
			     		   			break;
			     		   		case "NUMPAD5" :
			     		   			value = "5";
			     		   			break;
			     		   		case "NUMPAD6" :
			     		   			value = "6";
			     		   			break;
			     		   		case "NUMPAD7" :
			     		   			value = "7";
			     		   			break;
			     		   		case "NUMPAD8" :
			     		   			value = "8";
			     		   			break;
			     		   		case "NUMPAD9" :
			     		   			value = "9";
			     		   			break;
				     		   	case "ADD" :
			     		   			value = "+";
			     		   			break;
				     		   	case "SUBTRACT" :
				     		   		value = "-";
				     		   		break;
				 		   		case "MULTIPLY" :
				 		   			value = "*";
				 		   			break;
				     		   	case "DIVIDE" :
				 		   			value = "/";
				 		   			break;
				     		   case "ENTER" :
				 		   			value = "="	;	 	
				 		   			break;
				     		  case "DECIMAL" :
				 		   			value = "."	;	 	
				 		   			break;
	                	 	}
	                	 	if (value == "+" || value == "-" || value == "*" ||
	                	 		value == "/" || value == "=") {
	                	 		controller.handleOperatorAction(value);
	                	 	}
	                	 	else if (value == "0" || value == "1" || value == "2" || value == "3" || value == "4" ||
		                	 		 value == "5" ||  value == "6" || value == "7" || value == "8" 
		                	 		 || value == "9" || value == ".")
	                	 	{
	                	 		controller.handleNumAction(value);
	                	 	}
                   
                }
            });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
