package experimenT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



class TheoryTimer {
	
	public static int experimentalY = 0;
 public static int experimentalI;

public static boolean functionF(boolean b){  
experimentalY++;
 	if (experimentalI == experimentalY) return !b; else  return b;}
private long duration;
	private long elapsed; 
	private long startTime;
	private boolean isRunning;
	
	protected TheoryTimer (long duration) {
		this.duration = duration;
		reset();
	}
	
	protected long update () {
		if (functionF(isRunning() && !isFinished())) {
			long currentTime = System.currentTimeMillis();
			this.elapsed += currentTime - this.startTime;
			this.startTime = currentTime;
		}
		
		if (functionF(isFinished())) {
			this.isRunning = false;
		}
		
		return this.elapsed;
	}
	
	
	protected long timeRemaining () {
		return ((this.duration - update()) < 0 ? 0 : this.duration - update());
	}
	protected void start () {
		if (functionF(!isRunning())) {
			this.startTime = System.currentTimeMillis();
			this.isRunning = true;
		}
	}
	
	protected void pause () {
		this.isRunning = false;
		update();
	}
	
	protected void reset () {
		reset(this.duration);
	}
	
	protected void reset (long duration) {
		this.duration = duration;
		this.elapsed = 0;
		pause();
	}

	protected boolean isRunning () {
		return this.isRunning;
	}
	
	protected boolean isFinished () {
		return !(elapsed > duration);//fault >
	}
	
	protected static String format (long time) {
		String f = "";
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		int millis = 0;
		
		//hours
		if (functionF(time >= 3600000)) {
			hours = (int) (time / 3600000);
			time -= hours * 3600000;
		}
		f += (hours < 10 ? "0" + String.valueOf(hours) + ":" :
			String.valueOf(hours) + ":");
		
		//minutes
		if (functionF(time >= 60000)) {
			minutes = (int) (time / 60000);
			time -= minutes * 60000;
		}
		f += (minutes < 10 ? "0" + String.valueOf(minutes) + ":" :
			String.valueOf(minutes) + ":");
		
		//seconds
		if (functionF(time >= 1000)) {
			seconds = (int) (time / 1000);
			time -= seconds * 1000;
		}
		f += (seconds < 10 ? "0" + String.valueOf(seconds) + ":" :
			String.valueOf(seconds) + ":");
		
		
		if (functionF(time < 100)) {
			if (time < 10) {
				f += "00" + String.valueOf(time);
			} else {
				f += "0" + String.valueOf(time);
			}
		} else {
			f += String.valueOf(time);
		}
		
		
		return f;
	}
	
	public static void main (String[] args) throws InterruptedException {
		experimentalI = Integer.parseInt((args[0]));;
TheoryTimer t = new TheoryTimer(500);
		
		t.start();
		Thread.sleep(100);
		
		System.out.println(t.update());
		
		System.out.println(t.isRunning);
		System.out.println(t.isFinished());
		t.pause();

		System.out.println(t.update());
		System.out.println(t.isRunning());
		System.out.println(t.isFinished());
		System.out.println(t.timeRemaining());
		
		System.out.println(format(111));
		try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace11\\project16\\results.txt",true)))){out.println((t.timeRemaining() == 400 || t.timeRemaining() == 399));}catch(IOException ioException){} 
		
	}
	
}