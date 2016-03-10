/**
 												@authors
  							+----------------------------------------------------+
  							|													 |
  							|	Osman Toplica -        Ostop15@student.sdu.dk	 |
  							|													 |
    						|	Amar Djebbara -        Amdje10@student.sdu.dk	 |
      						|													 |
  							|	Asbjørn Mansa Jensen - Asjen15@student.sdu.dk	 |
     						|													 |
 							+----------------------------------------------------+

 *
 */

import java.util.*;

public class HeapSort {
	private static Scanner sc;

	public static void main(String[] args) {
		sc= new Scanner(System.in);
		ArrayList<Element> tA = new ArrayList<>(); //Temporary input storage
		int count = 0; //Counts inputs
		
		System.out.println("Please input the numbers you want sorted:");
		
		while (sc.hasNextInt()) {
			tA.add(new Element(sc.nextInt(), null));
			count++;
		}
		
		PQHeap Q = new PQHeap(count); //Instantiates PQHeap with count entries
		
		for(int i=0; i<count; i++) {
	    	Q.insert(tA.get(i)); // Inserts inputs 
	    }
		
        Q.printAll();
	}
} // class
