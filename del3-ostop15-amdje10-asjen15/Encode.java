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

**/

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Encode 
{ 
    public static void main(String[] args) throws Exception 
    { 
		// Open input and output byte streams to/from files.
		FileInputStream inFile = new FileInputStream(args[0]);
		FileOutputStream outFile = new FileOutputStream(args[1]);

		// Wrap the new bit streams around the input/output streams.
		BitInputStream in = new BitInputStream(inFile);
		BitOutputStream out = new BitOutputStream(outFile);
		
		// Gets character frequencies
	    int bi;
	    int c = 0;
	    int by = 0;
	    int[] arr = new int[256];
	    while((bi = in.readBit()) != -1)
	    {
	        by <<=  1;
	        by |= bi;
	        if(c >= 7)
	        {
	            arr[by] += 1;
	            by = 0;
	            c = 0;
	        }
	        else
	        {
	            c += 1;
	        }
	    }
	    
	    // Inserts frequencies to PQ and save them to a file. The frequency is written in 8 bits but if the frequency is 0 a single bit is written and to let the decoder know if it should read 1 or 8 bit a 1 is saved as 1 bit before 8 bit frequency.
	    PQHeap que = new PQHeap(256);
	    c = 0;
	    int jc;
	    int jcmj;
	    for (int i : arr)
	    {
	        if(i > 0)
	        {
	            que.insert(new Element(i, new Node(c)));
	            
	            jc = i;
	            out.writeBit(1);
	            for (int j = 128; j > 0; j/=2)
	            {
	                jcmj = jc - j;
	                if (jcmj >= 0)
	                {
	                    out.writeBit(1);
	                    jc = jcmj;
	                }
	                else
	                {
	                    out.writeBit(0);
	                }

	                if(j == 1)
	                {
	                    break;
	                }
	            }  
	        }
	        else
	        {
	            out.writeBit(0);
	        }
	        c++;
	    }
	    
	    // Huffmans algorithm
	    Node x;
	    Node y;
	    Node z;
	    int length = que.size()-1;
	    for (int i = 0; i < length; i++)
	    {
	        x = (Node) que.extractMin().data;
	        y = (Node) que.extractMin().data;
	        z = new Node(x.key + y.key); 
	        z.leftChild = x;
	        z.rightChild = y;
	        que.insert(new Element(x.key + y.key, z));
	    }
	    Node root = (Node) que.extractMin().data;
	    
		// Reads file again, gets encoding and writes it to file args[1]
		inFile = new FileInputStream(args[0]);
		in = new BitInputStream(inFile);
	    c = 0;
	    by = 0;
	    String[] codes = getBitCodes(root);
	    while((bi = in.readBit()) != -1)
	    {
	        by <<=  1;
	        by |= bi;
	        if(c >= 7)
	        {
	            for (String b : codes[by].split(""))
	            {
	                out.writeBit(Integer.parseInt(b));
	            }
	            by = 0;
	            c = 0;
	        }
	        else
	        {
	            c += 1;
	        }
	    }
	    
		in.close();
		out.close();
	}
		
	// Gets every possible code combination and returns it in a string array
	private static final String[] arrCode = new String[256];
	private static String[] getBitCodes(Node n)
	{
	    Node x = n;
	    
	    if(x == null) 
	    {
	        return arrCode;
	    }
	    
	    if(x.leftChild != null) 
	    {
	        getBitCodesR(x.leftChild, "0");
	    }
	    
	    if(x.rightChild != null) 
	    {
	        getBitCodesR(x.rightChild, "1");
	    }
	    
	    return arrCode;      
	}
	
	private static String[] getBitCodesR(Node n, String dir)
	{
		Node x = n;
		
		if(x == null) 
		{
		    return arrCode;
		}
		
		if(x.leftChild != null) 
		{
		    getBitCodesR(x.leftChild, dir + "0");
		}
		
		if(x.rightChild != null) 
		{
		    getBitCodesR(x.rightChild, dir + "1");
		}
		
		if (x.leftChild == null && x.rightChild == null)
		{
		    arrCode[x.key] = dir;
		}
	    
		return arrCode;  
	}    
}
