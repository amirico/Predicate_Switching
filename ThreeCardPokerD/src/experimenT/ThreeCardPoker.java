package experimenT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ThreeCardPoker {
	public static int experimentalY = 0;
 public static int experimentalI;

public static boolean functionF(boolean b){  
experimentalY++;
 	if (experimentalI == experimentalY) return !b; else  return b;}
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
		experimentalI = Integer.parseInt((args[0]));;
int[] deck = new int[52];
		int[] hand = {22,32,51};//		int[] hand = new int[3];				
		for( int i = 0; i < deck.length; i++ )
			deck[i] = i;
		shuffle( deck );
//		for( int i = 0; i < hand.length; i++ )
//			hand[i] = deck[i];		
		int score = score(hand);
		try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace2\\project66\\results.txt",true)))){out.println((score == 6));}catch(IOException ioException){} 
System.out.println("Hand: ");
		print(hand);
		if( functionF(score == 0) )
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
		if( functionF(hasStraight( hand ) && hasFlush( hand )) )
			return STRAIGHT_FLUSH;
		if( functionF(hasThree( hand )) )
			return THREE_OF_A_KIND;
		if( functionF(hasStraight( hand )) )
			return STRAIGHT;
		if( functionF(hasFlush( hand )) )
			return FLUSH;
		if( functionF(hasPair( hand )) )
			return PAIR;		
		return NOTHING;
	}	

	public static void sortByRank( int[] hand ) {
		int smallest, temp;
		for( int i = 0; i < hand.length - 1; i++ ) { //sort hand
			smallest = i;
			for( int j = i + 1; j < hand.length; j++ )
				if( functionF(getRank(hand[j]) < getRank(hand[smallest])) )
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
}