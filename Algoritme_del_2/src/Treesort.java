/**
 * @author Osman Toplica, Amar Djebbara, Asbjørn Mansa Jensen
 */

import java.util.*;

public class Treesort {
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		DictBinTree tree = new DictBinTree();
		
		while (sc.hasNextInt()) {
			tree.insert(sc.nextInt());
		}
		
		int[] array = new int[tree.size];
		array = tree.orderedTraversal();
		for(int element:array) {
			System.out.println("key: "+element);
		}
	}
} // class
