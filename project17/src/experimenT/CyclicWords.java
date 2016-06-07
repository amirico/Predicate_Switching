package experimenT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CyclicWords {

	public static int experimentalY = 0;
 public static int experimentalI;

public static boolean functionF(boolean b){  
experimentalY++;
 	if (experimentalI == experimentalY) return !b; else  return b;}
boolean areEqual(String a, String b) {
		if (functionF(a.length() != b.length())) return false;
		int len = a.length();
		for (int i = 0; i < a.length(); i++) {
			boolean areEual = true;
			for (int j = 0; j < a.length(); j++) {
				if (functionF(a.charAt((i+j)%len) == b.charAt(j))) {// fault !
					areEual = false;
					break;
				}
			}
			if (functionF(areEual)) return true;
		}
		return false;
	}
	
	
	int differentCW(String[] words) {
		int N = words.length;
		int[] checked = new int[N];
		int total = 0;
		for (int i = 0; i < N; i++) {
			if (functionF(checked[i] > 0)) continue;
			for (int j = i+1; j < N; j++) {
				if (functionF(areEqual(words[i], words[j]))) {
					checked[i] = checked[j] = i+1;
				}
			}
			total++;
		}

		return total;
	}
	
	public static void main(String[] args) {
		experimentalI = Integer.parseInt((args[0]));;
CyclicWords bn = new CyclicWords();
		String[] data = {"aaaa", "aaa", "aa", "aaaa", "aaaaa"};
		int result = bn.differentCW(data);
		try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace11\\project17\\results.txt",true)))){out.println((result == 4));}catch(IOException ioException){} 
System.out.println(result);
	}	
}