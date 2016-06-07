/*
 * MorseTree.java
 *
 * Created on April 23, 2005, 10:37 PM
 * This file is coded for JavaDoc
 * Copyright (c) 2005 Kelvin Wong
 * 
 * The MIT License
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

/**
 * This object facilitates the decoding of International Morse Code.
 * The decoding is done using a binary tree abstract data type where DITs are
 * left children and DAHs are right children. The element held by the nodes
 * is the decoded information.
 * @author  Kelvin Wong
 * @version 0.9.0
 * @license 
 */
public class MorseTree {
    
    //==========================================================================
    // Variables
    //==========================================================================
    
    BinaryTreeNode root;
    int size;
    
    // Constants
    //--------------------------------------------------------------------------
    protected final static String DIT = "·";
    protected final static String DAH = "-";
    protected final static String SPACE = " ";
    protected final static String ENDWORD = "/ ";
    protected final static String END = ". ";
    
    //==========================================================================
    // Constructors
    //==========================================================================
    
    /** 
     * Creates a new instance of MorseTree
     */
    public MorseTree() {
        
        // Set up the tree
        //----------------------------------------------------------------------
        root = new BinaryTreeNode();
        
        // Initialize the dictionary by filling it with object pairs
        // ---------------------------------------------------------------------
        
        // Numbers
        //----------------------------------------------------------------------
        put("0","-----");
        put("1","·----");
        put("2","··---");
        put("3","···--");
        put("4","····-");
        put("5","·····");
        put("6","-····");
        put("7","--···");
        put("8","---··");
        put("9","----·");
        
        // English alphabet
        //----------------------------------------------------------------------
        put("A","·-");
        put("B","-···");
        put("C","-·-·");
        put("D","-··");
        put("E","·");
        put("F","··-·");
        put("G","--·");
        put("H","····");
        put("I","··");
        put("J","·---");
        put("K","-·-");
        put("L","·-··");
        put("M","--");
        put("N","-·");
        put("O","---");
        put("P","·--·");
        put("Q","--·-");
        put("R","·-·");
        put("S","···");
        put("T","-");
        put("U","··-");
        put("V","···-");
        put("W","·--");
        put("X","-··-");
        put("Y","-·--");
        put("Z","--··");
        
        // Punctuation
        //----------------------------------------------------------------------
        put(".","·-·-·-");
        put(",","--··--");
        put("?","··--··");
        put("\'","·----·"); // apostrophe
        put("/","-··-·");
        put("\"","·-··-·"); // quotation mark
        put(":","---···");
        put("=","-···-");
        put("-","-····-");
        put("(","-·--·-");
        put(")","-·--·-");
        put("@","·--·-·");
        
        
        
    } // End constructor MorseTree()
    
    //==========================================================================
    // Methods
    //==========================================================================
    
    /**
     * This method encodes a new character to a code fragment
     * @param character The character to be encoded as an Object
     * @param code The Morse code fragment to be associated as an Object
     */
    public void put (Object character, Object code) {
        String element = (String) character;
        String path = (String) code;
        String nodeKey = "";
        BinaryTreeNode cursor = getRoot();
        BinaryTreeNode temp = new BinaryTreeNode();
        // Basic strategy is to walk down the tree using the code as a path
        // when you reach the end of the path (building nodes as necessary)
        // add the character as an element of the TreeNode.
        //
        // The following loop walks down the path, creating nodes if necessary
        for (int i=0; i<path.length(); i++) {
            nodeKey = path.substring(i,i+1);
            if (nodeKey.equals(DIT)) {
                if (cursor.hasLeftChild()) {
                    cursor = cursor.getLeftChild();
                } else {
                    cursor.setLeftChild(new BinaryTreeNode());
                    size += 1;
                    temp = cursor;
                    cursor = cursor.getLeftChild();
                    cursor.setParent(temp);
                }
            } else {
                if (cursor.hasRightChild()) {
                    cursor = cursor.getLeftChild();
                } else {
                    cursor.setRightChild(new BinaryTreeNode());
                    size += 1;
                    temp = cursor;
                    cursor = cursor.getRightChild();
                    cursor.setParent(temp);
                }
            }
        } // End for loop i
        // At the end of the loop, cursor holds the last node of the path
        cursor.setElement(element);
    } // End method put(Object character, Object code)
    
    /**
     * This method decodes a parsed fragment of Morse code.
     * @param code A Morse code fragment as a String
     * @return The decoded fragment as a String
     */
    public String decode (String code) throws RuntimeException {
        String path = (String) code;
        String nodeKey = "";
        BinaryTreeNode cursor = getRoot();
        // Basic strategy is to walk down the tree using the code as a path
        // if there is no such code, return an exception else return the 
        // decoded string
        for (int i=0; i<path.length(); i++) {
            nodeKey = path.substring(i,i+1);
            if (nodeKey.equals(DIT)) {
                if (cursor.hasLeftChild()) {
                    cursor = cursor.getLeftChild();
                } else {
                    throw new RuntimeException("Code pattern not found.");
                }
            } else if (nodeKey.equals(DAH)) {
                if (cursor.hasRightChild()) {
                    cursor = cursor.getRightChild();
                } else {
                    throw new RuntimeException("Code pattern not found. ");
                }
            }
        } // End for loop i
        // At the end of the loop, cursor holds the last node of the path
        return (String) cursor.element();
    } // End method decode (String code)
    
    /**
     * This method returns a reference to the root of the MorseTree
     * @return A reference to the root as a BinaryTreeNode
     */
    public BinaryTreeNode getRoot() {
        return root;
    } // End method getRoot()
    
    /**
     * This method returns the size of the MorseTree
     * @return The number of nodes in the MorseTree as an int
     */
    public int size() {
        return size;
    } // End method size()
    
    /**
     * This method returns some information on the MorseTree object
     * @return Some information on the MorseTree object as a String
     */
    public String toString() {
        String info = "I am a MorseTree object. ";
        info += "I have " + size() + " nodes encoded. ";
        return info;
    }
    
    public static void main (String[] args) {
        System.out.println("MorseTree - A binary tree for decoding Morse code.\n");
        MorseTree decoder = new MorseTree();
        
        // Print out the MorseTree
        System.out.println("MorseTree decoder says: " + decoder);
        
        // Decode some fragments
        String code1 = "-----";
        String code2 = "·-";
        String code3 = "--··";
        String code4 = "·--·-·";
        System.out.println("Input:  " + code1);
        System.out.println("Output: " + decoder.decode(code1));
        System.out.println("Input:  " + code2);
        System.out.println("Output: " + decoder.decode(code2));
        System.out.println("Input:  " + code3);
        System.out.println("Output: " + decoder.decode(code3));
        System.out.println("Input:  " + code4);
        System.out.println("Output: " + decoder.decode(code4));
        
        
    } // End method main(String[] args)
    
    //==========================================================================
    // Inner Classes
    //==========================================================================
    /**
     * This inner class creates a Binary Tree node.
     */
    private class BinaryTreeNode {
        //======================================================================
        // Variables
        //======================================================================
        protected BinaryTreeNode parent;
        protected BinaryTreeNode leftChild;
        protected BinaryTreeNode rightChild;
        protected Object element;
        //======================================================================
        // Constructors
        //======================================================================
        /**
         *  This constructor creates an empty BinaryTreeNode.
         */
        public BinaryTreeNode () {
            
        } // End constructor BinaryTreeNode()

        /**
         * This constructor creates a new BinaryTreeNode holding an Object passed
         * to it.
         * @param o An object to be associated with the node as an Object.
         */
        public BinaryTreeNode(Object o) {
            setElement(o);
        } // End constructor BinaryTreeNode(Object o)
        //======================================================================
        // Methods
        //======================================================================
        /**
         * This method sets the parent of the BinaryTreeNode to the node passed
         * as a parameter.
         * @param n A BinaryTreeNode object to be set as the parent
         */
        private void setParent (BinaryTreeNode n) {
            parent = n;
        } // End method setParent (BinaryTreeNode n)

        /**
         * This method returns the parent of the current node.
         * @return Parent as a BinaryTreeNode.
         */
        private BinaryTreeNode getParent() {
            return parent;
        } // End method getParent()

        /**
         * This method sets the left child of the current node to the node
         * passed as a parameter.
         * @param child The node to be set as the left child as a BinaryTreeNode
         */
        private void setLeftChild (BinaryTreeNode child) {
            leftChild = child;
        } // End method setLeftChild(BinaryTreeNode child)

        /**
         * This method sets the right child of the current node to the node
         * passed as a parameter.
         * @param child The node to be set as the right child as a BinaryTreeNode
         */
        private void setRightChild (BinaryTreeNode child) {
            rightChild = child;
        } // End method setLeftChild(BinaryTreeNode)
        
        /**
         * This method tests the current node for the presence of a left child.
         * @return TRUE if a left child is associated with the current node as a boolean
         */
        private boolean hasLeftChild () {
            return (leftChild != null);
        } // End method hasLeftChild()
        
        /**
         * This method tests the current node for the presence of a right child.
         * @return TRUE if a right child is associated with the current node as a boolean
         */
        private boolean hasRightChild () {
            return (rightChild != null);
        } // End method hasLeftChild()
        
        /**
         * This method associates the current node with an Object passed as a parameter.
         * @param o The element to be associated with the current node as an Object.
         */
        private void setElement (Object o) {
            element = o;
        } // End method setElement(Object o)
        
        /**
         * This method returns the left child node of the current node.
         * @return The left child object as a BinaryTreeNode
         */
        private BinaryTreeNode getLeftChild() {
            return (BinaryTreeNode) leftChild;
        } // End method getLeftChild()
        
        /**
         * This method returns the right child node of the current node.
         * @return The right child object as a BinaryTreeNode
         */
        private BinaryTreeNode getRightChild() {
            return (BinaryTreeNode) rightChild;
        } // End method getRightChild()
        
        /**
         * This method returns the element associated with the current node.
         * @return The element associated with the current node as an Object
         */
        private Object element() {
            return element;
        } // End method element()
        
        /**
         * This method returns the height of the current node.
         * @return The height of the current node as an integer
         */
        private int getHeight() {
            return findHeight(this);
        } // End method getHeight()
        
        private int findHeight(BinaryTreeNode n) {
            if (((BinaryTreeNode)n).isLeaf())
                return 0;
            else
            return 1 + Math.max(((BinaryTreeNode)n).findHeight(n.getLeftChild()), ((BinaryTreeNode)n).findHeight(n.getRightChild()));
        } // End method findHeight(BinaryTreeNode)

        /**
         * This method determines if the current node is a leaf or an external node.
         * @return TRUE if the node is a leaf as a boolean.
         */
        private boolean isLeaf () {
            return ((getLeftChild() == null) && (getRightChild() == null));
        } // End method isLeaf()
        
        /**
         * This method returns the size of the subtree using the current node as a root.
         * @return Size of the subtree as an integer
         */
        private int getSize() {
            return findSize(this);
        } // End method getSize()
        
        private int findSize (BinaryTreeNode n) {
            if (((BinaryTreeNode)n).isLeaf())
                return 1;
            else
                return 1 + findSize(n.getLeftChild()) + findSize(n.getRightChild());
        } // End method findSize(BinaryTreeNode n)
        
        /**
         * This method returns details of the object instance.
         * @return Instance details as a String
         */
        public String toString() {
            String out = "I am a BinaryTreeNode. ";
            if (isLeaf())
                out += "I am a leaf. ";
            else
                out += "I am an internal node. ";
                
            out += "I have " + getSize() + " nodes (including me). ";
            out += "My height is " + getHeight() + ". ";
            out += "I am holding --> " + element();
            return out;
        } // End method toString()
    } // End class BinaryTreeNode
}