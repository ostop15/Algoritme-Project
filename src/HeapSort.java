import java.util.*;

public class HeapSort 
{
    public static void main(String[] args) 
	{
        List<Element> arr = new ArrayList<Element>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please input numbers to sort:");
		
		while (sc.hasNextInt()) {
	    	arr.add(new Element(sc.nextInt(), null));
		}
		//arr = MinHeapify(0, arr, 10);
		//arr = BuildMinHeap(arr);
        arr = Sort(arr);

		System.out.println("Sorted:");
		String str = " | ";
		for (int k=0; k<arr.size(); k++)
		{
			str += arr.get(k).key + " | ";
		}
		System.out.println(str);
	}

    public static List<Element> replace( int i, int j, List<Element> rep)
	{
        int aux = rep.get(i).key;
        Object aux2 = rep.get(i).data;
		rep.set(i, new Element(rep.get(j).key, rep.get(j).data));
		rep.set(j, new Element(aux, aux2));
        return rep;
	}

	public static List<Element> Sort(List<Element> tmp) 
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

	public static List<Element> BuildMinHeap(List<Element> tmp)
	{
		for (int i=(int)Math.floor(tmp.size()/2)-1; i>-1; i--)
		{
			tmp = MinHeapify(i, tmp, tmp.size());
		}
		return tmp;
	}

	

} // class

