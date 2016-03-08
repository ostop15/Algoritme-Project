import java.util.*;

public class HeapSort 
{
  
	private static Scanner sc;

	public static void main(String[] args) 
	{
		PQHeap Q = new PQHeap(5);
		sc= new Scanner(System.in);
		
		System.out.println("Please input numbers to sort:");
		
		while (sc.hasNextInt()) {
	    	Q.insert(new Element(sc.nextInt(), null));
		}

        Sort(Q);

		System.out.println("Sorted:");
		while (Q.size() != 0)
		{
			System.out.println(Q.extractMin().key);
		}
	}

	public static PQHeap Sort(PQHeap Q) 
	{
		Q = BuildMinHeap(Q);
		for (int i = Q.size()+1; i < 0; i--) 
		{
			PQHeap.replace(0,i);
			PQHeap.minHeapify(i, Q.size()+1);
		}
		return Q;
	}

	public static PQHeap BuildMinHeap(PQHeap Q)
	{
		for (int i=(int)Math.floor(Q.size()/2)-1; i>-1; i--)
		{
			PQHeap.minHeapify(i, Q.size()+1);
		}
		return Q;
	}

} // class

