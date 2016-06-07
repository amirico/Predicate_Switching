
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class VersionsExecution {


	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		Scanner input = new Scanner ( System.in );
		
		System.out.println("Enter project name and then class name that contains main method: ");
		String s = input.nextLine();
		String m = input.nextLine();
		
		Scanner file = new Scanner(new File("C:\\Users\\Klant\\workspace11\\"+s+"\\testtext.txt"));
	    int count = 0;
	    while(file.hasNextLine()){//		    System.out.println(file.nextLine());
	    	String x = file.nextLine();
//	    	System.out.println(x);
	    	count = count + 1;
		    }
	    System.out.println("count "+count);
	    for (int i = count; i>0; i--){
//	    	System.out.println(i);
	    	runit2(i, s, m);
	    	System.out.println("yes: " +i);
		    
	    }
		
	}


	
	
	public static void runit(String s) throws InterruptedException{
		try {
			
			Runtime runTime = Runtime.getRuntime();
			Process process = runTime.exec("java -classpath " + s);//C:\\Users\\Klant\\workspace5\\predicate1\\bin Copyoftest2");
			process.waitFor();
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void runit2(int i, String st, String m) throws InterruptedException{
		try {
			System.out.println("x: "+i);
			System.out.println("m: "+m);
			System.out.println(st);
			Runtime runTime = Runtime.getRuntime();
			  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace11\\"+st+"\\results.txt", true)))) {
				  out.print(i+", ");
//				  System.out.println("OK");
				}
				catch (IOException e) {
					
				}

			String s = "C:\\Users\\Klant\\workspace11\\"+st + "\\bin\\ experimenT" + "." + m + " ";
			System.out.println(s);
			Process process = runTime.exec("java -classpath " + s + i);//C:\\Users\\Klant\\workspace5\\predicate1\\bin Copyoftest2");
			process.waitFor(500,TimeUnit.MILLISECONDS);
//			process.
			//			process.waitFor();
			System.out.println("ah");
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main4(String[] args) throws InterruptedException, FileNotFoundException {
		Set<String> set = new HashSet<String>();
		
		Scanner input = new Scanner ( System.in );
		
		
		System.out.println("Enter project name and then class name that contains main method: ");
		String s = input.nextLine();
		String m = input.nextLine();
		
		Scanner file = new Scanner(new File("C:\\Users\\Klant\\workspace5\\"+s+"\\testtext.txt"));
	    int count = 0;
//	    System.out.println(file.toString());
	    while(file.hasNextLine()){
//		    System.out.println(file.nextLine());
	    	String x = file.nextLine();
	    	if (set.contains(x)){
	    		set.remove(x);
	    	}
    		set.add(x);	    	
	    }
		    count = count + 1;// file.nextLine().nextInt();
//	    }
	    System.out.println(count);
//	    for (int i = 0; i<count; i++){
	    for (int i = 0; i<set.size(); i++){
		
	    runit2(i, s, m);
	    System.out.println("yes: " +i);
	    }
//	ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(0,2,1));//new ArrayList<Integer>();
//	System.out.print(arrayList);
//	for (int i : arrayList){
//			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)))) {
//				out.print(i + ",");
//			}
//			catch (IOException e) {
//			}
//			String s = "C:\\Users\\Klant\\workspace5\\predicate1\\bin\\ " + " experiment" + i + ".testfld.testfolder.AssignmentTwo";
//System.out.println(s);
//			//			String s = "C:\\Users\\Klant\\workspace5\\predicate1\\bin " + "pack" + i + ".Copyoftest2";
//			runit(s);
//		collect();
//		print();
		
//	}

	}
	
	
	
	public static void main2(String[] args) throws InterruptedException {
		
	ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(0,2,1));//new ArrayList<Integer>();
	System.out.print(arrayList);
	for (int i : arrayList){
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)))) {
				out.print(i + ",");
			}
			catch (IOException e) {
			}
			String s = "C:\\Users\\Klant\\workspace5\\predicate1\\bin\\ " + " experiment" + i + ".testfld.testfolder.AssignmentTwo";
System.out.println(s);
			//			String s = "C:\\Users\\Klant\\workspace5\\predicate1\\bin " + "pack" + i + ".Copyoftest2";
			runit(s);
//		collect();
//		print();
		
	}

		
		
	}
	
	
//	public static void main(String[] args) throws InterruptedException {
//		
//	ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(0,2,1));//new ArrayList<Integer>();
//	System.out.print(arrayList);
//	for (int i : arrayList){
//			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)))) {
//				out.print(i + ",");
//			}
//			catch (IOException e) {
//			}
//			String s = "C:\\Users\\Klant\\workspace5\\predicate1\\bin\\ " + " experiment" + i + ".testfld.testfolder.AssignmentTwo";
//System.out.println(s);
//			//			String s = "C:\\Users\\Klant\\workspace5\\predicate1\\bin " + "pack" + i + ".Copyoftest2";
//			runit(s);
////		collect();
////		print();
//		
//	}
//
//		
//		
//	}

    //	ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(0,2,1));//new ArrayList<Integer>();
//System.out.print(arrayList);
//for (int i : arrayList){
//		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)))) {
//			out.print(i + ",");
//		}
//		catch (IOException e) {
//		}
//		String s = "C:\\Users\\Klant\\workspace5\\predicate1\\bin\\ " + " experiment" + i + ".testfld.testfolder.AssignmentTwo";
//System.out.println(s);
//		//			String s = "C:\\Users\\Klant\\workspace5\\predicate1\\bin " + "pack" + i + ".Copyoftest2";
//		runit(s);
//	collect();
//	print();
	
//}

}

