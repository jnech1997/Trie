import java.util.ArrayList;

public class OneLetterTrie {
	Node root = new Node();
	ArrayList<OneLetterTrie> children = new ArrayList<OneLetterTrie>();
	
	/**Constructor: 
	 * Precondition: this OneWordTrie has exactly one root letter (char a-z)
	 * Ex: this OneLetterTrie can only contain words that start with B
	 *          b
	 *         / \
	 *        i   o
	 *       /     \
	 *      g       b
	 * 
	 *  */
	public OneLetterTrie() {
		
	}
	
	/**Initialize a OneLetterTrie whose root value is @c */
	public OneLetterTrie(Character c) {
		root = new Node(c);
	}

	/**Add Character c to this OneLetterTrie */
	public static void addLetter(Character c, OneLetterTrie t) {
		if (t.root.value == null) {
			t.root.value = c;
		}
		else {
			if (t.root.value != c) {	
				boolean containsChar = false;
				for (OneLetterTrie child : t.children) {
					if (child.root.value == c) {
						containsChar = true;
					}
				}
				if (!containsChar) {
					t.children.add(new OneLetterTrie(c));
				}
			}
			else {
				//System.out.println("You tried to add another start letter");
			}
		}
	}
 	
	/**Add string @word to this OneLetterTrie */
	public static void addWord(String word, OneLetterTrie t) {
		if (word.length() > 0) {
			Character c = word.charAt(0);
			addLetter(c, t);
			try {
				String sub = word.substring(1);
				if (t.children.size() == 0) {
					//add a child to this trie
					OneLetterTrie toAdd = new OneLetterTrie();
					t.children.add(toAdd);
					addWord(sub,toAdd);
				}
				else {
					//go through each of the children
					boolean containsChar = false;
					OneLetterTrie container = null;
					for (OneLetterTrie child : t.children) {
						if (child.root.value == c) {
							container = child;
							containsChar = true;
						}
					}
					if (!containsChar) {
						OneLetterTrie toAdd = new OneLetterTrie();
						t.children.add(toAdd);
						addWord(sub,toAdd);
					}	
					else {
						addWord(sub,container);
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				//System.out.println("tried to access a substring that doesn't exist");
			}
		}
		else {
			//System.out.println("tried adding a zero length word");
		}
	}
	
	/**Get the parent OneLetterTrie of this OneLetterTrie */
	public OneLetterTrie getParent(OneLetterTrie c) {
        // Base case
        for (OneLetterTrie dt : children) {
            if (dt.root == c.root) return this;
        }
        // Recursive case - ask children to look
        for (OneLetterTrie dt : children) {
            OneLetterTrie parent= dt.getParent(c);
            if (parent != null) return parent;
        }
        return null; //Not found
    }
	
	 /*** Return the depth at which Node n occurs in this OneLetterTrie, or -1
     * if n is not in the OneLetterTrie .
     * Note: depthOf(root) is 0.
     * If n is a child of this OneLetterTrie, then depth(n) is 1. etc. */
    public int depthOf(Node n) {
        if (n == root) {return 0;}
        for (OneLetterTrie bt : children) {
        	int d= 1 + bt.depthOf(n);
        	if (d != 0) {return d;}
        }
        return -1;
    }
	
	/**Return the number of levels in this OneLetterTrie */
	public int maxDepth() {
		int maxDepth= 0;
        for (OneLetterTrie child : children) {
            maxDepth = Math.max(maxDepth, 1 + child.maxDepth());
        }
        return maxDepth;
	}
	
	/** Return true if this OneLetterTrie contains the string @w
	 * Pre: w is a word string with length greater than 0 */
	public boolean containsWord(String w) {
		Character c = w.charAt(0);
		boolean rootEquals = (root.value == null) ? false : root.value.equals(c);
		if (w.length() == 1) {
			return (rootEquals);
		}
		if (rootEquals) {
			for (OneLetterTrie child : children) {
				boolean childrenHasWord = child.containsWord(w.substring(1));
				if (childrenHasWord) {return true;}
			}
		}
		return false;
	}
	
	/** MAIN FUNCTION for debugging and printing*/
	public static void main(String[] args) {
		OneLetterTrie w = new OneLetterTrie();
		addWord("capz", w);
		addWord("cat",w);
		addWord("captainz",w);
		boolean nodeHasCat = w.containsWord("cat");
		boolean nodeHasCap = w.containsWord("cap");
		boolean nodeHasCaptain = w.containsWord("captainz");
		System.out.println(nodeHasCat);
		System.out.println(nodeHasCap);
		System.out.println(nodeHasCaptain);
	}
}
