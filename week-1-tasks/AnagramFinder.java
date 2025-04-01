import java.util.*;

public class AnagramFinder {

	public static void main(String[] args) {
		// Example usage
		String s = "cbaebabacd";
		String p = "abc";

		List<Integer> result = findAnagrams(s, p);
		System.out.println(result); // Output: [0, 6]
	}

	public static List<Integer> findAnagrams(String s, String p) {
		List<Integer> result = new ArrayList<>();
		if (s == null || p == null || s.length() < p.length()) {
			return result;
		}

		// Frequency map for string p
		int[] pCount = new int[26]; // Since there are 26 lowercase English letters
		for (char c : p.toCharArray()) {
			pCount[c - 'a']++;
		}

		// Frequency map for the current window in string s
		int[] windowCount = new int[26];
		int pLength = p.length();
		int sLength = s.length();

		for (int i = 0; i < sLength; i++) {
			// Add the current character to the window
			windowCount[s.charAt(i) - 'a']++;

			// If the window size exceeds the size of p, remove the leftmost character
			if (i >= pLength) {
				windowCount[s.charAt(i - pLength) - 'a']--;
			}

			// Compare the window with p's frequency map
			if (Arrays.equals(pCount, windowCount)) {
				result.add(i - pLength + 1);
			}
		}

		return result;
	}
}
