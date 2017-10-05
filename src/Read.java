import java.io.*;
import java.util.ArrayList;
public class Read {

	/**Compare the time for searching through the trie vs. searching
	 * through an arraylist */
	public static void main(String[] args) throws IOException {
		long startTrie = System.nanoTime();
		Trie t = processInput("src/trieInput.txt");
		System.out.println("Time for making the Trie: " + (System.nanoTime() - startTrie));
		long startList = System.nanoTime();
		ArrayList<String> l = processInputList("src/trieInput.txt");
		System.out.println("Time for making the List: " + (System.nanoTime() - startList));
		long startContainsTrie = System.nanoTime();
		boolean containsT = t.containsWord("zymurgy");
		System.out.println("Time for checking contained in Trie: " + (System.nanoTime() - startContainsTrie));
		long startContainsList = System.nanoTime();
		boolean containsL = l.contains("zymurgy");
		System.out.println("Time for checking contained in List: " + (System.nanoTime() - startContainsList));
		System.out.println("The Trie contains" + containsT);
		System.out.println("The ArrayList contains" + containsL);
	}
	
	/**Process the input text file by creating a List of its entries */
	public static ArrayList<String> processInputList(String fileName) {
		String line = null;
		ArrayList<String> wordList = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				wordList.add(line);
			}
			bufferedReader.close();
		}
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file" + fileName);
			
		}
		catch (IOException ex) {
			System.out.println("Error reading file" + fileName);
			ex.printStackTrace();
		}
		return wordList;
	}
	
	/**Process the input text file by creating a trie of its entries*/
	public static Trie processInput(String fileName) {
		Trie trie = new Trie();
		String line = null;
		ArrayList<String> wordList = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				wordList.add(line);
			}
			bufferedReader.close();
			String[] wordListArr = new String[wordList.size()];
			for (int i = 0; i < wordList.size(); i++) {
				wordListArr[i] = wordList.get(i);
			}
			trie.addWords(wordListArr);
		}
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file" + fileName);
			
		}
		catch (IOException ex) {
			System.out.println("Error reading file" + fileName);
			ex.printStackTrace();
		}
		return trie;
	}

}
