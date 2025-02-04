import java.util.*;

public class TrailingZeroesInFactorial {

	public static void main(String[] args) {
		// Input the number
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int n = sc.nextInt();

		// Calculate trailing zeroes in n!
		int result = countTrailingZeroes(n);

		// Output the result
		System.out.println("Trailing zeroes in " + n + "! = " + result);
		sc.close();
	}

	// Method to count trailing zeroes in factorial of n
	public static int countTrailingZeroes(int n) {
		int count = 0;

		// Count the number of factors of 5
		while (n >= 5) {
			n /= 5;
			count += n;
		}

		return count;
	}
}
