import java.util.*;
public class Heap 
{
    public static void main(String[] args) 
	{
        int[] arr = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
		//arr = MinHeapify(0, arr, 10);
		//arr = BuildMinHeap(arr);
        arr = HeapSort(arr);
		for (int i=0; i<arr.length; i++)
		{
			System.out.println(arr[i]);
		}
    }

    public static int[] replace( int i, int j, int[] rep)
	{
        int aux = rep[i];
        rep[i] = rep[j];
        rep[j] = aux;
		System.out.println("Replace");
		for (int k=0; k<rep.length; k++)
		{
			System.out.println(rep[k]);
		}
        return rep;
    }

	public static int[] HeapSort(int[] tmp) 
	{
		tmp = BuildMinHeap(tmp);

		System.out.println("Build");
		for (int i=0; i<tmp.length; i++)
		{
			System.out.println(tmp[i]);
		}

		int l = tmp.length;
		for (int i=tmp.length-1; i>0; i--) 
		{
			tmp = replace(0,i,tmp);
			l--;
			tmp = MinHeapify(0,tmp,l);
		}
		return tmp;
	}

	public static int[] BuildMinHeap(int[] tmp)
	{
		for (int i=(int)Math.floor(tmp.length/2)-1; i>-1; i--)
		{
			System.out.println("Build index " + i);
			tmp = MinHeapify(i, tmp, tmp.length);
		}
		return tmp;
	}

	public static int[] MinHeapify(int i, int[] tmp, int heapSize) 
	{
		
		int smallest;
		int left = 2*(i+1)-1;
		System.out.println("left: " + left);
		int right = 2*(i+1)+1-1;
		System.out.println("right: " + right);

		if (left < heapSize && tmp[left] <= tmp[i]) 
		{
		    smallest = left;
		} 
		else 
		{
		    smallest = i;
		}

		if (right < heapSize && tmp[right] <= tmp[smallest]) 
		{
		    smallest = right;
		}

		if (smallest != i) 
		{
		    tmp = replace(i, smallest, tmp);
		    MinHeapify(smallest, tmp, heapSize);
		}

		System.out.println("MinHeap");
		for (int k=0; k<tmp.length; k++)
		{
			System.out.println(tmp[k]);
		}

	return tmp;
    }
} // class

