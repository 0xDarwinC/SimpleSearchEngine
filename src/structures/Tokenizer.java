package structures;
import java.util.ArrayList;
/**
 * A String Tokenizer class
 * 
 * @author Darwin Christopher
 * @date 10/12/23
 * @period 3
 */
public class Tokenizer {
	private ArrayList<String> tokens;

    /**
     * Constructor for the Tokenizer object.  A tokenizer takes in a String and
     * 	delimiters and splits the String into tokens based on the demlimiters.
     * The individual tokens should be stored in the tokens arraylist in the 
     * 	order for which they exist in the String passed by parameter.
     * @param delims a String of character for which string should be split
     * @param string the String to be split into tokens
     * 
     * 5 points
     */
	public Tokenizer(String delims, String string){
		/* Complete this method */
		tokens = new ArrayList<String>();
		if(string != null && !string.equals("")) {
			String holder = string.toLowerCase();
			String wordHolder = "";
			int i = 0;
			while(i <= string.length()-1) {
				if(delims.contains(holder.substring(i, i+1))) {
					if(!wordHolder.equals("")) {
						tokens.add(wordHolder);
					}
					wordHolder = "";
					i++;
				}
				else {
					wordHolder+= holder.substring(i, i+1);
					i++;
				}
			}
			if(!wordHolder.equals("")) {
				tokens.add(wordHolder);
			}
		}
    }

    /**
	 * Returns one word at a time from the String that was split
	 * 		into tokens
	 * @return the first token from the tokens list or 
	 * 			null if the list is empty
	 */
    public String nextToken() throws NoMoreTokensException{
    	if(this.hasToken())
    		return tokens.remove(0);
    	throw new NoMoreTokensException();
    }
    
    /**
     * @return true if more tokens exist
     */
    public boolean hasToken() 
    {	return tokens.size() > 0;  }
}

