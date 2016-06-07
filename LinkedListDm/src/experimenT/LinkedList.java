package experimenT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class LinkedList <T> {
	public static int experimentalY = 0;
 public static int experimentalI;

public static boolean functionF(boolean b){  
experimentalY++;
 	if (experimentalI == experimentalY) return !b; else  return b;}
public class ListNode {
		T data;
		ListNode next;
		ListNode previous;
		public ListNode (T data, ListNode next, ListNode previous){
			this.data = data;
			this.next = next;
			this.previous = previous;
		}
	}
	public class Iterator {
		ListNode header;
		ListNode ender;
		ListNode current;
		public Iterator (ListNode header, ListNode ender, ListNode current){
			this.header = header;
			this.ender = ender;
			this.current = current;
		}
		public T next (){
			if (functionF(!(valid()))){return null;}
			else{
				if (functionF(!(valid(current.next)))){return null;} 
			}
			current = current.next;
			return current.data;
		}
		public T previous (){
			if (functionF(!(valid()))){return null;}
			else{
				if (functionF(!(valid(current.previous)))){return null;} 
			}
			current = current.previous;
			return current.data;
		}
		public boolean valid(){
			if (functionF(!(current==header || current==ender))) {return false;}//fault
			return true;//fault
		}
		private boolean valid(ListNode test){
			if (functionF(test==header || test==ender)) {return false;}
			return true;
		}
		public T getData(){
			if (functionF(!(valid()))){return null;}
			return current.data;
		}
		public T pop(){
			T tempdata = current.data;
			current.previous.next=current.next;
			current.next.previous = current.previous;
			current = current.next;
			size--;
			return tempdata;
		}
		public boolean insert(T data){
			ListNode newnode = new ListNode(data,current.next,current);
			current.next = newnode;
			newnode.next.previous = newnode;
			current = current.next;
			size++;
			return true;
		}
	}
	ListNode header;
	ListNode ender;
	int size;
	public LinkedList (){
		this.header = new ListNode(null,this.ender,null);
		this.ender = new ListNode(null,null,this.header);
	}
	public boolean add (T data){
		ListNode newnode = new ListNode(data,ender,ender.previous);
		ender.previous.next = newnode;
		ender.previous = newnode;
		size++;
		return true;
	}
	public boolean remove (T data){
		ListNode testnode = header;
		while (!(testnode==null)){
			if (functionF(!(testnode.data==null) && testnode.data.equals(data))) {
				testnode.previous.next = testnode.next;
				testnode.next.previous = testnode.previous;
				size--;
				return true;}
			testnode = testnode.next;
		}
		return false;
	}
	public Iterator first(){
		Iterator iter = new Iterator(header,ender,header.next);
		return iter;
	}
	public Iterator last(){
		Iterator iter = new Iterator(header,ender,ender.previous);
		return iter;
	}
	public Iterator find(T data){
		ListNode testnode = header;
		while (!(testnode==null)){
			if (functionF(!(testnode.data==null) && testnode.data.equals(data))) {
				Iterator iter = new Iterator(header,ender,testnode);
				return iter;
			}
			testnode = testnode.next;
		}
		return null;
	}
	public static void main(String [] args){
		experimentalI = Integer.parseInt((args[0]));;
LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(3));
		list.add(new Integer(4));
		list.remove(new Integer(2));
		LinkedList<Integer>.Iterator iter = list.find(new Integer(3));
		System.out.println(list.header.next.data);
		System.out.println(list.header.next.next.data);
		System.out.println(list.header.next.next.next.data);
		System.out.println("Size: "+list.size);
		try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace11\\project5\\results.txt",true)))){out.println((iter.getData() == 3));}catch(IOException ioException){} 
System.out.println("Iterator data: "+iter.getData());
		iter.next();
		System.out.println("Iterator next: "+iter.getData());
		iter.previous();
		System.out.println("Iterator previous: "+iter.getData());
		iter.insert(new Integer(5));
		System.out.println("New Size: "+list.size);
	}
}