import java.util.*;

public class PQHeap implements PQ {

	public static void main(String[] args) {
		PQHeap pq = new PQHeap(5);
		System.out.println(pq);
		// Bruges kun til test

	}// end of main method
	


	
	public PQHeap(int maxElms) {
		PriorityQueue < Integer >  prq = new PriorityQueue < Integer > (); 
			       
			   // insert values in the queue
			   for ( int i = 1; i  <  10; i++ ){  
			   prq.add (new Integer (i)) ;  
			   }
			      
			   System.out.println ( "Initial priority queue values are: "+ prq);
			      
			   // get the head from the queue
			   Integer head = prq.poll();
			   System.out.println ( "Head of the queue is: "+ head);
			   System.out.println ( "Priority queue values after poll: "+ prq);

			   Integer peek = prq.peek();
			   System.out.println ( "Peek of the queue is: "+ peek);
			      
			   System.out.println ( "Priority queue values after poll: "+ prq);
			   }





	@Override
	public Element extractMin() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void insert(Element e) {
		// TODO Auto-generated method stub

	}
	


	private static int[] replace(int i, int smallest, int[] heap) {
		int aux = heap[i];
		heap[i] = heap[smallest];
		heap[smallest] = aux;
		System.out.printf("i: " + heap[i] + " " + "Smallest: " + heap[smallest]);
		return heap;
	}

	private static int getLeft(int i) {
		return 2 * i;

	}

	private static int getRight(int i) {
		return (2 * i) + 1;

	}

	public void minHeapify(int i, int[] heap) {

		int left = getLeft(i);
		int right = getRight(i);
		int smallest = i;

		if (left <= heap.length && heap[left] < heap[i]) {
			smallest = left;
		} else {
			smallest = i;
		}

		if (right <= heap.length && heap[right] < heap[i]) {
			smallest = right;
		}

		if (smallest != i) {
			replace(i, smallest, heap);
			minHeapify(smallest, heap);
		}
	}

} // end of class
