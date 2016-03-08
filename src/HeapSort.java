/**
 * @author Osman Toplica, Amar Djebbara, Asbjørn Mansa Jensen
 */

import java.util.*;

public class HeapSort {
	private static Scanner sc;

	public static void main(String[] args) {
		PQHeap Q = new PQHeap(5);
		sc= new Scanner(System.in);
		
		System.out.println("Please input 5 numbers to sort:");
		
		while (sc.hasNextInt()) {
	    	Q.insert(new Element(sc.nextInt(), null));
		}
		
        Q.printAll();
	}
} // class
