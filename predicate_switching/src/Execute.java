

import java.io.FileNotFoundException;
import java.io.IOException;

public class Execute {

	public static void runit3() throws InterruptedException{
		
		
		try {
			Runtime runTime = Runtime.getRuntime();
			String st = "C:\\Users\\Klant\\workspace11\\Sudoku2" + "\\bin\\ experimentt.Sudoku";
			Process process = runTime.exec("java -classpath " + st);//C:\\Users\\Klant\\workspace5\\predicate1\\bin Copyoftest2");
			System.out.println("err");
			Thread.sleep(3000);
			process.destroyForcibly();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		long start = System.currentTimeMillis();
		long end = start + 3*1000; // 60 seconds * 1000 ms/sec
		while (System.currentTimeMillis() < end)
		{
			System.out.println("!!!!!");
			runit3();
			System.out.println(System.currentTimeMillis());
		}
	}
	
}