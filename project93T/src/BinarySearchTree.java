import java.lang.Math;
import java.util.*;



public class BinarySearchTree {
    private Node root;

    public class Node {
        String key;
        int value;
        Node left;
        Node right;
        Node next;
        
        Node(Node copy){
            this.key=copy.key;
            this.value=copy.value;
            this.left=copy.left;
            this.right=copy.right;
        }

        Node(String key, int value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;      
            next=null;
        }

        Node(String key) {
            this(key, Integer.parseInt(key));
        }

        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    public BinarySearchTree() {
        root = null;
    }
    
    public BinarySearchTree(Node root) {
        this.root=root;
    }

    /*
     * the recursion implementation of basic BST operations
     */
          
    public void insert(String key) {
        if(root==null) root= new Node(key);            
        else insert(root, key);
    }

    private void insert(Node cur, String key) {
        if (cur.value>=Integer.parseInt(key)){
            if(cur.left!=null) insert(cur.left, key);
            else  cur.left= new Node(key); 
        }
        else{
            if(cur.right!=null) insert(cur.right, key);
            else  cur.right= new Node(key);             
        }
    }

    public Node search(Node cur, String key) {
        if (cur == null)
            return cur;
        if (cur.key.compareTo(key) > 0)
            return search(cur.left, key);
        else {
            if (cur.key.compareTo(key) < 0)
                return search(cur.right, key);
            else
                return cur;
        }
    }
    
    /*
     * size function
     */
    public int size(){
        return size(root, 0);
    }    
    

    private int size(Node cur, int num) {
        if (cur == null)
            return num;
        return size(cur.left, num) + 1 + size(cur.right, num);
    }
    
    /*
     * maxDepth function
     */
    public int maxDepth(){
        return maxDepth(root, 0);
    }
    
    private int maxDepth(Node cur, int depth) {
        if (cur == null)
            return depth;
        int leftDepth = maxDepth(cur.left, depth + 1);
        int rightDepth = maxDepth(cur.right, depth + 1);
        return leftDepth >= rightDepth ? leftDepth : rightDepth;
    }
    
    /*
     *
     */
     public int maxDepthDifference(){
        if (root==null) return 0;
        else{ 
            int[] depthPair=new int[]{Integer.MAX_VALUE, 0};
            maxDepthDifference(root, 0, depthPair);
            return depthPair[1]-depthPair[0];
        }
     }
     
     private void maxDepthDifference(Node cur, int curDepth, int[] depthPair){
        if(cur==null) return;
        if(cur.right==null && cur.left==null ){
                depthPair[0]=Math.min(depthPair[0], curDepth);
                depthPair[1]=Math.max(depthPair[1], curDepth);                
        }else{
            maxDepthDifference(cur.left, curDepth+1, depthPair);
            maxDepthDifference(cur.right, curDepth+1, depthPair);
        }        
     }
    
    /*
     * minValue function
     */
    public int minValue(){
        return minValue(root, Integer.MAX_VALUE);
    }
    
    private int minValue(Node cur, int value) {
        if (cur == null)
            return value;
        if (cur.value < value) {
            value = cur.value;
        }
        int leftMinValue = minValue(cur.left, value);
        int rightMinValue = minValue(cur.right, value);
        if (leftMinValue < value) {
            value = leftMinValue;
        }
        if (rightMinValue < value) {
            value = rightMinValue;
        }
        return value;
    }
    
    /*
     * Preorder print
     */
    public void printTree(String order){
        switch(order.toLowerCase()){
            case "pre":
                printTreePreorder(root);
                break;
            case "post":
                printTreePostorder(root);
                break;
            default:
                printTreeInorder(root);
                break;            
        }
        System.out.println();
    }
    
    private void printTreeInorder(Node cur){
        if (cur == null){
            return;
        }
        printTreeInorder(cur.left);
        System.out.println(cur);
        printTreeInorder(cur.right);
    }
    
    private void printTreePreorder(Node cur){
        if (cur == null){ return; }
        System.out.println(cur);
        printTreePreorder(cur.left);
        printTreePreorder(cur.right);        
    }
    
    public void printTreeDFS(String order){
        switch(order){
            case "pre":
                printTreePreorder(root);
                break;
            case "post":
                printTreePostorder(root);
                break;
            default:
                printTreeInorder(root);
        }
        System.out.println();
    }
    
    private void printTreePostorder(Node cur){
        if (cur == null){
            return;
        }
        printTreePostorder(cur.left);
        printTreePostorder(cur.right);
        System.out.println(cur);
    }
            
    public void printTreeInLevels(){
        printTreeInLevels(root);
        System.out.println();
    }
    
        private void printTreeInLevels(Node root){
        if(root==null)   return;
        LinkedList<Node> currentLvl=new LinkedList<Node>();
        LinkedList<Node> nextLvl=new LinkedList<Node>();
        nextLvl.add(root);
        while(!nextLvl.isEmpty()){
            currentLvl=nextLvl;
            nextLvl=new LinkedList<Node>();
            while(!currentLvl.isEmpty()){
                Node cur=currentLvl.pop();
                System.out.print(cur+"\t");
                if(cur.left!=null) nextLvl.add(cur.left);
                if(cur.right!=null) nextLvl.add(cur.right);                
            }            
            System.out.println();
        }
    }
    
   
    /*
     * mirror
     */
    public void mirror(){
        mirror(root);
    }
    
        private void mirror(Node cur){
        if (cur==null) return;
        Node tmp=cur.left;
        cur.left=cur.right;
        cur.right=tmp;
        mirror(cur.left);
        mirror(cur.right);        
    }
    
    /*
     * printPaths
     */
    public void printPaths(){
        if(root==null) return;
        HashMap<String, ArrayList<String>> allPaths= new HashMap<String, ArrayList<String>>();
        allPaths.put("", new ArrayList<String>()); //empty parent path for root
        printPaths(root, "", allPaths);
    }
    
        private void printPaths(Node cur, String parentKey,  HashMap<String, ArrayList<String>> allPaths){
        if (cur!=null){
            ArrayList<String> path=allPaths.get(parentKey);
            ArrayList<String> newPath=new ArrayList<String>();
            for(String key: path) newPath.add(key);
            newPath.add(cur.key);
            allPaths.put(cur.key, newPath);
            if (cur.right==null && cur.left==null){
                for(String key: newPath) System.out.print(key);
                System.out.println();    
            }else{    
                printPaths(cur.left, cur.key, allPaths );
                printPaths(cur.right, cur.key, allPaths);
            }
        }
    }
    
    /*
     * For the key values 1...numKeys, how many structurally unique 
       binary search trees are possible that store those keys?
       Strategy: consider that each value could be the root. 
       Recursively find the size of the left and right subtrees. 
     */
    public int countTree(int N){
        if(N<=1) return 1;
        int sum=0;
        for(int i=1;i<=N;i++){
            sum+=countTree(i-1)*countTree(N-i);
        }
        return sum;
    }
    
    /*
     * test if a tree is a bst
     */
    
    public boolean isBST(){
        return isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    
        private boolean isBST(Node node, int max, int min){
        if (node==null) return true;
        if (node.value>max || node.value<min) return false;
        return isBST(node.left, node.value-1, min) && isBST(node.right, max, node.value+1);
    }
    
    /*
     * test if two trees are identical
     */
    public boolean sameTree(Node otherTreeRoot){
        return sameTree(root, otherTreeRoot);
    }
    
        private boolean sameTree(Node one, Node another){
        if (one==null && another==null) return true;
        else{
            if (one!=null&& another!=null){
                if(one.value!=another.value) return false;
                return sameTree(one.left, another.left) && sameTree(one.right, another.right);
            }else return false;
        }
    }
    
      
    /*
     *         """
     *   For the key values 1...numKeys, how many structurally unique 
     *  binary search trees are possible that store those keys?
     *  print all these trees in the inorder
     *  Strategy: consider that each value could be the root. 
     *  Recursively find the size of the left and right subtrees. 
     *  """
     */
    public void printAllBinaryTreesPermutation(int n){
        ArrayList<Node> trees=BuildAllBinaryTreesPermutation( 1, n);
        for(Node t: trees){
            printTreeInLevels(t);
            System.out.println();
        }
    }    
    
    private ArrayList<Node> BuildAllBinaryTreesPermutation(int min, int max){
        ArrayList<Node> ret=new ArrayList<Node>();
        if (min>max){
            ret.add(null);
        }else{
            for(int i=min;i<=max;i++){
                Node root=new Node(String.valueOf(i), i);
                ArrayList<Node> left_trees=BuildAllBinaryTreesPermutation(min, i-1);
                ArrayList<Node> right_trees=BuildAllBinaryTreesPermutation(i+1, max);
                for(Node l: left_trees)
                    for(Node r: right_trees){
                        Node newTree=new Node(root);
                        ret.add(newTree);
                        newTree.left=l;
                        newTree.right=r;
                    }
            }
        }
        return ret;        
    }
    
    public void printPrettyTree(){
        //System.out.println(String.format("%-3d%5d", 12, 3));
     
        HashMap<Node, Integer> spaceAhead=new HashMap<Node, Integer>();
        int[] cumSum=new int[]{0};

        calSpaceAhead(root, spaceAhead, cumSum);   //traverse the tree inorderly to calculate the spaces before each node
        int size=cumSum[0]*2;
        
        //represent a tree using two-dimensional char array
        char[][] prettyTree=new char[size][size];
        for(int i=0;i<size;i++) Arrays.fill(prettyTree[i], ' ');
        
        //populate the char array by travesing the tree by level;
        int row=0;
        //printTree("pre");
        HashMap<Node, Integer> rows=new HashMap<Node, Integer>(); //stores the row position of nodes
        rows.put(root, 0); //root is at 0th row
        while(root!=null){
            Node nextLvl=null, prev=null;
            for(; root!=null; root=root.next){
                if(nextLvl==null) nextLvl=root.left!=null?root.left:root.right;
                int space=spaceAhead.get(root);
                row=rows.get(root);
                putNumberInLine(prettyTree, row, space, root.value);      
                if(root.left!=null){
                    if(prev!=null) prev.next=root.left;
                    prev=root.left;
                    //generating the slashes
                    int r=row+1;
                    for(int i=space-1;i>=spaceAhead.get(root.left)&&i>=0;i--) prettyTree[r++][i]='/';
                    rows.put(root.left, r);
                }
                if(root.right!=null){
                    if(prev!=null) prev.next=root.right;
                    prev=root.right;
                    //generating the slashes
                    int r=row+1;
                    for(int i=space+1;i<=spaceAhead.get(root.right)&&i<size;i++) prettyTree[r++][i]='\\';
                    rows.put(root.right, r);
                }
      
            }
            root=nextLvl;
            //row++;
        }
        
        // //print tree
        //System.out.println(size);
        for(int i=0;i<row+1;i++){
            for(int j=0;j<prettyTree[0].length;j++){
                System.out.print(prettyTree[i][j]);
            }
            System.out.println();
        }
    }
        //traverse inorderly, return the total width of spaces of the tree
        private void calSpaceAhead(Node root, HashMap<Node, Integer> spaceAhead, int[] cumSum){
            if(root==null) return;
            calSpaceAhead(root.left, spaceAhead, cumSum);
            //System.out.println(root.value+" : "+cumSum[0]);
            spaceAhead.put(root, cumSum[0]);
            cumSum[0]+=numberLength(root.value);
            calSpaceAhead(root.right, spaceAhead, cumSum);
        }
        
        private void putNumberInLine(char[][] prettyTree, int row, int startCol, int value){
            String v=String.valueOf(value);
            for(int j=0;j<v.length();j++)  prettyTree[row][j+startCol]=v.charAt(j);
        }

        private int numberLength(int num){
            return String.valueOf(num).length();
        }
        
        //flatten a tree to a linkedlist using inorder travesal
        public Node[] spine(Node cur) {   
            Node[] firstAndLast=new Node[2];
            firstAndLast[0]=firstAndLast[1]=cur;
            Node[] next;
            if(cur.left!=null){
                next=spine(cur.left);   
                next[1].right=cur;
                cur.left=null;
                firstAndLast[0]=next[0];
            }
            if(cur.right!=null){
                next=spine(cur.right);
                cur.right=next[0];
                firstAndLast[1]=next[1];                
            }
            return firstAndLast;
        } 

    
    private BinarySearchTree merge(BinarySearchTree t2){
        root=spine(root)[0];
        t2.root=spine(t2.root)[0];
        return new BinarySearchTree( balanceTree(merge(root, t2.root)) );
    }
    
        //merge sort
        private Node merge(Node l1, Node l2){
            Node head=l2, l2Prev=null;
            while(l1!=null&&l2!=null){
                if(l1.value>=l2.value){
                    l2Prev=l2;
                    l2=l2.right;
                }
                else{
                    if(l2Prev!=null){
                        l2Prev.right=l1;
                        l1=l1.right;   //advance l1
                        l2Prev.right.right=l2;
                        l2Prev=l2Prev.right; //advance l2Prev
                    }else{
                        l2Prev=l1; //advance l2Prev
                        head=l2Prev; //change head
                        l1=l1.right; //advance l1
                        l2Prev.right=l2;
                    }
                }
            }
            if(l2==null&&l1!=null){
                if(l2Prev!=null) l2Prev.right=l1; //copy rest of l1 to l2
                else head=l1;
            }
            return head;
        }
        
        
        private Node balanceTree(Node root){
            ArrayList<Node> nodes=new ArrayList<Node>();
            while(root!=null){ nodes.add(root); root=root.right;}
            return balanceTreeRecursive(nodes);
        }
        
            private Node balanceTreeRecursive(List<Node> nodes){
                int n=nodes.size();
                if(n==0) return null;
                int mid=n/2;
                Node root=nodes.get(mid);
                root.left=balanceTreeRecursive(nodes.subList(0, mid));
                root.right=balanceTreeRecursive(nodes.subList(mid+1, n));
                return root;
            }
    
    public boolean subtree(BinarySearchTree s){
        if(s==null) return true;
        return subtree(root, s.root);
    }
    
        private boolean subtree(Node t1, Node t2){
            if(t2!=null) {System.out.println("t2 is null"); return true;}//fault
            if(t1==null) {System.out.println("t1 is null"); return false;}
            
            if(t1.value==t2.value){ //current node of t1 matches t2's root
                //System.out.println(t1+" " + t2+" "+t1.right+" "+t2.right );
                return subtree(t1.left, t2.left)&&subtree(t1.right, t2.right);
            }   
            //System.out.println(t1+" " + t2 );
            return subtree(t1.left, t2)||subtree(t1.right, t2);
        }
    
    public Node firstCommonAncestor(Node n1, Node n2){
        if(n1==null) return n2;
        if(n2==null) return n1;
        //System.out.println(n1+" "+n2);
        return firstCommonAncestor(root, n1, n2);
    }
    
        private Node firstCommonAncestor(Node cur, Node n1, Node n2){
        if(cur==null || cur.value==n1.value|| cur.value==n2.value) return cur;
        Node l= firstCommonAncestor(cur.left, n1, n2);
        Node r= firstCommonAncestor(cur.right, n1, n2);
        //System.out.println(cur+" "+l+" "+r);
        if(l!=null && r!=null) return cur; 
        return l==null?r:l;
    }
    //***********************
    // main method
    //***********************

    public static void main(String[] args) {
        new BinarySearchTree().mainNonStatic();
    }
    
    public void mainNonStatic() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert("5");
        bst.insert("22");
        bst.insert("3");
        bst.insert("1");
        bst.insert("7");
        bst.insert("20");
        bst.insert("6");
        bst.insert("10");
        //System.out.println(bst.size()+" , "+bst.maxDepth()+" , "+bst.minValue()+" , "+bst.maxDepthDifference());
        //bst.mirror();
        
        //bst.printTreeInLevels();
        //bst.printTree("post");
        //bst.printTree("pre");
        //bst.printPaths();
        
        //System.out.println(bst.countTree(7));
        //bst.printAllBinaryTreesPermutation(4);

        //System.out.print(bst.firstCommonAncestor(new Node("20"), new Node("22") ));
        
        bst.printPrettyTree();
        
        // test subtree()
        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.insert("22");
        bst1.insert("7");
        bst1.insert("20");
        bst1.insert("6");
        bst1.printPrettyTree();
        boolean b0 = (bst.subtree(bst1));
//         System.out.print(bst.subtree(bst1));
        
        // test merge()
        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.insert("8");
        bst2.insert("13");
        bst2.insert("9");
        bst2.insert("2");
        bst2.printPrettyTree();        
        // BinarySearchTree merged=bst.merge(bst2);
        // merged.printTree("");

    }
}