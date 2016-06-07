package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** *//**
 * A word trie which can only deal with 26 alphabeta letters.
 * @author Leeclipse
 * @since 2007-11-21
 */

public class Trie{
 
   private Vertex root;//һ��Trie����һ�����ڵ�

    //�ڲ���
    protected class Vertex{//�ڵ���
        protected int words;
        protected int prefixes;
        protected Vertex[] edges;//ÿ���ڵ����26���ӽڵ�(����Ϊ����)
        Vertex() {
            words = 0;
            prefixes = 0;
            edges = new Vertex[26];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = null;
            }
        }
    }

  
    public Trie () {
        root = new Vertex();
    }

   
    /** *//**
     * List all words in the Trie.
     * 
     * @return
     */

    public List<String> listAllWords() {
       
        List<String> words = new ArrayList<String>();
        Vertex[] edges = root.edges;
       
        for (int i = 0; i < edges.length; i++) {
            if (trace((edges[i] != null),49,16,49,32,"/C:/Users/Klant/workspace2/project90/src/Trie.java")) {
                     String word = "" + (char)('a' + i);
                depthFirstSearchWords(words, edges[i], word);
            }
        }        
        return words;
    }

     /** *//**
     * Depth First Search words in the Trie and add them to the List.
     * 
     * @param words
     * @param vertex
     * @param wordSegment
     */

    private void depthFirstSearchWords(List<String> words, Vertex vertex, String wordSegment) {
        Vertex[] edges = vertex.edges;
        boolean hasChildren = false;
        for (int i = 0; i < edges.length; i++) {
            if (trace((edges[i] != null),69,16,69,32,"/C:/Users/Klant/workspace2/project90/src/Trie.java")) {
                hasChildren = true;
                String newWord = wordSegment + (char)('a' + i);                
                depthFirstSearchWords(words, edges[i], newWord);
            }            
        }
        if (trace((!hasChildren),75,12,75,24,"/C:/Users/Klant/workspace2/project90/src/Trie.java")) {
            words.add(wordSegment);
        }
    }

    public int countPrefixes(String prefix) {
        return countPrefixes(root, prefix);
    }

    private int countPrefixes(Vertex vertex, String prefixSegment) {
        if (trace((prefixSegment.length() == 0),85,12,85,39,"/C:/Users/Klant/workspace2/project90/src/Trie.java")) { //reach the last character of the word
            return vertex.prefixes;
        }

        char c = prefixSegment.charAt(0);
        int index = c - 'a';
        if (trace((vertex.edges[index] == null),91,12,91,39,"/C:/Users/Klant/workspace2/project90/src/Trie.java")) { // the word does NOT exist
            return 0;
        } else {

            return countPrefixes(vertex.edges[index], prefixSegment.substring(1));

        }        

    }

    public int countWords(String word) {
        return countWords(root, word);
    }    

    private int countWords(Vertex vertex, String wordSegment) {
        if (trace((wordSegment.length() == 0),106,12,106,37,"/C:/Users/Klant/workspace2/project12/src/Trie.java")) { //reach the last character of the word
            return vertex.words;
        }

        char c = wordSegment.charAt(0);
        int index = c - 'a';
        if (trace((vertex.edges[index] == null),112,12,112,39,"/C:/Users/Klant/workspace2/project12/src/Trie.java")) { // the word does NOT exist
            return 0;
        } else {
            return countWords(vertex.edges[index], wordSegment.substring(1));

        }        

    }

    
    /** *//**
     * Add a word to the Trie.
     * 
     * @param word The word to be added.
     */

    public void addWord(String word) {
        addWord(root, word);
    }

    

    /** *//**
     * Add the word from the specified vertex.
     * @param vertex The specified vertex.
     * @param word The word to be added.
     */

    private void addWord(Vertex vertex, String word) {
       if (trace((word.length() == 0),141,11,141,29,"/C:/Users/Klant/workspace2/project12/src/Trie.java")) { //if all characters of the word has been added
            vertex.words ++;
        } else {
            vertex.prefixes ++;
            char c = word.charAt(0);
            c = Character.toLowerCase(c);
            int index = c - 'a';
            if (trace((vertex.edges[index] == null),148,16,148,43,"/C:/Users/Klant/workspace2/project12/src/Trie.java")) { //if the edge does NOT exist
                vertex.edges[index] = new Vertex();
            }

            addWord(vertex.edges[index], word.substring(1)); //go the the next character
        }
    }

    public static void main(String args[])  //Just used for test
    {
    Trie trie = new Trie();
    trie.addWord("China");
    trie.addWord("China");
    trie.addWord("China");

    trie.addWord("crawl");
    trie.addWord("crime");
    trie.addWord("ban");
    trie.addWord("China");

    trie.addWord("english");
    trie.addWord("establish");
    trie.addWord("eat");
    System.out.println(trie.root.prefixes);
     System.out.println(trie.root.words);


   
     List<String> list = trie.listAllWords();
     Iterator listiterator = list.listIterator();
     
     while(listiterator.hasNext())
     {
          String s = (String)listiterator.next();
          System.out.println(s);
     }

   
     int count = trie.countPrefixes("ch");
     int count1=trie.countWords("china");
     System.out.println("the count of c prefixes:"+count);
     System.out.println("the count of china countWords:"+count1);

 
    }

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}