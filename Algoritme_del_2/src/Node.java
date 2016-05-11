
public class Node {
	int key;
	Node parent;
	Node leftChild;
	Node rightChild;
	
	public Node(int key) {
		this.key = key;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;

	}
}
