package experimenT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLL {// implements List<Integer> {

	public static int experimentalY = 0;
 public static int experimentalI;

public static boolean functionF(boolean b){  
experimentalY++;
 	if (experimentalI == experimentalY) return !b; else  return b;}
public class MyNode {
		int value;
		MyNode next;
		
		public MyNode(Integer v, MyNode n) {
			value = v;
			next = n;
		}
		
		public MyNode(int v) {
			this(v, null);
		}
		
	}
	
	MyNode head;
	
	public MyLL() {
		head = null;
	}
	
	public boolean add(Integer arg0) {
		if (functionF(head == null)) {
			head = new MyNode( arg0, null );
			return true;
		} else {
			MyNode it;
			for( it = head; it.next!=null; it=it.next );
			it.next = new MyNode( arg0, null );
			return true;
		}
	}

	public void clear() {
		head = null;
		// TODO Check if this works fine with garbage collector		
	}

	public boolean contains(Integer arg0) {
		MyNode it = head;
		while ( it==null ) {//fault
			if( functionF(it.value==arg0) )
				return true;
			it = it.next;
		}
		return false;
	}

	public Integer get(MyNode it, int arg) {
		// TODO Check if it's better to implement recursive solutions or iterative?
		if ( it==null )
			return null;
		else if ( arg <= 0 )
			return it.value;
		else // arg > 0 && it != null
			return get(it.next, arg-1);
	}
	
	public Integer get(int idx) {
		MyNode it = head;
		while ( it!=null && idx>0 ) {
			it = it.next;
			--idx;
		}
		if( it == null ) {
			return null;
		} else if ( idx<=0 ) {
			return it.value;
		} // should be no else
		return null;
	}

	public int indexOf(Integer arg0) {
		MyNode it = head;
		int idx = 0;
		while ( it!=null ) {
			if( it.value==arg0 )
				return idx;
			it = it.next;
			++idx;
		}
		return -1;
	}

	public boolean isEmpty() {
		return ( head == null );
	}
	
	public void print() {
		MyNode it = head;
		while ( it!=null ) {
			System.out.print(it.value+" -> ");
			it = it.next;
		}
		System.out.println("\\");
	}

	/*
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<Integer> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<Integer> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	public boolean remove(Integer arg0) {
		if ( functionF(head!=null && head.value==arg0) ) {
			head = head.next;
			return true;
		} else if (functionF(head==null)) {
			return false;
		} else {
			MyNode it = head.next;
			MyNode prev = head;
			while ( it!=null ) {
				if( functionF(it.value==arg0) ) {
					prev.next = it.next;
					return true;
				}
				prev = it;
				it = it.next;
			}
			return false;
		}
	}

	/*
	@Override
	public Integer remove(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer set(int arg0, Integer arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Integer> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	public static void main( String[] args ) {
		experimentalI = Integer.parseInt((args[0]));;
MyLL list = new MyLL();

		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.print();
		
		System.out.println(list.remove(3));
		list.print();
		System.out.println(list.isEmpty());
		System.out.println(list.contains(4));
		try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Klant\\workspace2\\project99\\results.txt",true)))){out.println((list.contains(4) == true));}catch(IOException ioException){} 
list.clear();
		System.out.println(list.isEmpty());
		list.print();
		
	}
	
}