import java.util.*;

public class PQHeap implements PQ{
	
	public static void main(String[] args) {
		PQHeap Q = new PQHeap(100);
		Element e = new Element(5, null);
		Element f = new Element(4, null);
		Element g = new Element(3, null);
		Element h = new Element(2, null);
		Element ik = new Element(1, null);

		Q.insert(e);
		Q.insert(f);
		Q.insert(g);
		Q.insert(h);
		Q.insert(ik);

		for(int i = 0; i < Q.size();i++)
		System.out.println(Q.get(i));
		
		Q.insert(new Element(6,null));
		System.out.println("\n\n\n");
		for(int i = 0; i < Q.size();i++)
		System.out.println(Q.get(i));

	}// end of main method
	
	private static ArrayList<Element> array;
	
	public int size(){
		return array.size();
	}
	
	public int get(int i){
		return array.get(i).key;
	}

	public PQHeap(int maxElms) {
		array = new ArrayList<>(maxElms);
	}
	
	
	@Override
	public Element extractMin() {
		Element min = array.get(0); // Store the first element
		array.set(0, array.get(array.size()-1)); //Replace the first element with the last
		array.remove(array.size()-1); // Delete the last element
		minHeapify(0,array.size()-1);
		
		return min;
	}
	

	@Override
	public void insert(Element e) {	
		int i = array.size()-1; //index of the last element
		int parent = (int) Math.floor(i/2);
		array.add(e);
		while ( i>0 && array.get(parent).key < array.get(i).key ){
			replace(i,parent);
			i = i-1;
		}
	}

	public static ArrayList<Element> minHeapify(int i, int heapsize) 
	{
		
		int smallest;
		int left = 2*(i+1)-1;
		int right = 2*(i+1)+1-1;

		if (left < heapsize && array.get(left).key <= array.get(i).key) 
		{
		    smallest = left;
		} 
		else 
		{
		    smallest = i;
		}

		if (right < heapsize && array.get(right).key <= array.get(smallest).key) 
		{
		    smallest = right;
		}

		if (smallest != i) 
		{
		    replace(i, smallest);
		    minHeapify(smallest, heapsize);
		}
		return array;
	}// end of minHeapify
	
	
	public static void replace( int child, int parent)
	{
        Element aux = array.get(child);
		array.set(child, array.get(parent));
		array.set(parent, aux);
        
	} // end of replace

} // end of class