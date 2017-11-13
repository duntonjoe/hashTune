package hashTune;

import java.util.Stack;

public class Mozart {
	String hashCodes;
	String input;
	public Mozart(String input) {
		this.input = input;
		this.hashCodes = writeMusic();
	}

	/**
	 * This method takes in a string (newWord) and adds it to a stack
	 * 
	 * @param newWord
	 * @return
	 */
	public String writeMusic() {
		String[] arr = input.split(" ");
		Integer code = input.hashCode();
		hashCodes = code.toString();
		for (String newWord : arr) {
			code = newWord.hashCode();
			hashCodes += code.toString();
		}
		hashCodes = hashCodes.substring(1); //remove leading "1"
		return hashCodes;
	}

}