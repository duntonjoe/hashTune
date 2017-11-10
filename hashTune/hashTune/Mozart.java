package hashTune;

import java.util.Stack;

public class HashMusic {
	String hashCodes;
	String input;
	public HashMusic(String input) {
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
		Stack<String> words = new Stack<String>();
		String[] arr = input.split(" ");
		Integer code = words.hashCode();
		hashCodes = code.toString();
		for (String newWord : arr) {
			code = newWord.hashCode();
			hashCodes += code.toString();
		}
		return hashCodes;
	}

}