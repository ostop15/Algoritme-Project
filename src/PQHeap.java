import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class PQHeap implements PQ, Comparable {

	public static void main(String[] args) {
		PQHeap hq = new PQHeap(5);
		Element e = new Element(5, null);
		Element f = new Element(1, null);
		Element g = new Element(7, null);
		Element h = new Element(33, null);
		Element i = new Element(10, null);
		Element j = new Element(12, null);
		Element k = new Element(-1, null);

		hq.insert(e);
		hq.insert(f);

		for(int in = 0; in<hq.pq.size();in++){
		System.out.println(hq.pq.remove());
		}
		//for(int i = 0; i <5;i++)
		// Bruges kun til test

	}// end of main method
	
	public PriorityQueue<Element> pq;

	
	public PQHeap(int maxElms) {
		this.pq = new PriorityQueue<Element>(maxElms);
	}



	@Override
	public Element extractMin() {
		Element min = pq.remove();
		System.out.println(min.key);
		return min;
		
	}

	
	@Override
	public void insert(Element e) {
		this.pq.add(e);
		
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



	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

} // end of class