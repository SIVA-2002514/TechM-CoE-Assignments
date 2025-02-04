import utility.UtilityClass; // Import the utility package

public class MainProgram {

	public static void main(String[] args) {
		int number = 12345; // Example number

		// Call the static method findLength() directly without creating an object
		int length = UtilityClass.findLength(number);

		// Print the length of the number
		System.out.println("Length of the number " + number + " is: " + length);
	}
}
