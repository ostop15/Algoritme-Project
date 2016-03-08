import java.util.*;

public class HeapSort 
{
    public static void main(String[] args) 
	{
        PQHeap Q = new PQHeap(5);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please input numbers to sort:");
		
		while (sc.hasNextInt()) {
	    	Q.insert(new Element(sc.nextInt(), null));
		}
		//Q = MinHeapify(0, Q, 10);
		//Q = BuildMinHeap(Q);
        Q = Sort(Q);

		System.out.println("Sorted:");
		String str = " | ";
		for (int k=0; k<Q.size(); k++)
		{
			str += Q.get(k)+ " | ";
		}
		System.out.println(str);
	}

	public static PQHeap Sort(PQHeap Q) 
	{
		Q = BuildMinHeap(Q);
		int l = Q.size();
		for (int i=Q.size()-1; i>0; i--) 
		{
			Q.replace(0,i);
			l--;
			Q.minHeapify(i, Q.size()-1);
		}
		return Q;
	}

	public static PQHeap BuildMinHeap(PQHeap Q)
	{
		for (int i=(int)Math.floor(Q.size()/2)-1; i>-1; i--)
		{
			Q.minHeapify(i, Q.size()-1);
		}
		return Q;
	}

} // class

