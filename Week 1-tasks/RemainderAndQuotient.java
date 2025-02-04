import java.util.*;

public class RemainderAndQuotient {

	// Defining a DisplayingValues class with constants
	public static class DisplayingValues {
		public static final String REMAINDER = "REMAINDER FOR THE GIVEN NUMBER IS --";
		public static final String QUOTIENT = "QUOTIENT FOR THE GIVEN NUMBER IS --";
		public static final String DIVIDE_BY_ZERO_ERROR = "ERROR: Division by zero is not allowed."; // setting one error constant
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int divident = sc.nextInt(); // Read dividend
		int divider = sc.nextInt(); // Read divisor
		int quotient = 0;

		// Check for division by zero
		if (divider == 0) {
			System.out.println(DisplayingValues.DIVIDE_BY_ZERO_ERROR); // Use the error constant here
			return;
		}

		// Perform division using subtraction to calculate the quotient and remainder
		while (divident >= divider) { // Make sure divident is larger or equal to the divider
			divident -= divider;
			quotient++;
		}

		// Output the results
		System.out.println(DisplayingValues.REMAINDER + " - " + divident);
		System.out.println(DisplayingValues.QUOTIENT + " - " + quotient);

		sc.close();
	}
}
