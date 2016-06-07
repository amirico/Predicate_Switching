

/**
 *
 * @author Aidan Lloyd-Tucker
 * @assignment 3B
 * @assnDesc BMI Calculator
 */
import java.util.Scanner;

public class Foothill
{
   public static void main(String[] args)
   {
      
      // Declare the ints weight and height. Declare the scanner.
      int weight, height;
      Scanner inputScanner = new Scanner(System.in);
      
      // Ask for weight and on the same line enter input. Check if input is valid (above 0), if not exit the program with an error
      System.out.print("Enter Your Weight (in pounds): ");
      weight = inputScanner.nextInt();
      if (weight <= 0)
      {
         System.out.println("Below zero weight");
         System.exit(-1);
      }
      
      // Ask for height and on the same line enter input. Check if input is valid (above 0), if not exit the program with an error
      System.out.print("Enter Your Height (in inches): ");
      height = inputScanner.nextInt();
      if (height <= 0)
      {
         System.out.println("Below zero height");
         System.exit(-1);
      }
      
      // Call method that will calculate and print the BMI
      findBMI(weight, height);
   }

   public static void findBMI(int weight, int height) // Declare the inputed values as ints named weight and height
   {
      // Declare the integers feet and inches, and declare the double BMI
      int feet, inches;
      double BMI;
      
      // Feet is height divided by 12, with no remainder. Inches is that remainder. BMI is the formula 703w/h^2
      feet = height / 12;
      inches = height % 12;
      BMI = (703.0 * weight) / (height * height);
      
      // Print start of sentence
      System.out.println("---");
      System.out.print("Your weight is " + weight + " and your height is ");
      
      // If feet is 1, call it a foot
      if (feet == 1)
      {
         System.out.print(feet + " foot, " + inches + " inches. So your BMI is ");
      }
      else
      {
         System.out.print(feet + " feet, " + inches + " inches. So your BMI is ");
      }
      
      // Find where the BMI is and tell user what their weight class is.
      if ((BMI < 18.5) && (BMI > 0.0))
      {
         System.out.println(BMI + ", which is underweight.");
      }
      else if ((BMI >= 18.5) && (BMI <= 25.0))
      {
         System.out.println(BMI + ", which is optimal.");
      }
      else if (BMI > 25.0) //If BMI is over 25
      {
         System.out.println(BMI + ", which is overweight.");
      }
      // An emergency catch
      else
      {
         System.out.println("impossible to determine. Go to a doctor ;)");
      }

   }
}
/* Run 1
run:
Enter Your Weight (in pounds): 120
Enter Your Height (in inches): 72
---
Your weight is 120 and your height is 6 feet, 0 inches. So your BMI is 16.27314814814815, which is underweight.
BUILD SUCCESSFUL (total time: 11 seconds)
*/

/* Run 2
run:
Enter Your Weight (in pounds): 1000
Enter Your Height (in inches): 15
---
Your weight is 1000 and your height is 1 foot, 3 inches. So your BMI is 3124.4444444444443, which is overweight.
BUILD SUCCESSFUL (total time: 11 seconds)
*/

/* Run 3
run:
Enter Your Weight (in pounds): 1
Enter Your Height (in inches): 0
Below zero height
Java Result: 255
BUILD SUCCESSFUL (total time: 6 seconds)
*/