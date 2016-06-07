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
		String inputData = "90";//in.nextLine();

		// check inputData and make sure it is a valid integer > 0 and < 1000
		String checkInput = "00" + inputData; // add 0's to front of data to make sure its at least 3 digits long
		int checkLength = checkInput.length();
		boolean valid = true; // assume input is valid
		if (checkLength > 2 && checkLength < 6) { // it's an appropriate length
			// check for digits
			if (! Character.isDigit(checkInput.charAt(checkLength-1))) { //check last character
				valid = false;
			} else if (! Character.isDigit(checkInput.charAt(checkLength-2))) { //check second to last
				valid = false;
			} else if (! Character.isDigit(checkInput.charAt(checkLength-3))) { // check third to last
				valid = false;
			}
		} else {
			valid = false;
		}
		
		System.out.println("validity " + valid);
		
		if (valid) { // if it is a valid integer
			int inputValue = Integer.parseInt(checkInput); // get its value
			if (inputValue == 0) { // if the value is 0
			  valid = false;
			} else { // okay ... its a valid integer and it is in the appropriate range
			  height = inputValue;
			}
		}
		// if it's not valid then print error and quit
		if (! valid) {
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
		if (checkLength > 2 && checkLength < 6) { // it's an appropriate length
			// check for digits
			if (! Character.isDigit(checkInput.charAt(checkLength-1))) { // check last character
				valid = false;
			} else if (! Character.isDigit(checkInput.charAt(checkLength-2))) { // check second to last
				valid = false;
			} else if (! Character.isDigit(checkInput.charAt(checkLength-3))) { // check third to last
				valid = false;
			}
		} else {
			valid = false;
		}
		
		if (valid) { // if it is a valid integer
			int inputValue = Integer.parseInt(checkInput); // get its value
			if (inputValue == 0) { // if the value is 0
			  valid = false;
			} else { // okay ... its a valid integer and it is in the appropriate range
			  weight = inputValue;
			}
		}
		// if it's not valid then print error and quit
		if (! valid) {
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
		if (bmi < 15.0) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Very severely underweight");
			System.out.println("*** This is considered a dangerous underweight condition. ***");
		} else if (bmi < 16.0) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Severely underweight");
		} else if (bmi < 18.5) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Underweight");
		} else if (bmi < 25.0) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Normal");
		} else if (bmi < 30.0) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Overweight");
		} else if (bmi < 35.0) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Moderately obese");
		} else if (bmi < 40.0) {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Severely obese");
		} else {
			System.out.println("BMI Classification: (Given a weight, height of " + weight + ", " + height +
			  "). Very severely obese");
			System.out.println("*** This is considered a dangerous overweight condition. ***");
		}
	}
}