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

public class Node 
{
	int key;
	Node parent;
	Node leftChild;
	Node rightChild;
	
	public Node(int key) 
	{
		this.key = key;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}
        
    @Override
    public String toString() 
    { 
        String lc = "null";
        String rc = "null";
        String p = "null";
        if(this.leftChild != null && this.rightChild != null)
        {
            lc = "" + this.leftChild.key;
            rc = "" + this.rightChild.key;
        }
        else if(this.leftChild != null)
        {
            lc = "" + this.leftChild.key;
        }
        else if(this.rightChild != null)
        {
            rc = "" + this.rightChild.key;
        }
        if(this.parent != null)
        {
            p = "" + this.parent.key;
        }
        return "Key: '" + this.key + "', Children: '" + lc + " & " + rc + "', Parent: '" + p + "'";
    } 
}
