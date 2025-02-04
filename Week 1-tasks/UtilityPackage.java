package utility;

public class UtilityClass {

	// Static method to find the length of a given integer
	public static int findLength(int number) {
		// Convert the number to a string and return its length
		return Integer.toString(Math.abs(number)).length(); // Use Math.abs() to handle negative numbers
	}
}
