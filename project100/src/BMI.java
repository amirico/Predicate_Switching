// CSE 142 Lecture 8
// Scanner, if/else

import java.util.*; // For Scanner

// Calculates two people's BMI (body mass index), reports their weight class
//  according to the CDC, and prints the difference in BMI.
public class BMI {
	public static void main(String[] args) {
		intro();
		
		Scanner console = new Scanner(System.in);
		double bmi1 = getData(console);
		double bmi2 = getData(console);
		
		reportClass(bmi1, 1);
		reportClass(bmi2, 2);
		
		System.out.printf("Difference = %.2f\n", Math.abs(bmi1 - bmi2));
	}
	
	// Prints the intro message.
	public static void intro() {
		System.out.println("This program reads data for two people and");
		System.out.println("computes their body mass index (BMI).");
		System.out.println();
	}
	
	// Prints the BMI for the given person and prints their
	// weight class as determined by the CDC.
	public static void reportClass(double bmi, int person) {
		System.out.printf("Person %d BMI = %.2f\n", person, bmi);
		if (bmi < 18.5) {
			System.out.println("underweight");
		} else if (bmi < 25) {
			System.out.println("normal");
		} else if (bmi < 30) {
			System.out.println("overweight");
		} else {
			System.out.println("obese");
		}
	}
	
	// Gets the user's height and weight and returns their BMI.
	public static double getData(Scanner console) {
		System.out.println("Enter next person's information:");
		System.out.print("height (in inches)? ");
		double height = console.nextDouble();
		System.out.print("weight (in pounds)? ");
		double weight = console.nextDouble();
		System.out.println();
		return calculateBMI(height, weight);
	}
	
	// Calculates a BMI from height and weight.
	public static double calculateBMI(double height, double weight) {
		return weight * 703 / Math.pow(height, 2);
	}
}