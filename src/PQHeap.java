import java.util.*;

public class PQHeap implements PQ{
	
	public static void main(String[] args) {
		
		// Bruges kun til test
		
	}//end of main method
	
	
	PQHeap(int maxElms){
		
	}

	public static int[] replace(int i, int smallest, int[] heap) {
		int aux = heap[i];
		heap[i] = heap[smallest];
		heap[smallest] = aux;
		System.out.printf("i: " + heap[i] + " " + "Smallest: " + heap[smallest]);
		return heap;
	}

	public static int getLeft(int i) {
		return 2 * i;

	}

	public static int getRight(int i) {
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


	@Override
	public Element extractMin() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void insert(Element e) {
		// TODO Auto-generated method stub
		
	}
} // end of class
