import java.util.*;

public class ExceptionHandlingExample {
	public static void main(String args[]) {

		processInput(); //method call statement
	}

	private static void processInput() {
		Scanner sc = new Scanner(System.in); //creation of scanner class
		try {
			@SuppressWarnings("unused")
			int reciprocal = 0;
			System.out.println(DisplayingValues.INPUT_NUMBER);

			int inputNumber = sc.nextInt();
			reciprocal = 1 / inputNumber; //Harmful line of code which leads to cause Exception

			System.out.println(DisplayingValues.RECIPROCAL_OF_A_NUMBER + " is " + "1/" + inputNumber);

		}

		catch (ArithmeticException e) {
			System.out.println(DisplayingValues.PROVIDE_VALID_NUMBER);
		} finally {
			sc.close(); //Scanner class closing
		}
	}
}
