package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree {

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
		if (trace((trace((t == null),25,6,25,15,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,31,6,31,96,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			return null;
		} else {
			return new TreeNode(t.myItem, copy(t.myLeft), copy(t.myRight));
		}
	}

	public BinaryTree(ArrayList preorder, ArrayList inorder) {
		if (trace((trace((preorder != null),33,6,33,22,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,39,6,39,103,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			for (int pos = 0; pos < preorder.size(); pos++) {
				myRoot = add(myRoot, pos, preorder, inorder);
			}
		}
	}

	private static TreeNode add(TreeNode t, int pos, ArrayList preorder,
			ArrayList inorder) {
		if (trace((trace((t == null),42,6,42,15,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,48,6,48,96,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			return new TreeNode(preorder.get(pos));
		}
		if (trace((trace((inorder.indexOf(preorder.get(pos)) < inorder.indexOf(t.myItem)),45,6,45,68,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,51,6,51,149,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
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
		if (trace((trace((t != null),62,6,62,15,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,68,6,68,96,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
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
		if (trace((trace((t != null),78,6,78,15,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,84,6,84,96,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
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
		if (trace((trace((x == null),136,6,136,15,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,142,6,142,98,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			return 0;
		} else if (trace((trace((x.myLeft == null && x.myRight == null),138,13,138,50,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,144,13,144,134,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			return 1;
		} else {
			TreeNode tallestChild = x.myLeft;
			if (trace((trace((height(x.myRight) > height(x.myLeft)),142,7,142,43,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,148,7,148,126,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
				tallestChild = x.myRight;
			}
			return 1 + height(tallestChild);
		}
	}

	public boolean isCompletelyBalance() {
		return isCompletelyBalanced(myRoot);
	}

	private static boolean isCompletelyBalanced(TreeNode t) {
		if (trace((trace((height(t) == 0 || height(t) == 1),154,6,154,38,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,160,6,160,121,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
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
		if (trace((trace((t == null),176,6,176,15,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,182,6,182,98,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			return;
		}
		if (trace((trace((alreadySeen.contains(t)),179,6,179,29,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,185,6,185,112,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			throw new IllegalStateException();
		}
		alreadySeen.add(t);
		if (trace((trace((t.myLeft != null),183,6,183,22,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,189,6,189,105,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			check(t.myLeft);
		}
		if (trace((trace((t.myRight != null),186,6,186,23,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,192,6,192,106,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			check(t.myRight);
		}
	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (trace((trace((n == 0),198,6,198,12,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,204,6,204,95,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			return new TreeNode(new Integer(0));
		}
		if (trace((trace((n == 1),201,6,201,12,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,207,6,207,95,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
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
		if (trace((trace((expr.charAt(0) != '('),221,6,221,27,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,227,6,227,110,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
			return new TreeNode(expr); // you fill this in
		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				if (trace((trace((expr.charAt(k) == '('),231,8,231,29,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,237,8,237,112,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
					nesting++;
				}
				if (trace((trace((expr.charAt(k) == ')'),234,8,234,29,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) ,240,8,240,112,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java")){
					nesting--;
				}
				if (trace((nesting == 0,237,8,238,57,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java"))
						&& (expr.charAt(k) == '*' || expr.charAt(k) == '+')) {
					op(trace(Pos = k;,245,8,246,133,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java"))
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
		if (trace((cur.myLeft == null),265,6,265,24,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) { // nodes in expression tree must either have 0
									// or 2 children so checking only the left
									// node is sufficient
			re(trace(turn;,274,6,274,107,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java"))
		}
		optimizeHelper(cur.myLeft);
		optimizeHelper(cur.myRight);
		try {
			left = Integer.parseInt((String) cur.myLeft.myItem);
			right = Integer.parseInt((String) cur.myRight.myItem);
			if (trace((cur.myItem.equals("*")),275,7,275,29,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) {
				cur.myItem = left * right;
			} else {
				cu(trace(r.myItem = left + right;,284,7,284,112,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java"))
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
			if (trace((!nodeStack.isEmpty()),320,7,320,27,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) {
				nextNode = nodeStack.pop();
				assert nextNode != null; // since nodeStack was not empty
						(trace(					// before the pop,329,7,329,110,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java"))
				if (trace((nextNode.myRight != null),324,8,324,32,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) {
					nodeStack.push(nextNode.myRight);
					currentNode = nextNode.myRight.myLeft;
				} e(trace(lse {,333,8,333,115,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java"))
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
		if (trace((myRoot != null),365,6,365,20,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) {
			printHelper(myRoot, 0);
		}(trace(,373,6,373,103,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java"))
	}

	private static final String indent1 = "    ";

	private static void printHelper(TreeNode root, int indent) {
		if (trace((root.myRight != null),373,6,373,26,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) {
			printHelper(root.myRight, indent + 1);
		}(trace(,381,6,381,109,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java"))
		println(root.myItem, indent);
		if (trace((root.myLeft != null),377,6,377,25,"/C:/Users/Klant/workspace11/project20/src/BinaryTree.java")) {
			printHelper(root.myLeft, indent + 1);
		}(trace(,385,6,385,108,"/C:/Users/Klant/workspace11/project20/src/experimentt/BinaryTree.java"))
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


public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}