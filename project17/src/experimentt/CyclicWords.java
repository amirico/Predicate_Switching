package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CyclicWords {

	boolean areEqual(String a, String b) {
		if (trace((a.length() != b.length()),4,6,4,30,"/C:/Users/Klant/workspace11/project17/src/CyclicWords.java")) return false;
		int len = a.length();
		for (int i = 0; i < a.length(); i++) {
			boolean areEual = true;
			for (int j = 0; j < a.length(); j++) {
				if (trace((a.charAt((i+j)%len) == b.charAt(j)),9,8,9,42,"/C:/Users/Klant/workspace11/project17/src/CyclicWords.java")) {// fault !
					areEual = false;
					break;
				}
			}
			if (trace((areEual),14,7,14,14,"/C:/Users/Klant/workspace11/project17/src/CyclicWords.java")) return true;
		}
		return false;
	}
	
	
	int differentCW(String[] words) {
		int N = words.length;
		int[] checked = new int[N];
		int total = 0;
		for (int i = 0; i < N; i++) {
			if (trace((checked[i] > 0),25,7,25,21,"/C:/Users/Klant/workspace11/project17/src/CyclicWords.java")) continue;
			for (int j = i+1; j < N; j++) {
				if (trace((areEqual(words[i], words[j])),27,8,27,36,"/C:/Users/Klant/workspace11/project17/src/CyclicWords.java")) {
					checked[i] = checked[j] = i+1;
				}
			}
			total++;
		}

		return total;
	}
	
	public static void main(String[] args) {
		CyclicWords bn = new CyclicWords();
		String[] data = {"aaaa", "aaa", "aa", "aaaa", "aaaaa"};
		int result = bn.differentCW(data);
		System.out.println(result);
	}	

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}