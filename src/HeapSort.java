import java.util.*;

public class HeapSort 
{
    public static void main(String[] args) 
	{
        List<Integer> arr = new ArrayList<Integer>();;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please insert numbers to sort:");
		
		while (sc.hasNextInt()) {
	    	arr.add(sc.nextInt());
		}
		//arr = MinHeapify(0, arr, 10);
		//arr = BuildMinHeap(arr);
        arr = Sort(arr);

		System.out.println("Sorted:");
		String str = " | ";
		for (int k=0; k<arr.size(); k++)
		{
			str += arr.get(k) + " | ";
		}
		System.out.println(str);
	}

    public static List<Integer> replace( int i, int j, List<Integer> rep)
	{
        int aux = rep.get(i);
		rep.set(i,rep.get(j));
		rep.set(j,aux);
        return rep;
	}

	public static List<Integer> Sort(List<Integer> tmp) 
	{
		tmp = BuildMinHeap(tmp);
		int l = tmp.size();
		for (int i=tmp.size()-1; i>0; i--) 
		{
			tmp = replace(0,i,tmp);
			l--;
			tmp = MinHeapify(0,tmp,l);
		}
		return tmp;
	}

	public static List<Integer> BuildMinHeap(List<Integer> tmp)
	{
		for (int i=(int)Math.floor(tmp.size()/2)-1; i>-1; i--)
		{
			tmp = MinHeapify(i, tmp, tmp.size());
		}
		return tmp;
	}

	public static List<Integer> MinHeapify(int i, List<Integer> tmp, int heapsize) 
	{
		
		int smallest;
		int left = 2*(i+1)-1;
		int right = 2*(i+1)+1-1;

		if (left < heapsize && tmp.get(left) <= tmp.get(i)) 
		{
		    smallest = left;
		} 
		else 
		{
		    smallest = i;
		}

		if (right < heapsize && tmp.get(right) <= tmp.get(smallest)) 
		{
		    smallest = right;
		}

		if (smallest != i) 
		{
		    tmp = replace(i, smallest, tmp);
		    MinHeapify(smallest, tmp, heapsize);
		}
		return tmp;
	}
} // class

