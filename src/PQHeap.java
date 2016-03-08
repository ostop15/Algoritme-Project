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

public class PQHeap implements PQ {
	
	private ArrayList<Element> array;
	private int maxElms;

	public PQHeap(int maxElms) {
		array = new ArrayList<>();
		this.maxElms = maxElms;
	}
	
	@Override
	public Element extractMin() {
		Element min = array.get(0); // Store the first element
		array.set(0, array.get(array.size()-1)); //Replace the first element with the last
		array.remove(array.size()-1); // Delete the last element
		minHeapify(0, array.size()); // Heapify the array

		return min;
	} // end of extractMin method
	
	@Override
	public void insert(Element e) {	
		if(array.size() == maxElms) { // Cannot add entry if PQ is full
			return;
		}
		array.add(e); // add the element to the back of the array
		int i = array.size()-1; //index of the last element
		int parent = (int)Math.floor((i+1)/2)-1; // index of parent
		// Rearrange the queue, if the new element is smaller than its parents
		while (i > 0 && array.get(parent).key > array.get(i).key ) {				
			replace(i,parent);
			i = parent;
			parent = (int)Math.floor((i+1)/2)-1;
		}
	} // end of insert method
	
	private void sort() {
		buildMinHeap(); 
		int sortSize = array.size(); // sortSize is the part of the queue needed sorting
		for (int i = array.size()-1; i > 0; i--) {
			replace(0, i); // takes the smallest entry and makes it last in the queue
			sortSize--; // one entry sortet, shortens the list needed sorting
			minHeapify(0, sortSize); // entry switched with smallest entry gets heapified
		}
	} // end of sort

	private void buildMinHeap() {
		for (int i = (int)Math.floor(array.size()/2)-1; i > -1; i--) { // heapifies the half floored entries
			minHeapify(i, array.size());
		}
	} // end of buildMinHeap
	
	private void minHeapify(int i, int heapsize) {
		int smallest; // integer for comparison
		int left = 2*(i+1)-1; // gets the left child
		int right = 2*(i+1); // gets the right child

		if (left < heapsize && array.get(left).key < array.get(i).key) { // checks if the left child exist and if it is smaller than the parent
			smallest = left; // if so it is set to the smallest
		} 
		else {
			smallest = i; // if not the parent is set to the smallest
		}

		if (right < heapsize && array.get(right).key < array.get(smallest).key) { // checks if the right child exist and if it is smaller than the result from above
			smallest = right; // if so it is set to the smallest
		}

		if (smallest != i) { // if the smallest is not the same as the parent
			replace(i, smallest); // replace result with parent
			minHeapify(smallest, heapsize); // heapifies new index
		}
	} // end of minHeapify
	
	private void replace(int child, int parent) {
		Element aux = array.get(child); // saves child Element to temporary Element
		array.set(child, array.get(parent)); // sets child Element to parent Element
		array.set(parent, aux); // sets parent Element to temporary Element
	} // end of replace
	
	public void printAll() {
		System.out.println("Sorted:");
		while(array.size()>0) {
			System.out.println(extractMin().key);
		}
	}
} // end of class