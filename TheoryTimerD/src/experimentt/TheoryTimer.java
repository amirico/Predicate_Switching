package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



class TheoryTimer {
	
	private long duration;
	private long elapsed; 
	private long startTime;
	private boolean isRunning;
	
	protected TheoryTimer (long duration) {
		this.duration = duration;
		reset();
	}
	
	protected long update () {
		if (trace((isRunning() && !isFinished()),16,6,16,34,"/C:/Users/Klant/workspace2/project29/src/TheoryTimer.java")) {
			long currentTime = System.currentTimeMillis();
			this.elapsed += currentTime - this.startTime;
			this.startTime = currentTime;
		}
		
		if (trace((isFinished()),22,6,22,18,"/C:/Users/Klant/workspace2/project29/src/TheoryTimer.java")) {
			this.isRunning = false;
		}
		
		return this.elapsed;
	}
	
	
	protected long timeRemaining () {
		return ((this.duration - update()) < 0 ? 0 : this.duration - update());
	}
	protected void start () {
		if (trace((!isRunning()),34,6,34,18,"/C:/Users/Klant/workspace2/project29/src/TheoryTimer.java")) {
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
		return !(elapsed < duration);//fault
	}
	
	protected static String format (long time) {
		String f = "";
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		int millis = 0;
		
		//hours
		if (trace((time >= 3600000),71,6,71,21,"/C:/Users/Klant/workspace2/project29/src/TheoryTimer.java")) {
			hours = (int) (time / 3600000);
			time -= hours * 3600000;
		}
		f += (hours < 10 ? "0" + String.valueOf(hours) + ":" :
			String.valueOf(hours) + ":");
		
		//minutes
		if (trace((time >= 60000),79,6,79,19,"/C:/Users/Klant/workspace2/project29/src/TheoryTimer.java")) {
			minutes = (int) (time / 60000);
			time -= minutes * 60000;
		}
		f += (minutes < 10 ? "0" + String.valueOf(minutes) + ":" :
			String.valueOf(minutes) + ":");
		
		//seconds
		if (trace((time >= 1000),87,6,87,18,"/C:/Users/Klant/workspace2/project29/src/TheoryTimer.java")) {
			seconds = (int) (time / 1000);
			time -= seconds * 1000;
		}
		f += (seconds < 10 ? "0" + String.valueOf(seconds) + ":" :
			String.valueOf(seconds) + ":");
		
		
		if (trace((time < 100),95,6,95,16,"/C:/Users/Klant/workspace2/project29/src/TheoryTimer.java")) {
			if (trace((time < 10),96,7,96,16,"/C:/Users/Klant/workspace2/project29/src/TheoryTimer.java")) {
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
		
	}
	

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}