public class PrimitiveAndReferenceVariables {
	// DisplayingValues class to hold final static variables for messages
	class DisplayingValues {
		public static final String BEFORE_METHOD_CALL = "Before method call:";
		public static final String AFTER_METHOD_CALL = "After method call:";
		public static final String NUM_MESSAGE = "num = ";
		public static final String ARR_MESSAGE = "arr[0] = ";
	}

	public class PrimitiveReferenceDemo {

		public static void main(String[] args) {
			// Primitive variable (integer)
			int num = 10;

			// Reference variable (array)
			int[] arr = { 1, 2, 3 };

			// Print values before method call
			System.out.println(DisplayingValues.BEFORE_METHOD_CALL);
			System.out.println(DisplayingValues.NUM_MESSAGE + num); // Prints: 10
			System.out.println(DisplayingValues.ARR_MESSAGE + arr[0]); // Prints: 1

			// Pass variables to method
			modifyVariables(num, arr);

			// Print values after method call
			System.out.println("\n" + DisplayingValues.AFTER_METHOD_CALL);
			System.out.println(DisplayingValues.NUM_MESSAGE + num); // Prints: 10 (no change)
			System.out.println(DisplayingValues.ARR_MESSAGE + arr[0]); // Prints: 10 (modified)
		}

		// Method that modifies the variables
		public static void modifyVariables(int num, int[] arr) {
			num = 20; // This change does not affect the original num
			arr[0] = 10; // This change affects the original array
		}
	}

}
