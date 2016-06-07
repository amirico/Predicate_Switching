

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Contains all calculator methods and their helpers.
 * @author Frogging101
 *
 */
public class Calculator {
	static int size = 50; //Default array size
	
	/**
	 * Series/Parallel resistor calculator function
	 */
	public static void spResistance(){
		int config = -1; //1 for series, 2 for parallel
		int valueCount = 0; //Number of values in the array
		double[] resistances;
		double resistance = 0; //Final calculated resistance
		SpResult result;
		
		System.out.println("Series/Parallel Resistance");
		System.out.println("==========");
		while(true){ //Loop until broken
			System.out.println("Enter 1 for series or 2 for parallel. ");
			config = EECalc.getSelection();
			if(config != 1 && config != 2){
				System.out.println("Invalid selection. Please try again."); //It can only be 1 or 2
				continue;}
			break;
			}
		result = getArray("resistor");
		resistances = result.values;
		valueCount = result.valueCount;
		
		if(config == 1) //If the user selected series calculation
			for(int i=0;i<valueCount;i++) //For every resistor
				resistance += resistances[i];
		if(config == 2){ //If the user selected parallel calculation
			for(int i=0;i<valueCount;i++){ //For every resistor
				resistance += 1/resistances[i];
			}
			resistance = 1/resistance;
		}
		System.out.println("\nThe total resistance is " + makeSuffix(resistance,"") + " ohms");
		returnPrompt();
	}
	
	/**
	 * Series/Parallel capacitor calculator function
	 */
	public static void spCapacitance(){
		int config = -1; 
		double[] capacitances = new double[size]; //Array of inputted capacitances
		double capacitance = 0; //Total capacitance
		int valueCount = 0;
		SpResult result;
		
		System.out.println("Series/Parallel Capacitance");
		System.out.println("==========");
		
		while(true){ //Loop until broken
			System.out.println("Enter 1 for series or 2 for parallel. ");
			config = EECalc.getSelection();
			if(config != 1 && config != 2){
				System.out.println("Invalid selection. Please try again."); //It can only be 1 or 2
				continue;}
			break;
			}
		result = getArray("capacitor");
		capacitances = result.values;
		valueCount = result.valueCount;
		
		if(config == 1){ //If the user selected series calculation
			for(int i=0;i<valueCount;i++) //For every capacitor
				capacitance += 1/capacitances[i];
		capacitance = 1/capacitance;}
		if(config == 2) //If the user selected parallel calculation
			for(int i=0;i<valueCount;i++){ //For every capacitor
				capacitance += capacitances[i];
		}
		System.out.println("\nThe total capacitance is "+makeSuffix(capacitance,"F"));
		returnPrompt();
	}
	
	/**
	 * Series/Parallel inductor calculator function
	 */
	public static void spInductance(){
		int config = -1;
		double[] inductances = new double[size]; //Array of inputted inductances
		double inductance = 0; //Total inductance
		int valueCount = 0;
		SpResult result;
		
		System.out.println("Series/Parallel Inductance");
		System.out.println("==========");
		
		while(true){ //Loop until broken
			System.out.println("Enter 1 for series or 2 for parallel. ");
			config = EECalc.getSelection();
			if(config != 1 && config != 2){
				System.out.println("Invalid selection. Please try again."); //It can only be 1 or 2
				continue;}
			break;
			}
		result = getArray("inductor");
		inductances = result.values;
		valueCount = result.valueCount;
		
		if(config == 1) //If the user selected series calculation
			for(int i=0;i<valueCount;i++) //For every inductor
				inductance += inductances[i];
		if(config == 2){ //If the user selected parallel calculation
			for(int i=0;i<valueCount;i++){ //For every inductor
				inductance += inductances[i];
			inductance = 1/inductance;
			}
		}
		System.out.println("\nThe total inductance is "+makeSuffix(inductance,"H"));
		returnPrompt();
	}
	
	/**
	 * Voltage divider calculator function
	 */
	public static void voltageDivider()
	{
		System.out.println("Voltage Divider");
		System.out.println("==========");
		
		double R1 = 0; //R1, R2 and V1 are input values
		double R2 = 0;
		double V1 = 0;
		double vout = 0; //This is the output value
		boolean valid = true;
		System.out.println("Vin\n"+
				 " |\n"+
				 " |\n"+
				 " -\n"+
				"| |\n"+ 
				"| | R1\n"+
				"| |\n"+
				 " -\n"+
				 " |\n"+
				 " ------- Vout\n"+
				 " |\n"+
				 " -\n"+
				"| |\n"+
				"| | R2\n"+
				"| |\n"+
				 " -\n"+
				 " |\n"+
				 " |\n"+
				 "0V\n");
		
		do{
			valid = true;
			try{
				System.out.print("Enter value of V1 (e.g. 9V): ");
				V1 = handleInput();
				System.out.print("Enter value of R1 (e.g. 10k): ");
				R1 = handleInput();
				System.out.print("Enter value of R2 (e.g. 1k): ");
				R2 = handleInput();
			}catch(NumberFormatException e)
			{
				valid = false;
			}
		}while(valid == false);
		vout = (R2/(R1+R2))*V1;
		System.out.println("\nOutput voltage (Vout) is "+makeSuffix(vout,"V"));
		returnPrompt();
	}
	
	/**
	 * LC Filter calculator function
	 */
	public static void resonantCircuit()
	{		
		double inductance = 0; //Input value
		double capacitance = 0; //Input value
		double frequency = 0; //Output value
		double reactance = 0; //Output value
		boolean valid = true;
		
		System.out.println("LC Circuit");
		System.out.println("==========");
		
		do{
			valid = true;
			try{
				System.out.print("Enter the value of L (Inductor) (e.g. 10uH): ");
				inductance = handleInput();
				System.out.print("Enter the value of C (Capacitor) (e.g. 10nF): ");
				capacitance = handleInput();
			}catch(NumberFormatException e){
				valid = false;
			}
		}while(valid == false);
		
		frequency = 1/(2*Math.PI*Math.sqrt(inductance*capacitance));
		reactance = 2*Math.PI*frequency*inductance; //Reactance is the same for both components; Inductance was an arbitrary choice
		
		System.out.println("\nThe resonant frequency of this circuit is "+makeSuffix(frequency,"Hz"));
		System.out.println("The reactance of the components is "+makeSuffix(reactance,"")+" ohms.");
		returnPrompt();
	}
	
	/**
	 * RC Filter calculator function
	 */
	public static void rcFilter()
	{
		double resistance = 0; //Input value
		double capacitance = 0; //Input value
		double cornerFrequency = 0; //Output value
		boolean valid = true;
		
		do{
			valid = true;
			try{
				System.out.print("Enter the value of R (Resistor) (e.g. 1k): ");
				resistance = handleInput();
				System.out.print("Enter the value of C (Capacitor) (e.g. 10nF): ");
				capacitance = handleInput();
				}catch(NumberFormatException e){
					valid = false;
				}
		}while(valid == false);
		
		cornerFrequency = 1/(2*Math.PI*resistance*capacitance);
		System.out.println("The corner frequency of this RC filter is: "+makeSuffix(cornerFrequency,"Hz"));
		returnPrompt();
	}
	
	/**
	 * Battery life calculator function
	 */
	public static void batteryLife()
	{
		int capacity = 0;
		int current = 0;
		boolean valid = true;
		
		System.out.println("Battery Life");
		System.out.println("==========");
		
		do{
			valid = true;
			try{
				System.out.print("Enter the battery capacity in mAh (e.g. 1200mAh): ");
				//The suffix will be ignored for these inputs below
				capacity = (int) handleInput(0); //Casting to int because there's only one possible unit to use for this formula
				System.out.print("Enter the current where the battery connects to the circuit in mA (e.g. 100mA): ");
				current = (int) handleInput(0);
				if(current == 0)
				{
					System.out.println("The current cannot be 0. Please try again.\n");
					valid = false;
				}
			}catch(NumberFormatException e){
				valid = false;
			}
		}while(valid == false);
		
		System.out.println("\nThe battery will last for "+capacity/current+" hour(s)." );
		returnPrompt();
	}

	/**
	 * Ohm's Law calculator function
	 * @param variable Equal to 1 for voltage, 2 for current, 3 for resistance
	 */
	public static void ohmsLaw(int variable)
	{
		double voltage = 0; //voltage, current and resistance are potential inputs
		double current = 0;
		double resistance = 0;
		double result = 0; //This will be set equal to one of the above depending on the user's menu selection
		String variableStr = ""; //This will be used in displaying the result
		String unit = ""; //This will also be used in displaying the result
		String unitR = ""; //If the selection was resistance, this will be defined and displayed at the end
		boolean valid = true;
		
		// The code in the if statements below is mostly repetitive
		// but it's not enough to justify creating a more complex but
		// more modular function to handle it.
		
		if(variable == 1){
			do{
				valid = true;
				try{
					System.out.print("Enter the current (e.g. 500mA): ");
					current = handleInput();
					System.out.print("Enter the resistance (e.g. 1k): ");
					resistance = handleInput();
				}catch(NumberFormatException e){
					valid = false;
				}
			}while(valid == false);
			voltage = current*resistance; //Calculate the voltage
			result = voltage;
			variableStr = "voltage";
			unit = "V";
		}
		
		if(variable == 2){
			do{
				valid = true;
				try{
					System.out.print("Enter the voltage (e.g. 9V): ");
					voltage = handleInput();
					System.out.print("Enter the resistance (e.g. 1k): ");
					resistance = handleInput();
				}catch(NumberFormatException e){
					valid = false;
				}
			}while(valid == false);
			current = voltage/resistance; //Calculate the current
			result = current;
			variableStr = "current";
			unit = "A";
		}
		
		if(variable == 3){
			do{
				valid = true;
				try{
					System.out.print("Enter the voltage (e.g. 9V): ");
					voltage = handleInput();
					System.out.print("Enter the current (e.g. 500mA): ");
					current = handleInput();
				}catch(NumberFormatException e){
					valid = false;
				}
			}while(valid == false);
			resistance = voltage/current; //Calculate the resistance 
			result = resistance;
			variableStr = "resistance";
			unitR = " ohms";
		}
		System.out.println("The "+variableStr+" is "+makeSuffix(result,unit) + unitR);
		returnPrompt();
	}
	
	/**
	 * Frequency/Wavelength conversion calculator function
	 * @param variable Equal to 1 for frequency input, 2 for wavelength input
	 */
	public static void freqWave(int variable)
	{
		double frequency = 0;
		double wavelength = 0;
		int lightspeed = 299792458;
		boolean valid = true;
		
		if(variable == 1)
		{
			do{
				System.out.print("Enter the frequency (e.g. 10MHz): ");
				try{
					frequency = handleInput();}
				catch(NumberFormatException e){
					valid = false;
				}
			}while(valid == false);
			
			System.out.println("The wavelength is "+makeSuffix(lightspeed/frequency,"m"));
			returnPrompt();
		}
		
		if(variable == 2)
		{
			do{
				System.out.print("Enter the wavelength (e.g. 10m): ");
				try{
					wavelength = handleInput();}
				catch(NumberFormatException e){
					valid = false;
				}
			}while(valid == false);
			
			System.out.println("The frequency is "+makeSuffix(lightspeed/wavelength,"Hz"));
			returnPrompt();
		}
	}	
	
	/**
	 * Gets multiple values as input from the user, and places them in an array.
	 * @param component The component name, as a string. This is used to ask the user to input a value.
	 * @return
	 */
	private static SpResult getArray(String component)
	{
		double[] values = new double[size]; //Array to store resistances
		int valueCount = 0;
		boolean done = false; //This is set to true when the user is finished inputting values;
		SpResult result = new SpResult();
		
		for(int i=0;done==false;i++){
			if(i==0) //For user-friendliness, say "first resistor" for the first one only
				System.out.print("Enter the value the first "+component+", or enter 0 to end: ");
			else
				System.out.print("Enter the value of another "+component+", or enter 0 to end: ");
			double current = 0;
			try{
				current = handleInput();}
			catch(NumberFormatException e)
			{
				i--; //This is to ensure that the index doesn't get moved up when this happens
				continue;
			}
			if(current == 0){
				done = true; //If the user enters 0, they are done inputting values
				valueCount = i;} //Set value count to current index
			else
				values[i] = current; //Set current array index to inputted value
		}
		result.valueCount = valueCount;
		result.values = values;
		return result;
	}
	
	/**
	 * Gets input from the user and parses it based on a suffix at the end of it.
	 * "50mV" will become 0.05, for example.
	 * @param parsePrefix Optional, tells the method to parse the prefix or just throw it away
	 * @throws NumberFormatException
	 */
	private static double handleInput(int parseSuffix) throws NumberFormatException
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		boolean valid = true;
		boolean suffixExists; //If there is a suffix, this will be true
		String suffix = ""; //Unit suffix such as pF, nH, k, etc.
		String inputStr = ""; //Input string
		double inputNum = 0;
		do{
			valid = true;
			suffixExists = false;
			try {
				inputStr = input.readLine();
			} catch (IOException e) { //This seriously will never happen
				System.out.println("An IOException has occurred. This should /NEVER/ happen!"); //Scream
				e.printStackTrace(); //Burn
				System.exit(1); //Die
			}
			
			if(inputStr.equals(""))
			{
				System.out.println("Your input cannot be empty. Please try again.\n");
				throw new NumberFormatException();
			}
			
			char c; //Character to check
			for(int i=0;i<inputStr.length();i++){
				c = inputStr.charAt(i);
				if(!Character.isDigit(c) && c != '.'){ //Checks if character is neither a number or a decimal point
					suffix = inputStr.substring(i, inputStr.length()); //Get suffix, which should 
																		//be from the current point to the end of the string
					suffixExists = true;
					inputNum = Double.parseDouble(inputStr.substring(0,i));
				}
				if(suffixExists)
					break;
			}
			if(!suffixExists)
				return Double.parseDouble(inputStr);
			if(parseSuffix == 0)
			{
				return inputNum; //Just return the number and ignore the suffix
			}
			if(suffix.equals("MF") || suffix.equals("MH") || suffix.equals("M") || suffix.equals("MV") || suffix.equals("MA") ||
					suffix.equals("MHz"))
			{
				inputNum = inputNum*Math.pow(10,6);
				return inputNum;
			}
			suffix = suffix.toLowerCase(); //Converts suffix to lowercase to make the input case-insensitive
			
			//The if-else block below uses the suffix to determine the exponentiation of the result
			if(suffix.equals("pf") || suffix.equals("ph") || suffix.equals("p") || suffix.equals("pv") || suffix.equals("pa") ||
					suffix.equals("pm"))
				inputNum = inputNum*Math.pow(10, -12);
			else if(suffix.equals("nf") || suffix.equals("nh") || suffix.equals("n") || suffix.equals("nv") || suffix.equals("na") ||
					suffix.equals("nm"))
				inputNum = inputNum*Math.pow(10, -9);
			else if(suffix.equals("uf") || suffix.equals("uh") || suffix.equals("u") || suffix.equals("uv") || suffix.equals("ua") ||
					suffix.equals("um"))
				inputNum = inputNum*Math.pow(10, -6);
			else if(suffix.equals("mf") || suffix.equals("mh") || /*suffix.equals("m") ||*/ suffix.equals("mv") || suffix.equals("ma") ||
					suffix.equals("mm"))
				inputNum = inputNum*Math.pow(10,-3);
			else if(suffix.equals("f") || suffix.equals("h") || suffix.equals("") || suffix.equals("v") || suffix.equals("a") ||
					suffix.equals("hz") || suffix.equals("m"))
				return inputNum;
			else if(suffix.equals("kf") || suffix.equals("kh") || suffix.equals("k") || suffix.equals("kv") || suffix.equals("ka") ||
					suffix.equals("khz") || suffix.equals("km"))
				inputNum = inputNum*Math.pow(10, 3);
			else{
				System.out.print("Invalid suffix. Please try again: ");
				valid = false;}
			
		}while(valid == false);
		return inputNum;
	}
	
	/**
	 * Optional-parameter version of handleInput
	 * @throws NumberFormatException
	 */
	private static double handleInput() throws NumberFormatException{
		return handleInput(1);
	}
	
	/**
	 * Takes an input and returns a user-friendly string displaying the number along with
	 * a suffix if necessary.
	 * @param input
	 * @param unit A unit string that will be appended to the output
	 * @return A string such as "1.59KHz"
	 */
	private static String makeSuffix(double input, String unit)
	{
		/* The program will crash if it attempts to parse an infinite or broken value.
		 * To react to this, the program will gracefully handle the weird result and inform the user
		 * that their input was probably faulty.
		 */
		if(Double.isInfinite(input))
			return "Infinity. This can happen if you entered zero (or other impossible values).";
		else if(Double.isNaN(input))
			return "NaN. This can happen if you entered zero (or other impossible values).";
		
		DecimalFormat df = new DecimalFormat("0.###E0"); //Scientific notation DecimalFormat
		DecimalFormat df2 = new DecimalFormat("#.##"); //Final output format
		String formattedInput = df.format(input);
		int scientificNotation = 0; //This will be the exponentiation; 1.234E4 would make this 4
		double number = 0; //This will be the number before the scientific notation
							//For example, 1.234E4 would make this 1.234
		int remainder = 0; //This is part of the formula to make a suffix
		String suffix = "";
		
		char c;
		for(int i=0;i<formattedInput.length();i++){
			c = formattedInput.charAt(i);
			if(!Character.isDigit(c) && c != '.'){ //Checks if character is neither a number or a decimal point
				scientificNotation = Integer.parseInt(formattedInput.substring(i+1, formattedInput.length())); //Get exponent, which should 
																	//be from after the E to the end of the string
				
				number = Double.parseDouble(formattedInput.substring(0,i));
				break;
			}
		}
		
		if(scientificNotation<0) //Different formulae are needed for + and -, or it won't work correctly.
			remainder = Math.abs(scientificNotation % 3 + 3);
		else
			remainder = scientificNotation % 3;
		number = number*Math.pow(10,remainder);
		
		scientificNotation -= remainder;
		
		if (scientificNotation==9)
			suffix = "G";
		else if(scientificNotation==6)
			suffix = "M";
		else if(scientificNotation==3)
			suffix = "k";
		else if(scientificNotation==-3)
			suffix = "m";
		else if(scientificNotation==-6)
			//suffix = "\u03BC";
			suffix = "u"; //"mu" unicode does not display on all systems
		else if(scientificNotation==-9)
			suffix = "n";
		else if(scientificNotation==-12)
			suffix = "p";
		
		return df2.format(number)+suffix+unit;
	}
	
	/**
	 * Simply prompts the user to press enter to return to the main menu.
	 */
	private static void returnPrompt(){
		System.out.println("Press Enter to return to the main menu.");
		Scanner keyIn = new Scanner(System.in);
		keyIn.nextLine();
		EECalc.main(null);
	}
}