import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.IntStream;

public class Decode { 
    public static void main(String[] args) throws Exception {
        
	// Open input and output byte streams to/from files.
	FileInputStream inFile = new FileInputStream(args[0]);
	FileOutputStream outFile = new FileOutputStream(args[1]);

	// Wrap the new bit streams around the input/output streams.
	BitInputStream in = new BitInputStream(inFile);
	BitOutputStream out = new BitOutputStream(outFile);

	// Gets character frequencies
        long tStart = System.currentTimeMillis();
        int bi;
        int c = 0;
        int c2 = 0;
        int by = 0;
        int[] arr = new int[256];
        String code = "";
        int tmp;
        boolean swh = false;
        while((bi = in.readBit()) != -1)
        {
            if(c2 < 256)
            {
                if (!swh)
                {
                    if(bi == 0)
                    {
                        arr[c2] = 0;
                        c2 += 1;
                    }
                    else
                    {
                        swh = true;
                    }
                }
                else
                {
                    by <<=  1;
                    by |= bi;
                    if(c >= 7)
                    {
                        //System.out.println(by);
                        arr[c2] = by;
                        by = 0;
                        c = 0;
                        c2 += 1;
                        swh = false;
                    }
                    else
                    {
                        c += 1;
                    }
                }
            }
            else
            {
                code += String.valueOf(bi);
            }
        }
        System.out.println("Decode: Getting frequencies took " + (System.currentTimeMillis() - tStart) + "ms" );
        
        // Inserts frequencies to PQ
        tStart = System.currentTimeMillis();
        PQHeap que = new PQHeap(256);
        c = 0;
        for (int i : arr)
        {
            //System.out.print(i + " ");
            if(i > 0)
            {
                //System.out.println(c + ": " + i);
                que.insert(new Element(i, new Node(c)));
            }
            c++;
        }
        System.out.println("Decode: Inserting to PQ took " + (System.currentTimeMillis() - tStart) + "ms" );
        
        // Huffmans algorithm
        tStart = System.currentTimeMillis();
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
            //System.out.println(x.toString());
            //System.out.println(y.toString());
            que.insert(new Element(x.key + y.key, z));
        }
        
        // Get root
        Node root = (Node) que.extractMin().data;
        //System.out.println(root.toString());
        System.out.println("Decode: Implementing Huffman took " + (System.currentTimeMillis() - tStart) + "ms" );
        
        //System.out.println(code);
        String mess = getMessage(code, root, IntStream.of(arr).sum());
        //System.out.println(mess);
        String[] ss = mess.split(" ");
        tStart = System.currentTimeMillis();
        for (String s : ss)
        {
            //System.out.println(s);
            try {
            tmp = Integer.parseInt(s);
            for (int j = 128; j > 0; j/=2)
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
            } catch(NumberFormatException | IOException e) { System.out.println("Error on: " + s);}
        }
        System.out.println("Decode: Decoding took " + (System.currentTimeMillis() - tStart) + "ms" );
        
	// Close the streams
	in.close();
	out.close();

    }
    
    private static String getMessage(String code, Node n, int length)
    {
        String k = code;
        String f;
        String s = "";
        int c = 0;
        Node x = n;
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
