package hashCodes;

import java.util.Scanner;
import java.util.Stack;

public class HashCodesResearch {
	static Stack<String> words = new Stack<String>();
	static String hashCodes;

	public static void main(String[] args) {
		// create console for input:
		System.out.println("Type some string of words/characters, then press enter");
		Scanner console = new Scanner(System.in);
		String input = console.nextLine();
		console.close();
		collectWords(input, words);
		convertWords(words, hashCodes);
	}

	/**
	 * This method takes in a string (newWord) and adds it to a stack
	 * 
	 * @param newWord
	 * @return
	 */
	public static void collectWords(String input, Stack<String> words) {
		String[] arr = input.split(" ");
		for (String newWord : arr) {
			System.out.println(words.push(newWord));
		}
	}
	/**
	 * This method grabs the hashcodes of all words in a stack(words) and concats them together.
	 * @param words
	 * @param hashCodes
	 */
	public static void convertWords(Stack<String> words, String hashCodes) {
		while (!words.isEmpty()) {
			Integer code = words.pop().hashCode();
			if (hashCodes != null) {
				hashCodes.concat(code.toString());
			} else {
				hashCodes = code.toString();
			}
		}
		System.out.println(hashCodes);
	}

}
