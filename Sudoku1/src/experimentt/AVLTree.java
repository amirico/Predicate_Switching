package experimentt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AVL_Tree.java
 * 
 * by Calin Curescu 2006-01-03
 * cosmetic modifications and better documentation by Tommy Farnqvist 2013-01-12 
 */

import java.io.*;

/**
 * An AVL Tree class
 */
public class AVLTree {

	/** Root node of the AVL tree */
	private AVLTreeNode rootNode = null;

	/**
	 * Inner class for representing the AVL tree nodes
	 */
	class AVLTreeNode {
		double value;
		private AVLTreeNode parent = null;
		private AVLTreeNode left = null;
		private AVLTreeNode right = null;
		int height = 0;

		AVLTreeNode(double val) {
			value = val;
		}
	}

	/**
	 * A special type of Exception for our AVL trees
	 */
	public class AVLTreeException extends Exception {
		private static final long serialVersionUID = 17L;

		AVLTreeException(String msg) {
			super(msg);
		}
	}

	// Private methods for AVLTree management:

	/*
	 * Returns true if the node is the left child of it's parent. Throws an
	 * AVLTreeException if the node is null or a rootNode.
	 */
	private boolean isLeftChild(AVLTreeNode node) throws AVLTreeException {
		if (trace((node == null),51,6,51,18,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			throw new AVLTreeException(
					"Error, in isLeftChild(), the node is null!");
		}
		if (trace((node.parent == null),55,6,55,25,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			throw new AVLTreeException("Error, the node does not have a parent");
		}
		return (node == node.parent.left);
	}

	/*
	 * Returns a node's height. Note: works also on null (empty trees) and
	 * returns -1 on them.
	 */
	private static int getHeight(AVLTreeNode node) {
		if (trace((node != null),66,6,66,18,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			return node.height;
		}
		return -1;
	}

	/*
	 * Adjust the height for a node. Needs the height of the left and right
	 * subtrees to be accurate. If node = null it just returns. Returns the
	 * updated height of the node
	 */
	private static void adjustHeight(AVLTreeNode node) {
		if (trace((node == null),78,6,78,18,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			return;
		}
		node.height = 1 + (getHeight(node.left) > getHeight(node.right) ? getHeight(node.left)
				: getHeight(node.right));
	}

	private static AVLTreeNode tallerChild(AVLTreeNode node) {
		return (getHeight(node.left) > getHeight(node.right) ? node.left
				: node.right);
	}

	private static boolean isBalanced(AVLTreeNode node) {
		if (trace((node == null),91,6,91,18,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			return true;
		}
		if (trace((Math.abs(getHeight(node.left) - getHeight(node.right)) > 1),94,6,94,64,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			return false;
		}
		return true;
	}

	/*
	 * Rebalance the tree starting from a node, and searching upwards for the
	 * imbalanced node (the z node in the lecture). Throws an AVLTreeException if
	 * node is null.
	 */
	private void rebalance(AVLTreeNode node) throws AVLTreeException {
		if (trace((node == null),106,6,106,18,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			throw new AVLTreeException(
					"Error, should not  rebalance a null node");
		}
		int prevHeight = node.height;
		adjustHeight(node);

		if (trace((!isBalanced(node)),113,6,113,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			node = restructure(node);
		} else if (trace((prevHeight != node.height && node != rootNode),115,13,115,58,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			if (trace((node.parent == null),116,7,116,26,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
				throw new AVLTreeException(
						"Error, a non-root node does not have a parent");
			}
			rebalance(node.parent);
		}
	}

	/*
	 * Do the trinode restructuring, imbalanced node as argument. Throws an
	 * AVLTreeException if isLeftChild() is used improperly in the node;
	 */
	private AVLTreeNode restructure(AVLTreeNode zNode) throws AVLTreeException {

		AVLTreeNode yNode = tallerChild(zNode);
		AVLTreeNode xNode = tallerChild(yNode);
		// identify a,b,c the inorder of x,y,z
		AVLTreeNode aNode = null;
		AVLTreeNode bNode = null;
		AVLTreeNode cNode = null;
		// identify subtrees T0, T1, T2, T3
		AVLTreeNode t0SubTree = null;
		AVLTreeNode t1SubTree = null;
		AVLTreeNode t2SubTree = null;
		AVLTreeNode t3SubTree = null;

		if (trace((isLeftChild(yNode)),142,6,142,24,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			if (trace((isLeftChild(xNode)),143,7,143,25,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
				// single rotation right:
				aNode = xNode;
				bNode = yNode;
				cNode = zNode;
				t0SubTree = xNode.left;
				t1SubTree = xNode.right;
				t2SubTree = yNode.right;
				t3SubTree = zNode.right;
			} else {
				// double rotation left-right:
				aNode = yNode;
				bNode = xNode;
				cNode = zNode;
				t0SubTree = yNode.left;
				t1SubTree = xNode.left;
				t2SubTree = xNode.right;
				t3SubTree = zNode.right;
			}
		} else if (trace((isLeftChild(xNode)),162,13,162,31,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			// double rotation right-left:
			aNode = zNode;
			bNode = xNode;
			cNode = yNode;
			t0SubTree = zNode.left;
			t1SubTree = xNode.left;
			t2SubTree = xNode.right;
			t3SubTree = yNode.right;
		} else {
			// single rotation left:
			aNode = zNode;
			bNode = yNode;
			cNode = xNode;
			t0SubTree = zNode.left;
			t1SubTree = yNode.left;
			t2SubTree = xNode.left;
			t3SubTree = xNode.right;
		}

		// replace zNode with bNode
		if (trace((zNode.parent == null),183,6,183,26,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			rootNode = bNode;
		} else {
			if (trace((isLeftChild(zNode)),186,7,186,25,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java"))
				zNode.parent.left = bNode;
			else
				zNode.parent.right = bNode;
		}
		bNode.parent = zNode.parent;
		// set the other links:
		bNode.left = aNode;
		aNode.parent = bNode;
		bNode.right = cNode;
		cNode.parent = bNode;
		aNode.left = t0SubTree;
		if (trace((t0SubTree != null),198,6,198,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			t0SubTree.parent = aNode;
		}
		aNode.right = t1SubTree;
		if (trace((t1SubTree != null),202,6,202,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			t1SubTree.parent = aNode;
		}
		cNode.left = t2SubTree;
		if (trace((t2SubTree != null),206,6,206,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			t2SubTree.parent = cNode;
		}
		cNode.right = t3SubTree;
		if (trace((t3SubTree != null),210,6,210,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			t3SubTree.parent = cNode;
		}
		// adjust heights:
		adjustHeight(aNode);
		adjustHeight(cNode);
		adjustHeight(bNode);

		return bNode;
	}

	/*
	 * Help function to be used by find, insert and delete. It searches for the
	 * value in the tree and returns the first occurence. If the value is not
	 * found it returns the last node in the tree, so it can be used in inserts.
	 * Returns null only when initial node is null.
	 */
	private AVLTreeNode findClosestNode(AVLTreeNode node, double val) {
		boolean found = (node == null);

		while (!found) {
			if (trace((val < node.value),231,7,231,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
				if (trace((node.left == null),232,8,232,25,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
					found = true;
				} else {
					node = node.left;
				}
			} else if (trace((val > node.value),237,14,237,30,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
				if (trace((node.right == null),238,8,238,26,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
					found = true;
				} else {
					node = node.right;
				}
			} else {
				found = true;
			}
		}
		return node;
	}

	/*
	 * Insert value in subtree that has node as a root. Throws an
	 * AVLTreeException if there is a rebalancing problem.
	 */
	void insert(AVLTreeNode node, double val) throws AVLTreeException {
		AVLTreeNode foundNode = findClosestNode(node, val);

		if (trace((foundNode == null),257,6,257,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			rootNode = new AVLTreeNode(val);
			return;
		}

		if (trace((foundNode.value == val),262,6,262,28,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			System.out.println("Value already in tree");
			return;
		}

		AVLTreeNode newNode = new AVLTreeNode(val);
		newNode.parent = foundNode;

		if (trace((val < foundNode.value),270,6,270,27,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			foundNode.left = newNode;
		} else {
			foundNode.right = newNode;
		}

		rebalance(foundNode);
	}

	/*
	 * Delete value in subtree that has node as a root.
	 */
	void delete(AVLTreeNode node, double val) throws AVLTreeException {

		// !!!You should implement this function!!!

	}

	/*
	 * Prints elements in order.
	 */
	private void inorderPrint(AVLTreeNode node) {
		if (trace((node != null),292,6,292,18,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			inorderPrint(node.left);
			System.out.println(node.value);
			inorderPrint(node.right);
		}
	}

	/*
	 * Prints tree in ASCII art.
	 */
	private void printASCII(AVLTreeNode node, String indent) {
		String indentStep = "     ";
		String indentStep2 = "  ";
		if (trace((node != null),305,6,305,18,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {

			printASCII(node.right, indent + indentStep);

			if (trace((node.right != null),309,7,309,25,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
				System.out.println(indent + indentStep2 + "/");
			}

			System.out.println(indent + node.value);

			if (trace((node.left != null),315,7,315,24,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
				System.out.println(indent + indentStep2 + "\\");
			}

			printASCII(node.left, indent + indentStep);
		}
	}

	/*
	 * Constructs a new empty tree.
	 */
	AVLTree() {
	}

	/*
	 * Public functions for AVLTree
	 */

	/**
	 * Inserts a value in the tree. If it's already there it will print an error
	 * message and do nothing. Throws an AVLTreeException if there is an insert
	 * problem.
	 * 
	 * @param val
	 *            the value to be inserted
	 */
	public void insert(double val) throws AVLTreeException {
		insert(rootNode, val);
	}

	/**
	 * Delete value from the tree. If it does not exist it will print an error
	 * message and do nothing.
	 * 
	 * @param val
	 *            the value to be deleted
	 */
	public void delete(double val) throws AVLTreeException {
		delete(rootNode, val);
	}

	/**
	 * Search for value in the tree.
	 * 
	 * @param val
	 *            the value to be searched for
	 * @return <code>true</code> if <code>val</code> is found in the tree
	 */
	public boolean find(double val) {
		if (trace((rootNode == null),364,6,364,22,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			return false;
		}
		if (trace((findClosestNode(rootNode, val).value != val),367,6,367,49,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {//fault
			return true;
		}
		return false;
	}

	/**
	 * Get a value currently inserted in the tree
	 * 
	 * @return the value of the root node of the tree.
	 * @throw throws an AVLTreeException if the tree is empty
	 */
	double get() throws AVLTreeException {
		if (trace((rootNode == null),380,6,380,22,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			throw new AVLTreeException("The tree is empty!");
		}
		return rootNode.value;
	}

	/**
	 * Finds the minimum value currently inserted in the tree
	 * 
	 * @return the minimum value of all nodes of the tree.
	 * @throw throws an AVLTreeException if the tree is empty
	 */
	double getMin() throws AVLTreeException {
		if (trace((rootNode == null),393,6,393,22,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			throw new AVLTreeException("The tree is empty!");
		}
		return findClosestNode(rootNode, Double.NEGATIVE_INFINITY).value;
	}

	/**
	 * Finds the maximum value currently inserted in the tree
	 * 
	 * @return the maximum value of all nodes of the tree.
	 * @throw throws an AVLTreeException if the tree is empty
	 */
	double getMax() throws AVLTreeException {
		if (trace((rootNode == null),406,6,406,22,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
			throw new AVLTreeException("The tree is empty!");
		}
		return findClosestNode(rootNode, Double.POSITIVE_INFINITY).value;
	}

	/**
	 * Check if the tree is empty
	 * 
	 * @return <code>true</code> if the tree is empty, otherwise
	 *         <code>false</code>
	 */
	boolean isEmpty() {
		return rootNode == null;
	}

	/**
	 * Replaces the tree with the subtree starting at node as the new tree.
	 * 
	 * @param node
	 *            the node to use as new root of the tree
	 */
	void setRoot(AVLTreeNode node) {
		rootNode = node;
	}

	/**
	 * Prints node values in order.
	 */
	void inorderPrint() {
		inorderPrint(rootNode);
	}

	/**
	 * Prints tree in ASCII art.
	 */
	void print() {
		System.out.println();
		printASCII(rootNode, "  ");
		System.out.println();
	}

	/**
	 * Runs an interactive tree simulation.
	 */
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(
				System.in));

		for (int i = 1; i <= 11; i++) {
			try {
				tree.insert(i);
			} catch (AVLTreeException e) {
				System.out.println("e.getMessage()");
				e.printStackTrace();
			}
		}

		System.out.println("An ASCII representation of the tree is:\n");
		tree.print();

		int choice = -1;
		double value;

//		while (true) {
			System.out.println("1.  Insert");
			System.out.println("2.  Delete");
			System.out.println("3.  Search for value");
			System.out.println("4.  Search for smallest value");
			System.out.println("5.  Search for largest value");
			System.out.println("6.  Print tree inorder");
			System.out.println("7.  Print tree ");
			System.out.println("0.  Exit");

			try {
				choice = 3;

				switch (choice) {
				case 0:
					System.out.println("Exit");
					return;
				case 1:
					System.out.println("Input value: ");
					value = Double.valueOf(inputReader.readLine())
							.doubleValue();
					tree.insert(value);
					break;
				case 2:
					System.out.println("Value to remove: ");
					value = Double.valueOf(inputReader.readLine())
							.doubleValue();
					tree.delete(value);
					break;
				case 3:
					System.out.println("Value to find: ");
					value = 3;//Double.valueOf(inputReader.readLine())
//							.doubleValue();
					if (trace((tree.find(value)),504,9,504,25,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
						System.out.println("Value " + value
								+ " found in the tree");
					} else {
						System.out.println("Value " + value
								+ " not found in the tree");
					}
					break;
				case 4:
					if (trace((tree.isEmpty()),513,9,513,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
						System.out.println("Tree is empty!");
					} else {
						System.out.println("The smallest value in the tree is "
								+ tree.getMin());
					}
					break;
				case 5:
					if (trace((tree.isEmpty()),521,9,521,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
						System.out.println("Tree is empty!");
					} else {
						System.out.println("The largest value in the tree is "
								+ tree.getMax());
					}
					break;
				case 6:
					if (trace((tree.isEmpty()),529,9,529,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
						System.out.println("Tree is empty!");
					} else {
						System.out.println("The inorder print of the tree is:");
						tree.inorderPrint();
						System.out.println();
					}
					break;
				case 7:
					if (trace((tree.isEmpty()),538,9,538,23,"/C:/Users/Klant/workspace2/project23/src/AVLTree.java")) {
						System.out.println("Tree is empty!");
					} else {
						System.out.println("The print of the tree is:");
						tree.print();
					}
					break;
				default:
					System.out.println("Incorrect choice");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("Incorrect choice");
			} catch (AVLTreeException e) {
				System.out.println("e.getMessage()");
				e.printStackTrace();
			}
//		}
	}

public static boolean trace(boolean b, int beginL, int beginC, int endL, int endC, String s){ 
  try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("testtext.txt", true)))) {out.println("" + beginL + ", " + beginC + ", " + endL + ", " + endC  + ", " + s);}catch (IOException ioException) {} 
return b;}
}