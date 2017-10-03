import java.util.ArrayList;

public class Node {
	Character value;//the letter contained in this Node
	ArrayList<Node> children = new ArrayList<Node>();//all of the children nodes of this node
	
	/** Constructor: create a Node without a value */
	public Node() {
		
	}
	
	/** Constructor: create a Node with Character value @c 
	 * Pre: c is a Character a-z*/
	public Node(Character c) {
		value = c;
	}
}
