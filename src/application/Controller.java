package application;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Controller implements Initializable {
		 @FXML
		 private Button numpad;
		 @FXML
		 private Label lblOutput;
		 @FXML
		 private Label lblHistory; 
		 @FXML
		 private Model model = new Model();

		 private static final String empty = "";
		 private String operator = empty;
		 private BigDecimal numberA = BigDecimal.ZERO;
		 private BigDecimal numberB = BigDecimal.ZERO;
		 private boolean start = true;
		 private boolean numberBhasAdded = false;
		 private boolean doNotEraseHistory = false;
		 private int operatorCounts = 0;
		 private String result;

	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
	   }

	   @FXML
	   public void numPadClick(ActionEvent event) {
		   String value = ((Button)event.getSource()).getText();
		   handleNumAction(value);
	   } 
	   
	   
	   public void handleNumAction(String value) {
		   if (start) {
			   lblOutput.setText(empty);
			   if (doNotEraseHistory== false) {
				   lblHistory.setText(empty);
			   }
	       start = false;
	        }
		   lblOutput.setText(lblOutput.getText() + value);
		   lblHistory.setText(lblHistory.getText() + value);
		   
		   if (operatorCounts == 0 ) {
			   numberA = new BigDecimal(lblOutput.getText());
			   System.out.println("numberA :" + numberA);
		   }
		   else if (operatorCounts >= 1 ) {
			   numberB = new BigDecimal(lblOutput.getText());
			   numberBhasAdded = true;
			   System.out.println("numberB :" + numberB);
		   }
	   }
	  
	   
	   @FXML
	    private void operatorClick(ActionEvent event) {
	        String value = ((Button)event.getSource()).getText();
	        handleOperatorAction(value);    
	    }  
	   
	   public void handleOperatorAction (String value) {
		   operatorCounts++;
	        
	        if (value.equals("AC")) {
	        	lblOutput.setText("0");
	        	lblHistory.setText("0");
	        	numberA = BigDecimal.ZERO;
	        	numberB = BigDecimal.ZERO;
	        	start = true;
	        	operatorCounts=0;
	        	doNotEraseHistory = false;
	        	return;
	        }
	        
	        if (!(value.equals("="))) {
	        	
	        	
	        	if(operatorCounts == 1 ) {
	        		operator = value;
	        		lblHistory.setText(lblOutput.getText() + value);
	        		numberA = new BigDecimal(lblOutput.getText());
	        		lblOutput.setText(empty);
	        	}
	        	
	        	else if  (operatorCounts >= 2 ){
	        		String history = numberA + operator;
	        		if (numberBhasAdded) {
	        			lblOutput.setText(empty);
	        			if (checkIfBigDecimalHasDecimals(model.calculate(numberA, numberB, operator))){
	        				result = model.calculate(numberA, numberB, operator).toBigInteger().toString();
	        			}
	        			else {
	        				result = model.calculate(numberA, numberB, operator).toString();
	        			}
	        			history += numberB;
		        		operator = value;
		        		lblHistory.setText(result + operator);
		        		numberBhasAdded = false;
		        		numberA= new BigDecimal(result);
		        		System.out.println("Number A:" + numberA);
		        		operatorCounts = 1;
		        		lblOutput.setText(empty);
	        		}
	        		
	        		else {
	        			operator = value;
	        			lblHistory.setText(numberA + operator);
	        			return;
	        		}		
	        	}
	        }
	        
	        else {
	            lblHistory.setText(lblHistory.getText());
	            //Model calculation
	            if (checkIfBigDecimalHasDecimals(model.calculate(numberA, numberB, operator))){
    				result = model.calculate(numberA, numberB, operator).toBigInteger().toString();
    			}
    			else {
    				result = model.calculate(numberA, numberB, operator).toString();
    		}
       		lblHistory.setText(empty);
       		numberA= new BigDecimal(result);
       		System.out.println("numberA :" + numberA);
       		lblOutput.setText(result);
       		operatorCounts = 0;
       		numberBhasAdded = false;
       		doNotEraseHistory = true;
       		start = true;
	       }
	   }
	   
	   @FXML
	   public void onExitClick() {
		   Alert alert = new Alert(AlertType.CONFIRMATION);
		   alert.setTitle("Exit confirmation");
		   alert.setHeaderText("");
		   alert.setContentText("Do you want to exit the program?");

		   Optional<ButtonType> result = alert.showAndWait();
		   if (result.get() == ButtonType.OK){
			   Platform.exit();
		   } 
		   else {return;}		   
	   }
	   
	   public void numFromKeyboard(String keyCode) {
		 if (start) {
			   lblOutput.setText(empty);
			   if (doNotEraseHistory== false) {
				   lblHistory.setText(empty);
			   }
	       start = false;
	     }
		 String test = "" ;
		   if (keyCode == (KeyCode.NUMPAD6).toString())
			   test = "6";
		   String value = test;
		   lblOutput.setText(lblOutput.getText() + value);
		   lblHistory.setText(lblHistory.getText() + value);
		 }
	   
	   public static boolean checkIfBigDecimalHasDecimals(BigDecimal num) {
		   return num.signum() == 0 || num.scale() <= 0 || num.stripTrailingZeros().scale() <= 0; 
	   }
}
	  
	
