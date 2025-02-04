public class DisplayingValues {
	 public static final String FINAL_VARIABLE_MESSAGE = "THE VALUE OF THE FINAL VARIABLE IS : ";
}
   public class FinalClassObserviations {

	 public class FinalKeywordDemo {

		public static void main(String[] args) {
			// Creating an instance of the final class
			FinalClass finalObj = new FinalClass();

			// Using the final variable
			System.out.println(DisplayingValues.FINAL_VARIABLE_MESSAGE);

			// Calling the final method
			finalObj.finalMethod();

			// Attempting to modify the final variable (This will cause a compilation error)
			// finalObj.finalVariable = 200; // Uncommenting this line will cause a
			// compile-time error

			// Attempting to override the final method in a subclass will also cause a
			// compile-time error
			// Uncommenting the following subclass code will cause a compile-time error

			/*
			 * class SubClass extends FinalClass {
			 * 
			 * @Override public void finalMethod() {
			 * System.out.println("Attempt to override final method"); } }
			 */
		}

	}
}
