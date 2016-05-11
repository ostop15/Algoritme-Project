
public class DictBinTree implements Dict {
	Node root;
	int size = 0;
	private int count = 0;
	private int[] array = new int[size];

	
	public DictBinTree(){
		this.root = null;
	}
	
	@Override
	public void insert(int key) {
		Node y = null;
		Node x = root;
		Node z = new Node(key);
		
		while(x != null){
			y = x;
			if (z.key < x.key)
				x = x.leftChild;
			else
				x = x.rightChild;
		}
		z.parent = y;
		if (y == null)
			this.root = z;	// Tree was empty
		else if(z.key < y.key)
			y.leftChild = z;
		else
			y.rightChild = z;
		size++;
	}

	private int[] uOrderedTraversal(Node x, int[] array) {
		if (x != null){
			uOrderedTraversal(x.leftChild, array);

			array[count] = x.key;
			count++;
			
			uOrderedTraversal(x.rightChild, array);	
		}
		return array;
	}
	
	@Override
	public int[] orderedTraversal() {
		int[] array = new int[size];
		return uOrderedTraversal(root, array);
	}

	@Override
	public boolean search(int k) {
		Node x = this.root;
		Node y;
		Node z;
		boolean keyNotFound = true;
		
		while(keyNotFound) {
			if(x == null) {
				return false;
			}
			//System.out.println("This is current node: " + x.key);
			y = x.leftChild;
			z = x.rightChild;
			if(x.key == k) {
				return true;
			}
			else if (k < x.key) {
				x = x.leftChild;
			}
			else {
				x = x.rightChild;
			}
		}
		return false;
	}

} // end of DictBinTree class
