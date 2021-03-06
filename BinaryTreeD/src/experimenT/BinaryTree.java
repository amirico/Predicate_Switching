package experimenT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree {

	public static int experimentalY = 0;
 public static int experimentalI;

public static boolean functionF(boolean b){  
experimentalY++;
 	if (experimentalI == experimentalY) return !b; else  return b;}
protected TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	// Constructor that copies the structure of toCopy Tree.
	public BinaryTree(BinaryTree toCopy) {
		myRoot = copy(toCopy.myRoot);
	}

	// Helper method for the previous constructor
	private static TreeNode copy(TreeNode t) {
		if (t == null) {
			return null;
		} else {
			return new TreeNode(t.myItem, copy(t.myLeft), copy(t.myRight));
		}
	}

	public BinaryTree(ArrayList preorder, ArrayList inorder) {
		if (preorder != null) {
			for (int pos = 0; pos < preorder.size(); pos++) {
				myRoot = add(myRoot, pos, preorder, inorder);
			}
		}
	}

	private static TreeNode add(TreeNode t, int pos, ArrayList preorder,
			ArrayList inorder) {
		if (t == null) {
			return new TreeNode(preorder.get(pos));
		}
		if (inorder.indexOf(preorder.get(pos)) < inorder.indexOf(t.myItem)) {
			t.myLeft = add(t.myLeft, pos, preorder, inorder);
		} else {
			t.myRight = add(t.myRight, pos, preorder, inorder);
		}
		return t;
	}

	// Print the values in the tree in preorder: root value first,
	// then values in the left subtree (in preorder), then values
	// in the right subtree (in preorder).
	public void printPreorder() {
		printPreorder(myRoot);
		System.out.println();
	}

	private static void printPreorder(TreeNode t) {
		if (functionF(t != null)) {
			System.out.print(t.myItem + " ");
			printPreorder(t.myLeft);
			printPreorder(t.myRight);
		}
	}

	// Print the values in the tree in inorder: values in the left
	// subtree first (in inorder), then the root value, then values
	// in the right subtree (in inorder).
	public void printInorder() {
		printInorder(myRoot);
		System.out.println();
	}

	private static void printInorder(TreeNode t) {
		if (functionF(t != null)) {
			printInorder(t.myLeft);
			System.out.print(t.myItem + " ");
			printInorder(t.myRight);
		}
	}

	public void fillSampleTree1() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
	}

	public void fillSampleTree2() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
	}

	public void fillSampleTree3() {
		myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C",
				new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
	}

	public void fillSampleTree4() {
		myRoot = new TreeNode("A");
	}

	public void fillSampleTree5() {
		myRoot = new TreeNode("A", new TreeNode("B", new TreeNode("C",
				new TreeNode("D", new TreeNode("E"), null), null), null), null);
	}

	public void fillSampleTree6() {
		myRoot = new TreeNode("A", new TreeNode("B", new TreeNode("D"),
				new TreeNode("E")), new TreeNode("C", new TreeNode("F"), null));
	}

	public void fillSampleTree7() {
		myRoot = new TreeNode("A", new TreeNode("B", new TreeNode("D"),
				new TreeNode("E")), new TreeNode("C", new TreeNode("F"),
				new TreeNode("G")));
	}

	public void fillSampleTree8() {
		TreeNode D = new TreeNode("D");
		myRoot = new TreeNode("A", new TreeNode("B", null, D), new TreeNode(
				"C", D, null));
	}

	public void fillSampleTree9() {
		TreeNode B = new TreeNode("B");
		myRoot = new TreeNode("A", B, null);
		B.myLeft = myRoot;
	}

	public int height() {
		return BinaryTree.height(myRoot);
	}

	private static int height(TreeNode x) {
		if (x == null) {
			return 0;
		} else if (x.myLeft == null && x.myRight == null) {
			return 1;
		} else {
			TreeNode tallestChild = x.myLeft;
			if (height(x.myRight) > height(x.myLeft)) {
				tallestChild = x.myRight;
			}
			return 1 + height(tallestChild);
		}
	}

	public boolean isCompletelyBalance() {
		return isCompletelyBalanced(myRoot);
	}

	private static boolean isCompletelyBalanced(TreeNode t) {
		if (height(t) == 0 || height(t) == 1) {
			return true;
		}
		return isCompletelyBalanced(t.myLeft)
				|| isCompletelyBalanced(t.myRight)
				|| height(t.myLeft) == height(t.myRight);
	}

	public boolean isOK() {
		alreadySeen = new ArrayList();
		try {
			check(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	// Contains nodes already seen in the traversal.
	private ArrayList alreadySeen;

	private void check(TreeNode t) throws IllegalStateException {
		if (t == null) {
			return;
		}
		if (alreadySeen.contains(t)) {
			throw new IllegalStateException();
		}
		alreadySeen.add(t);
		if (t.myLeft != null) {
			check(t.myLeft);
		}
		if (t.myRight != null) {
			check(t.myRight);
		}
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (n == 0) {
			return new TreeNode(new Integer(0));
		}
		if (n == 1) {
			return new TreeNode(new Integer(1));
		}
		TreeNode left = fibTreeHelper(n - 1);
		TreeNode right = fibTreeHelper(n - 2);
		return new TreeNode(new Integer(((Integer) left.myItem).intValue()
				+ ((Integer) right.myItem).intValue()), left, right);

	}

	public static BinaryTree exprTree(String s) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.exprTreeHelper(s);
		return result;
	}

	// Return the tree corresponding to the given arithmetic expression.
	// The expression is legal, fully parenthesized, contains no blanks,
	// and involves only the operations + and *.
	private TreeNode exprTreeHelper(String expr) {
		if (expr.charAt(0) != '(') {
			return new TreeNode(expr); // you fill this in
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				if (expr.charAt(k) == '(') {
					nesting++;
				}
				if (expr.charAt(k) == ')') {
					nesting--;
				}
				if (nesting == 0
						&& (expr.charAt(k) == '*' || expr.charAt(k) == '+')) {
					opPos = k;
					break;
				}
				// you fill this in
			}
			String opnd1 = expr.substring(1, opPos);
			String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
			String op = expr.substring(opPos, opPos + 1);
			System.out.println("expression = " + expr);
			System.out.println("operand 1  = " + opnd1);
			System.out.println("operator   = " + op);
			System.out.println("operand 2  = " + opnd2);
			System.out.println();
			TreeNode left = exprTreeHelper(opnd1);
			TreeNode right = exprTreeHelper(opnd2);
			return new TreeNode(op, left, right);
			// you fill this in
		}
	}

	public void optimize() {
		BinaryTree.optimizeHelper(myRoot);
	}

	private static void optimizeHelper(TreeNode cur) {
		int left, right;
		if (cur.myLeft == null) { // nodes in expression tree must either have 0
									// or 2 children so checking only the left
									// node is sufficient
			return;
		}
		optimizeHelper(cur.myLeft);
		optimizeHelper(cur.myRight);
		try {
			left = Integer.parseInt((String) cur.myLeft.myItem);
			right = Integer.parseInt((String) cur.myRight.myItem);
			if (cur.myItem.equals("*")) {
				cur.myItem = left * right;
			} else {
				cur.myItem = left + right;
			}
			cur.myItem = cur.myItem.toString();
			cur.myLeft = null;
			cur.myRight = null;
		} catch (NumberFormatException e) {
		}
	}

	// This code gets put inside the BinaryTree class.

	// Method for the BinaryTree class
	public Iterator iterator() {
		return new InorderIterator();
	}

	// Inner class inside of the BinaryTree class.
	// Also, it uses java.util.Iterator and java.util.Stack.
	private class InorderIterator implements Iterator {
		private Stack<TreeNode> nodeStack;
		private TreeNode currentNode;

		public InorderIterator() {
			nodeStack = new Stack<TreeNode>();
			nodeStack.push(myRoot);
			currentNode = myRoot.myLeft;
		} // end default constructor

		public boolean hasNext() {
			return !nodeStack.isEmpty();
		} // end hasNext

		public Object next() {
			TreeNode nextNode = null;

			// find leftmost node with no left child
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.myLeft;
			} // end while

			// get leftmost node, then move to its right subtree
			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				assert nextNode != null; // since nodeStack was not empty
											// before the pop
				if (nextNode.myRight != null) {
					nodeStack.push(nextNode.myRight);
					currentNode = nextNode.myRight.myLeft;
				} else {
					currentNode = nextNode.myRight;
				}
			} else {
				throw new NoSuchElementException();
			}
			return nextNode.myItem;
		} // end next

		public void remove() {
			throw new UnsupportedOperationException();
		} // end remove

	} // end InorderIterator

	public static void main(String[] args) {
		experimentalI = Integer.parseInt((args[0]));;
BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		t.fillSampleTree1();
		print(t, "sample tree 1");
		t.fillSampleTree2();
		print(t, "sample tree 2");
		t.fillSampleTree3();
		print(t, "sample tree 3");
		t.print();
		try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace2\\project91T2\\results.txt",true)))){out.println(t.isCompletelyBalance() == false);}catch(IOException ioException){} 
System.out.println(t.isCompletelyBalance());
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	public void print() {
		if (functionF(myRoot != null)) {
			printHelper(myRoot, 0);
		}
	}

	private static final String indent1 = "    ";

	private static void printHelper(TreeNode root, int indent) {
		if (functionF(root.myRight != null)) {
			printHelper(root.myRight, indent + 1);
		}
		println(root.myItem, indent);
		if (functionF(root.myLeft != null)) {
			printHelper(root.myLeft, indent + 1);
		}
	}

	private static void println(Object obj, int indent) {
		for (int k = 0; k < indent; k++) {
			System.out.print(indent1);
		}
		System.out.println(obj);
	}

	protected static class TreeNode {

		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;

		public TreeNode(Object obj) {
			myItem = obj;
			myLeft = myRight = null;
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
			myItem = obj;
			myLeft = left;
			myRight = right;
		}
	}

}