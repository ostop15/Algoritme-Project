import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Encode { 
    public static void main(String[] args) throws Exception {
        
	// Open input and output byte streams to/from files.
	FileInputStream inFile = new FileInputStream(args[0]);
	FileOutputStream outFile = new FileOutputStream(args[1]);

	// Wrap the new bit streams around the input/output streams.
	BitInputStream in = new BitInputStream(inFile);
	BitOutputStream out = new BitOutputStream(outFile);

	// Gets character frequencies
        int bi;
        int c = 0;
        int by = 00000000;
        int[] arr = new int[256];
        while((bi = in.readBit()) != -1)
        {
            by <<=  1;
            by |= bi;
            if(c >= 7)
            {
                arr[by] += 1;
                by = 00000000;
                c = 0;
            }
            else
            {
                c += 1;
            }
        }
        if (c != 0)
        {
            arr[by] += 1;
        }
        
        // Inserts frequencies to PQ
        PQHeap que = new PQHeap(256);
        c = 0;
        int jc;
        for (int i : arr)
        {
            if(i > 0)
            {
                que.insert(new Element(i, new Node(c)));
                for (int h = 0; h < i; h++)
                {
                    jc = c;
                    for (int j = 128; j > 0; j/=2)
                    {
                        if (jc - j >= 0)
                        {
                            out.writeBit(1);
                            jc -= j;
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
            }
            c++;
        }
        
        // Huffmans algorithm
        int length = que.size()-1;
        for (int i = 0; i < length; i++)
        {
            Node x = (Node) que.extractMin().data;
            Node y = (Node) que.extractMin().data;
            Node z = new Node(x.key + y.key); 
            z.leftChild = x;
            z.rightChild = y;
            //System.out.println(x.toString());
            //System.out.println(y.toString());
            que.insert(new Element(x.key + y.key, z));
        }
        
        // Get root
        Node root = (Node) que.extractMin().data;
        //System.out.println(root.toString());
        
	// Write tag for PQ. Tag is \!
	out.writeBit(0);
	out.writeBit(1);
	out.writeBit(0);
	out.writeBit(1);
	out.writeBit(1);
	out.writeBit(1);
	out.writeBit(0);
	out.writeBit(0);
        
	out.writeBit(0);
	out.writeBit(0);
	out.writeBit(1);
	out.writeBit(0);
	out.writeBit(0);
	out.writeBit(0);
	out.writeBit(0);
	out.writeBit(1);
        
	// Read file again and get encoding
        inFile = new FileInputStream(args[0]);
	in = new BitInputStream(inFile);
        c = 0;
        by = 00000000;
        String s;
        while((bi = in.readBit()) != -1)
        {
            by <<=  1;
            by |= bi;
            if(c >= 7)
            {
                s = getBitCodePart(by, root, 2);
                //System.out.print(s);
                String[] sp = s.split("");
                for (String b : sp)
                {
                    out.writeBit(Integer.parseInt(b));
                }
                by = 00000000;
                c = 0;
            }
            else
            {
                c += 1;
            }
        }
        if (c != 0)
        {
            s = getBitCodePart(by, root, 2);
            System.out.print(s);
            String[] sp = s.split("");
            for (String b : sp)
            {
                out.writeBit(Integer.parseInt(b));
            }
        }        
	// Close the streams
	in.close();
	out.close();

    }
    
    private static String getBitCodePart(int key, Node n, int direction)
    {
        int k = key;
        Node x = n;
        String s = "";
        
        if(x == null) 
        {
            return s;
        }
        
        if(x.leftChild != null) 
        {
            s += getBitCodePart(k, x.leftChild, 0);
        }
        
        if(x.rightChild != null) 
        {
            s += getBitCodePart(k, x.rightChild, 1);
        }
        
        if(direction==2 || "".equals(s) && x.key != k)
        {
            return s;
        }
        else
        {
            return direction + s;
        }
    }
}
