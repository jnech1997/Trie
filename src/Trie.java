import java.util.ArrayList;

public class Trie {
	ArrayList<OneLetterTrie> roots = new ArrayList<OneLetterTrie>();

	/** 
	 * Description taken from:
	 * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/trie-keyword-tree/tutorial/
	 * "Tries are an extremely special and useful 
	 * data-structure that are based on the prefix of a string (made of a-z chars). 
	 * They are used to represent the “Retrieval” of data and thus the name Trie. 
	 * into a manageable data structure. 
	 * A Trie is a special data structure used to store strings 
	 * that can be visualized like a graph. It consists of nodes and edges. 
	 * Each OneWordTrie node consists of at max 26 OneWordTrie children and 
	 * edges connect each parent node to its children. These 26 pointers are nothing but 
	 * pointers for each of the 26 letters of the English alphabet."
	 * For example, given the String words cats, 
	 * cap, bar, and bat, the Trie would look like this:
	 *              c  b
	 *             /    \
	 *            a      a
	 *           / \    / \
	 *          t   p  t   r
	 *         /
	 *        s
	 *  Notice that it's made of two OneLetterTrie's: one that contains
	 *  words that start with c, and one that contains with words
	 *  that start with b
	 *  */

	/**Constructor: */
	public Trie() {

	}

	/**Add the words in this string array to this Trie. 
	 * Pre: each words in @words is a string of Characters
	 * a-z */
	public void addWords(String[] words) {
		for (String s : words) {
			OneLetterTrie wordTrie = trieWithLetter(s.charAt(0));
			OneLetterTrie.addWord(s, wordTrie);
		}
	}

	/**Return the OneWordTrie which contains words that
	 * begin with the Character @c if one exists, else
	 * add a new OneWordTrie to this Trie with @c (Characrer a-z)
	 * as its root value and return it*/
	public OneLetterTrie trieWithLetter(Character c) {
		for (OneLetterTrie singleTrie : roots) {
			if (singleTrie.root.value == c) {
				return singleTrie;
			}
		}
		OneLetterTrie toAdd = new OneLetterTrie(c);
		roots.add(toAdd);
		;		return toAdd;
	}

	/**Return true if this Trie already contains 
	 * the word @word. Pre: @word is a string of 
	 * Characters a-z*/
	public boolean trieContainsWord(String word) {
		for (OneLetterTrie singleTrie : roots) {
			if (singleTrie.containsWord(word)) {
				return true;
			}
		}
		return false;
	}

	/** MAIN FUNCTION for debugging and printing*/
	public static void main(String[] args) {
		Trie multiTrie = new Trie();
		String[] wordList = new String[]{"cat", "captain", "coup", "bat", "butter","zaboomafoo"};
		multiTrie.addWords(wordList);
		boolean contains0 = multiTrie.trieContainsWord("c");
		boolean contains1 = multiTrie.trieContainsWord("zaboom");
		boolean contains2 = multiTrie.trieContainsWord("butter");
		System.out.println(contains0);
		System.out.println(contains1);
		System.out.println(contains2);
	}
}
