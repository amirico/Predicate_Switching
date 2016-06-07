package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ThreeCardPoker {
	public static final String[] SUITS = {"Spades", "Hearts",
					      "Diamonds", "Clubs"};
	public static final String[] RANKS = {"2", "3", "4", "5", "6", "7",
			    "8", "9", "10", "Jack", "Queen", "King", "Ace"};	
	public static final int STRAIGHT_FLUSH = 40;	
	public static final int THREE_OF_A_KIND = 30;
	public static final int STRAIGHT = 6;
	public static final int FLUSH = 2;	
	public static final int PAIR = 1;
	public static final int NOTHING = 0;

	public static void main(String[] args) {
		int[] deck = new int[52];
		int[] hand = {22,32,51};//		int[] hand = new int[3];				
		for( int i = 0; i < deck.length; i++ )
			deck[i] = i;
		shuffle( deck );
//		for( int i = 0; i < hand.length; i++ )
//			hand[i] = deck[i];		
		int score = score(hand);
		System.out.println("Hand: ");
		print(hand);
		if((trace( score == 0 ,24,6,24,16,"/C:/Users/Klant/workspace2/project66/src/ThreeCardPoker.java")))
			System.out.println("You win nothing.");
		else
			System.out.println("You win " + score + " times your bet.");
	}

	public static void shuffle(int[] deck) {
		int index, temp;
		for( int i = 0; i < deck.length; i++ ) {
			index = i + (int)((deck.length - i)*Math.random());
			temp = deck[index];
			deck[index] = deck[i];
			deck[i] = temp;
		}		
	}

	public static void print(int[] hand) {
		for( int i = 0; i < hand.length; i++ )
			System.out.println(RANKS[getRank(hand[i])] + " of "
			+ SUITS[getSuit(hand[i])]);
	}

	public static int getRank(int value) { return value / 13; }
	public static int getSuit(int value) { return value / 13; }
	public static int score(int[] hand) {	
		sortByRank( hand );	
		if((trace( hasStraight( hand ) && hasFlush( hand ) ,50,6,50,45,"/C:/Users/Klant/workspace2/project66/src/ThreeCardPoker.java")))
			return STRAIGHT_FLUSH;
		if((trace( hasThree( hand ) ,52,6,52,22,"/C:/Users/Klant/workspace2/project66/src/ThreeCardPoker.java")))
			return THREE_OF_A_KIND;
		if((trace( hasStraight( hand ) ,54,6,54,25,"/C:/Users/Klant/workspace2/project66/src/ThreeCardPoker.java")))
			return STRAIGHT;
		if((trace( hasFlush( hand ) ,56,6,56,22,"/C:/Users/Klant/workspace2/project66/src/ThreeCardPoker.java")))
			return FLUSH;
		if((trace( hasPair( hand ) ,58,6,58,21,"/C:/Users/Klant/workspace2/project66/src/ThreeCardPoker.java")))
			return PAIR;		
		return NOTHING;
	}	

	public static void sortByRank( int[] hand ) {
		int smallest, temp;
		for( int i = 0; i < hand.length - 1; i++ ) { //sort hand
			smallest = i;
			for( int j = i + 1; j < hand.length; j++ )
				if((trace( getRank(hand[j]) < getRank(hand[smallest]) ,68,8,68,50,"/C:/Users/Klant/workspace2/project66/src/ThreeCardPoker.java")))
					smallest = j;
			temp = hand[smallest];
			hand[smallest] = hand[i];
			hand[i] = temp;
		}
	}

	private static boolean hasPair( int[] hand ) {
		return getRank(hand[0]) == getRank(hand[1]) ||
		       getRank(hand[1]) == getRank(hand[2]);
	}

	private static boolean hasThree( int[] hand ) {
		return getRank(hand[0]) == getRank(hand[1]) &&
		       getRank(hand[1]) == getRank(hand[2]);
	}

	private static boolean hasFlush( int[] hand ) {
		return getSuit(hand[0]) == getSuit(hand[1]) &&
		       getSuit(hand[1]) == getSuit(hand[2]);
	}

	private static boolean hasStraight( int[] hand ) {		
		return (getRank(hand[0]) == 0 && getRank(hand[1]) == 1
			&& getRank(hand[2]) == 12) && //ace low
		       (getRank(hand[1]) == getRank(hand[0]) + 1 &&
			getRank(hand[2]) == getRank(hand[1]) + 1);
	}	

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}