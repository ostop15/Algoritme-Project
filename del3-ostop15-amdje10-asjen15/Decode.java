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
import java.io.IOException;
import java.util.stream.IntStream;

public class Decode { 
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
		int c2 = 0;
		int by = 0;
		int[] arr = new int[256];
		String code = "";
		int tmp;
		boolean swh = false;
		// Reads bit until the end of the file
		while((bi = in.readBit()) != -1)
		{
			// Finds frequency for 256 values
		    if(c2 < 256)
		    {
		    	// Reads tagbit, if 0 return zero, if 1 run else statement in next iteration
		        if (!swh)
		        {
		            if(bi == 0)
		            {
		                arr[c2] = 0; // add frequency to value
		                c2 += 1;
		            }
		            else
		            {
		                swh = true; // start reading 8bit
		            }
		        }
		        // Gets the frequency in 8bit
		        else
		        {
		            by <<=  1; // Left shift once
		            by |= bi; // adds bit by bitwise or
		            if(c >= 7) // if eight bits have been readen
		            {
		                arr[c2] = by; // add frequency to value
		                by = 0; 
		                c = 0;
		                c2 += 1;
		                swh = false; // return to reading tagbit
		            }
		            else
		            {
		                c += 1;
		            }
		        }
		    }
			// Reads code
		    else
		    {
		        code += String.valueOf(bi);
		    }
		}
		
		// Inserts frequencies to PQ
		PQHeap que = new PQHeap(256);
		c = 0;
		for (int i : arr)
		{
		    if(i > 0)
		    {
		        que.insert(new Element(i, new Node(c))); // Inserts element to PQ with the frequency as key and a node with the value ad key
		    }
		    c++;
		}
		
		// Huffmans algorithm (As explain in textbook)
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
		
		// Gets decoded message and writes it to file args[1]
		String mess = getMessage(code, root, IntStream.of(arr).sum());
		String[] ss = mess.split(" "); // Splits the string to get individual values
		for (String s : ss)
		{
		    try 
		    {
				tmp = Integer.parseInt(s);
				for (int j = 128; j > 0; j/=2) // Writes value to file
				{
				    if (tmp - j >= 0)
				    {
				        out.writeBit(1);
				        tmp -= j;
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
		    catch(NumberFormatException | IOException e) 
		    { 
		    	System.out.println("Error on: " + s);
		    }
		}
		    
		// Close the streams
		in.close();
		out.close();
    }
    
    // Decode message
    private static String getMessage(String code, Node n, int length)
    {
        String k = code;
        String f;
        String s = "";
        int c = 0;
        Node x = n;
        // Follows path from code and if it ends up in a leaf with no children fitting the next bit it adds the original byte to a string. The for loop runs until all code is readen or all frequencies has been used.
        for (int i = 0; i < code.length(); i++)
        {
            if (c >= length)
            {
                break;
            }
            f = String.valueOf(k.charAt(0));
            if(x.leftChild != null && "0".equals(f)) 
            {
                x = x.leftChild;
                k = k.substring(1);
            }
            else if(x.rightChild != null && "1".equals(f)) 
            {
                x = x.rightChild;
                k = k.substring(1);
            }
            else
            {
                s += x.key + " ";
                i -= 1;
                x = n;
                c += 1;
            }
        }
        
        return s;
    }
}
