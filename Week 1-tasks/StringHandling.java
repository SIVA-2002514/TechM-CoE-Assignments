public class StringProcessor {

	// Method to reverse the given string
	public String reverseString(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder reversed = new StringBuilder(str);
		return reversed.reverse().toString();
	}

	// Method to count how many times a substring appears in the given text
	public int countOccurrences(String text, String sub) {
		if (text == null || sub == null || sub.isEmpty()) {
			return 0;
		}
		int count = 0;
		int index = 0;

		// Loop to find all occurrences of the substring
		while ((index = text.indexOf(sub, index)) != -1) {
			count++;
			index += sub.length(); // Move the index forward to search for the next occurrence
		}

		return count;
	}

	// Method to split the string by spaces and capitalize each word
	public String splitAndCapitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		String[] words = str.split(" ");
		StringBuilder result = new StringBuilder();

		for (String word : words) {
			if (!word.isEmpty()) {
				// Capitalize the first letter and append the rest of the word
				result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
			}
		}

		// Remove the trailing space and return the result
		return result.toString().trim();
	}

	// Main method for testing the above methods
	public static void main(String[] args) {
		StringProcessor processor = new StringProcessor();

		// Test reverseString method
		String str = "hello";
		System.out.println("Reversed String: " + processor.reverseString(str));

		// Test countOccurrences method
		String text = "hello world, hello everyone";
		String sub = "hello";
		System.out.println("Occurrences of 'hello': " + processor.countOccurrences(text, sub));

		// Test splitAndCapitalize method
		String sentence = "this is a test sentence";
		System.out.println("Capitalized Sentence: " + processor.splitAndCapitalize(sentence));
	}
}
