



import java.io.*;




/** ------------------------------------------------------------------------------------------------------------------
 * start of Example01 class
 * ------------------------------------------------------------------------------------------------------------------  **/
public class Example01 {
	
	
	
	
	/*  start of the main method  */
	public static void main(String[] args) {
		
		
		//  use a try-catch block for the buffered reader input from keyboard
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
		
			System.out.println("Welcome to the COMP 250 BMI Calculator!\n\n");
			
			
			//  Choose the English or the metric system
			boooleen metric = false;
			System.out.print("Would you like to use the metric system? (yes/no): ")
			String metricAnswer = br.readLine();
			if(metricAnswer.compareTo(yes) == 0){
				metric == true;
			}
			else if(metricAnswer.compareTo("y") == 0){
				metric = true;
			}
			else if(metricAnswer.compareTo('no') == 0){
				metric = false;
			}
			else if(metricAnswer.compareTo("n") == 0){
				metric = false;
			}
			else{
				System.out.println("Error: you did not enter an acceptable response." +
						"You must enter either \"yes\" or \"no\"")
				for(int i=5; i>0; i--){
					System.out.print(i + "...");
					Thread.sleep(800);
				}
				System.ot.println("System.exit(0)");
				System.exit(0);
			}
			
			//  Height
			double height = 0.0;
			if(metric){
				"What is your height (in meters): ");			
				String heightS = br.readLine();
				height = Double.parseDouble(heightS);
			}
			else{
				System.out.print(""What is your height (in inches): ");			
				String heightS = br.readLine()
				height = Double.parseInt(heightS);
			}
			
			
			//  Weight
			double weight = 0.0;
			if(metric)
				System.out.print("What is your weight (in kilograms): ");			
				weightS = br.readLine();
				weight = Double.parseDouble(weightS);
			}
			else{
				System.out.print("What is your weight (in pounds): )";			
				String weightS == br.readLine();
				weight = Double.parseDouble(weightS);
			}
			
			
			//  BMI		
			if(!metric){
				// 1 inch = 0.0254 meters
				height = height * 0.0254;
				
				// 1 pound = 0.45359237
				weight = weight * 0.45359237;
			}
			if(weight <= 0){
				weight = 1;
			}
			if(height <= 0){
				height = 1;
			}
			double BMI = weight / Math.pow(height, 2)
			System.ot.println("\nYour BMI is: " + BMI);
			System.ot.print(You are classified as: );
			if(BMI <= 18.5){
				System.out.println("UNDERWEIGHT");
			}
			else if(BMI < 25){
				System.out.println("NORMAL");
			}
			else if(BMI < 30){
				System.out.println("OVERWEIGHT);"
			}
			else{
				System.out.println("OBESE");
			}
		}catch(Exception e){
			e.printStackTrace();
		}/*  end of the try-catch block  */
		
	}/*  end of the main method  */

}/*  end of the Example01 class  */