package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Thur120913 (ltaylor - lecture)
//   Used in lecture to demonstrate if-then-else-if
//   Problems validating user input
//   And a solution to a common problem

public class BMICalculator {
	
		public static boolean checkValidity(String input){
			int checkLength = input.length();
			boolean valid = true; // assume input is valid
			if (trace((checkLength < 2 && checkLength > 6),16,7,16,41,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) { // it's an appropriate length
				// check for digits
				if (trace((! Character.isDigit(input.charAt(checkLength-1))),18,8,18,56,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) { //check last character
					valid = false;
				} else if (trace((! Character.isDigit(input.charAt(checkLength-2))),20,15,20,63,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) { //check second to last
					valid = false;
				} else if (trace((! Character.isDigit(input.charAt(checkLength-3))),22,15,22,63,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) { // check third to last
					valid = false;
				}
			} else {
				valid = false;
			}
			
			return valid;
		
		}
	
  public static void main(String[] args) {
		// we need to get input ... for now we'll use the Scanner class
		java.util.Scanner in = new java.util.Scanner(System.in);

		// this will hold valid values after we've processed user input.
		int height = 0;
		int weight = 0;
		
		// get height from user (okay .. so I took this from lecture notes ...)
		System.out.print("Patient Height (inches): ");
		String inputData = "90";//in.nextLine();

		// check inputData and make sure it is a valid integer > 0 and < 1000
		String checkInput = "00" + inputData; // add 0's to front of data to make sure its at least 3 digits long
		
		boolean valid = checkValidity(checkInput);
			
		
		if (trace((valid),51,6,51,11,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) { // if it is a valid integer
			int inputValue = Integer.parseInt(checkInput); // get its value
			if (trace((inputValue == 0),53,7,53,22,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) { // if the value is 0
			  valid = false;
			} else { // okay ... its a valid integer and it is in the appropriate range
			  height = inputValue;
			}
		}
		// if it's not valid then print error and quit
		else {
			System.out.println("  !ERROR: height must be a integer value greater than 0 and less than 1000 inches");
			System.exit(0);
		}
		System.out.println(height != 0);
		//**********************************************************************************************
		// now do the same to get the weight from th user
		System.out.print("Patient Weight (pounds): ");
		inputData = "40";//in.nextLine();

		// check inputData and make sure it is a valid integer > 0 and < 1000
		checkInput = "00" + inputData; // add 0's to front of data to make sure its at least 3 digits long
		valid = checkValidity(checkInput);
		
		if (trace((valid),74,6,74,11,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) { // if it is a valid integer
			int inputValue = Integer.parseInt(checkInput); // get its value
			if (trace((inputValue == 0),76,7,76,22,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) { // if the value is 0
			  valid = false;
			} else { // okay ... its a valid integer and it is in the appropriate range
			  weight = inputValue;
			}
		}
		// if it's not valid then print error and quit
		else {
			System.out.println("  ERROR: weight must be a integer value greater than 0 and less than 1000 pounds");
			System.exit(0);
		}
		
		//**********************************************************************************************
		// calculate bmi
		double bmi = ((double)weight / (height * height)) * 703;
		// could have done = (weight * 703) / (height * height)' as well
		
		// used to check value
		// System.out.println("*** " + bmi + " ***");
		if (trace((bmi < 15.0),95,6,95,16,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Very severely underweight");
			System.out.println("*** This is considered a dangerous underweight condition. ***");
		} else if (trace((bmi < 16.0),99,13,99,23,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Severely underweight");
		} else if (trace((bmi < 18.5),102,13,102,23,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Underweight");
		} else if (trace((bmi < 25.0),105,13,105,23,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Normal");
		} else if (trace((bmi < 30.0),108,13,108,23,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Overweight");
		} else if (trace((bmi < 35.0),111,13,111,23,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Moderately obese");
		} else if (trace((bmi < 40.0),114,13,114,23,"/C:/Users/Klant/workspace11/BMICalculator4/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Severely obese");
		} else {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Very severely obese");
			System.out.println("*** This is considered a dangerous overweight condition. ***");
		}
	}

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}