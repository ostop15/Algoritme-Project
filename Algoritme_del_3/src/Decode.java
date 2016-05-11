import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Decode { 
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
        int ch = 0;
        int by = 00000000;
        int[] arr = new int[256];
        int track = 0;
        String code = "";
        while((bi = in.readBit()) != -1)
        {
            by <<=  1;
            by |= bi;
            if(c >= 7)
            {
                if(by == 33 && track == 92)
                {
                    arr[track] -= 1;
                    ch -= 1;
                    while((bi = in.readBit()) != -1)
                    {
                        code += String.valueOf(bi);
                    }
                    break;
                }
                track = by;
                arr[by] += 1;
                by = 00000000;
                c = 0;
                ch += 1;
            }
            else
            {
                c += 1;
            }
        }
        // Inserts frequencies to PQ
        PQHeap que = new PQHeap(256);
        c = 0;
        for (int i : arr)
        {
            if(i > 0)
            {
                que.insert(new Element(i, new Node(c)));
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
        //System.out.println(code);
        String mess = getMessage(code, root, ch);
        String[] ss = mess.split(" ");
        int tmp;
        for (String s : ss)
        {
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
        }
        
	// Close the streams
	in.close();
	out.close();

    }
    
    private static String getMessage(String code, Node n, int ch)
    {
        String k = code;
        String f;
        String s = "";
        Node x = n;
        int c = 0;
        for (int i = 0; i < code.length(); i++)
        {
            if(c >= ch)
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
                c += 1;
                i -= 1;
                x = n;
            }
        }
        return s;
    }
}
