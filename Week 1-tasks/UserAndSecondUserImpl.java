public class MainProgram {

	public static void main(String[] args) {
		// Creating objects for User and SecondUser
		User user1 = new User("Alice", 30);
		User user2 = new User("Alice", 30);

		SecondUser secondUser1 = new SecondUser("Alice", 30);
		SecondUser secondUser2 = new SecondUser("Alice", 30);

		// Demonstrating the behavior of overridden methods in User class

		// Using toString() method in User class
		System.out.println("User 1 (with overridden toString): " + user1);
		System.out.println("User 2 (with overridden toString): " + user2);

		// Using equals() method to compare User objects
		System.out.println("\nAre User 1 and User 2 equal? " + user1.equals(user2)); // Should return true

		// Demonstrating hashCode() for User objects
		System.out.println("\nUser 1 hash code: " + user1.hashCode());
		System.out.println("User 2 hash code: " + user2.hashCode());

		// Demonstrating behavior of SecondUser (without overridden methods)

		// Using default toString() method (inherited from Object class)
		System.out.println("\nSecondUser 1 (default toString): " + secondUser1);
		System.out.println("SecondUser 2 (default toString): " + secondUser2);

		// Using default equals() method (inherited from Object class)
		System.out.println("\nAre SecondUser 1 and SecondUser 2 equal? " + secondUser1.equals(secondUser2)); // Should
																												// return
																												// false

		// Demonstrating hashCode() for SecondUser objects (default behavior)
		System.out.println("\nSecondUser 1 hash code: " + secondUser1.hashCode());
		System.out.println("SecondUser 2 hash code: " + secondUser2.hashCode());
	}
}
