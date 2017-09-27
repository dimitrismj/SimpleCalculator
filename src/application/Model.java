package application;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Model {
	
	private static final String addition = "+";
	private static final String abstraction = "-";
	private static final String multiplication = "*";
	private static final String division = "/";
	private static final String percentage = "%";
	private static final BigDecimal hundred = new BigDecimal(100);
	private static BigDecimal temp;
	
	 public BigDecimal calculate(BigDecimal number1, BigDecimal number2, String operator) {
	        switch (operator) {
	            case addition:
	                return number1.add(number2);
	            case abstraction:
	                return number1.subtract(number2);
	            case multiplication:
	                return number1.multiply(number2);
	            case division:
	                if (number2 == BigDecimal.ZERO) {
	                    return BigDecimal.ZERO;
	                }
	                else {
	                	return number1.divide(number2, 12, RoundingMode.HALF_UP);
	                }
	            case percentage:
	            	temp = number1.multiply(number2);
	            	return temp.divide(hundred);
	            default: return BigDecimal.ZERO;
	        }
			
	 }
}
