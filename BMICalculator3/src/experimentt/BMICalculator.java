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
  public static void main(String[] args) {
		// we need to get input ... for now we'll use the Scanner class
		java.util.Scanner in = new java.util.Scanner(System.in);

		// this will hold valid values after we've processed user input.
		int height = 0;
		int weight = 0;
		
		// get height from user (okay .. so I took this from lecture notes ...)
		System.out.print("Patient Height (inches): ");
		String inputData = "75";//in.nextLine();

		// check inputData and make sure it is a valid integer > 0 and < 1000
		String checkInput = "00" + inputData; // add 0's to front of data to make sure its at least 3 digits long
		int checkLength = checkInput.length();
		boolean valid = true; // assume input is valid
		if (trace((checkLength < 2 && checkLength > 6),28,6,28,40,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // it's an appropriate length
			// check for digits
			if (trace((! Character.isDigit(checkInput.charAt(checkLength-1))),30,7,30,60,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { //check last character
				valid = false;
			} else if (trace((! Character.isDigit(checkInput.charAt(checkLength-2))),32,14,32,67,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { //check second to last
				valid = false;
			} else if (trace((! Character.isDigit(checkInput.charAt(checkLength-3))),34,14,34,67,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // check third to last
				valid = false;
			}
		} else {
			valid = false;
		}
		
		System.out.println("validity " + valid);
		
		if (trace((valid),43,6,43,11,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // if it is a valid integer
			int inputValue = Integer.parseInt(checkInput); // get its value
			if (trace((inputValue == 0),45,7,45,22,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // if the value is 0
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
		checkLength = checkInput.length();
		valid = true; // assume input is valid
		if (trace((checkLength > 2 && checkLength < 6),66,6,66,40,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // it's an appropriate length
			// check for digits
			if (trace((! Character.isDigit(checkInput.charAt(checkLength-1))),68,7,68,60,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // check last character
				valid = false;
			} else if (trace((! Character.isDigit(checkInput.charAt(checkLength-2))),70,14,70,67,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // check second to last
				valid = false;
			} else if (trace((! Character.isDigit(checkInput.charAt(checkLength-3))),72,14,72,67,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // check third to last
				valid = false;
			}
		} else {
			valid = false;
		}
		
		if (trace((valid),79,6,79,11,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // if it is a valid integer
			int inputValue = Integer.parseInt(checkInput); // get its value
			if (trace((inputValue == 0),81,7,81,22,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) { // if the value is 0
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
		System.out.println(bmi);
		// could have done = (weight * 703) / (height * height)' as well
		
		// used to check value
		// System.out.println("*** " + bmi + " ***");
		if (trace((bmi < 15.0),101,6,101,16,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Very severely underweight");
			System.out.println("*** This is considered a dangerous underweight condition. ***");
		} else if (trace((bmi < 16.0),105,13,105,23,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Severely underweight");
		} else if (trace((bmi < 18.5),108,13,108,23,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Underweight");
		} else if (trace((bmi < 25.0),111,13,111,23,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Normal");
		} else if (trace((bmi < 30.0),114,13,114,23,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Overweight");
		} else if (trace((bmi < 35.0),117,13,117,23,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Moderately obese");
		} else if (trace((bmi < 40.0),120,13,120,23,"/C:/Users/Klant/workspace11/BMICalculator3/src/BMICalculator.java")) {
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