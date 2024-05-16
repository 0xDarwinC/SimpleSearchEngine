package structures;
import java.io.*;
import java.util.Scanner;

/**
 * Indexer class to create a HashTable of KeyValues
 * 
 * @name Darwin Christopher
 * @date 5/26
 * @period 3
 */
public class Indexer {
	private HashTable table;
	
	public Indexer(String filename) throws IOException, NoMoreTokensException {
		table = new HashTable();
		buildInvertedIndex(filename);
	}
	
	/**
	 * Fills a HashTable of KeyValue objects to act as the storage unit for
	 * 		this InvertedIndex.  The HashTable and KeyValues are built using
	 * 		Strings from the File filename passed by parameter.
	 * @param filename the name of a txt file to be parsed into KeyValue objects
	 * 		and indexed into a HashTable
	 * 
	 * 6 points
	 */
	private void buildInvertedIndex(String filename) 
			throws IOException, NoMoreTokensException{
		/* Complete this method */
		File file = new File(filename);
		Scanner scan = new Scanner(file);
		String line;
		String word;
		String subject = "";
		boolean isFirst;
		KeyValue newWord;
		
		while(scan.hasNextLine()) {
			line = scan.nextLine();
			Tokenizer st = new Tokenizer(": ", line);
			isFirst = true;
			while(st.hasToken()) {
				word = st.nextToken();
				if(!isFirst) {
					if(this.getKeyValue(word) != null) {
						this.getKeyValue(word).addValue(subject);
					}
					else {
						newWord = new KeyValue(word);
						newWord.addValue(subject);
						table.insertKeyValue(this.index(word), newWord);
					}
				}
				else {
					subject = word;
					isFirst = false;
				}	
			}
		}
		scan.close();
	}
	
	/**
	 * Returns the KeyValue object in the HashTable of this Indexer
	 * 		with the key of key. If no such object exists, return null.
	 * @param key the key of a KeyValue object
	 * @return the KeyValue object for which key exist, null if no such
	 * 		object exists in the HashTable of this Indexer.
	 * 
	 * 2 points
	 */
	public KeyValue getKeyValue(String key) {
		/* Complete this method */
		return table.lookUpKey(this.index(key), key);
	}
    
	/**
	 * Determines the index at which String key should be found/located 
	 * 		in HashTable for this Indexer as calculated with the hashCode 
	 * 		method of the KeyValue class and the current size of the 
	 * 		HashTable attribute of this Indexer object.
	 * 
	 * @param key - A String of the key for a KeyValue object
	 * @return a valid index in the HashTable attribute of this Indexer
	 * 		object as calculated with the KeyValue hashCode and the size of
	 * 		the HashTable.
	 * 
	 * 2 points
	 */
	public int index(String key){
		/* Complete this method */
		KeyValue pointer = new KeyValue(key);
		int index = Math.abs(pointer.hashCode())%table.size();
		
	    return index; 
	}
	
    /**
     * @return a String representation of this InvertedIndex...which is being
     * 		stored using a HashTable
     */
    public String toString() {
    	return table.toString();
    }
}
