import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// Custom Exceptions
class BookNotFoundException extends Exception {
	public BookNotFoundException(String message) {
		super(message);
	}
}

class UserNotFoundException extends Exception {
	public UserNotFoundException(String message) {
		super(message);
	}
}

class MaxBooksAllowedException extends Exception {
	public MaxBooksAllowedException(String message) {
		super(message);
	}
}

// Book Class
class Book implements Serializable {
	private String title;
	private String author;
	private String ISBN;
	private boolean isAvailable;

	public Book(String title, String author, String ISBN) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.isAvailable = true; // Default availability
	}

	// Getter and Setter Methods
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	@Override
	public String toString() {
		return "Title: " + title + " | Author: " + author + " | ISBN: " + ISBN + " | Status: "
				+ (isAvailable ? "Available" : "Not Available");
	}
}

// User Class
class User implements Serializable {
	private String name;
	private String userID;
	private List<Book> borrowedBooks = new ArrayList<>();

	public User(String name, String userID) {
		this.name = name;
		this.userID = userID;
	}

	// Getter and Setter Methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void borrowBook(Book book) {
		borrowedBooks.add(book);
	}

	public void returnBook(Book book) {
		borrowedBooks.remove(book);
	}

	@Override
	public String toString() {
		return "User ID: " + userID + " | Name: " + name + " | Borrowed Books: " + borrowedBooks.size();
	}
}

// Library Interface
interface ILibrary {
	void borrowBook(String ISBN, String userID)
			throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException;

	void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException;

	Book searchBook(String title);
}

// Abstract Library System Class
abstract class LibrarySystem implements ILibrary {
	protected ConcurrentHashMap<String, Book> books = new ConcurrentHashMap<>();
	protected ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

	public abstract void addBook(Book book);

	public abstract void addUser(User user);
}

// Library Manager Class
class LibraryManager extends LibrarySystem {
	private ExecutorService executor = Executors.newFixedThreadPool(3); // Multithreading

	@Override
	public void addBook(Book book) {
		books.put(book.getISBN(), book);
	}

	@Override
	public void addUser(User user) {
		users.put(user.getUserID(), user);
	}

	@Override
	public void borrowBook(String ISBN, String userID)
			throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException {
		executor.execute(() -> {
			try {
				if (!users.containsKey(userID))
					throw new UserNotFoundException("User not found.");
				if (!books.containsKey(ISBN))
					throw new BookNotFoundException("Book not found.");

				User user = users.get(userID);
				Book book = books.get(ISBN);

				if (!book.isAvailable())
					throw new MaxBooksAllowedException("Book is already borrowed.");
				if (user.getBorrowedBooks().size() >= 3)
					throw new MaxBooksAllowedException("User has reached max books allowed.");

				book.setAvailable(false);
				user.borrowBook(book);
				System.out.println("SUCCESS: " + user.getName() + " borrowed " + book.getTitle());

			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		});
	}

	@Override
	public void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
		executor.execute(() -> {
			try {
				if (!users.containsKey(userID))
					throw new UserNotFoundException("User not found.");
				if (!books.containsKey(ISBN))
					throw new BookNotFoundException("Book not found.");

				User user = users.get(userID);
				Book book = books.get(ISBN);

				if (!user.getBorrowedBooks().contains(book))
					throw new BookNotFoundException("Book not borrowed by user.");

				book.setAvailable(true);
				user.returnBook(book);
				System.out.println("SUCCESS: " + user.getName() + " returned " + book.getTitle());

			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		});
	}

	@Override
	public Book searchBook(String title) {
		return books.values().stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
	}

	public void saveLibraryData(String filename) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			out.writeObject(books);
			out.writeObject(users);
		}
	}

	public void loadLibraryData(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			books = (ConcurrentHashMap<String, Book>) in.readObject();
			users = (ConcurrentHashMap<String, User>) in.readObject();
		}
	}

	public void shutdown() {
		executor.shutdown();
	}
}

// Main Class
public class LibraryManagement {
	public static void main(String[] args) {
		LibraryManager library = new LibraryManager();

		// Command: Add books
		library.addBook(new Book("Java Programming", "James Gosling", "12345"));
		library.addBook(new Book("Python Basics", "Guido van Rossum", "67890"));

		// Command: Add users
		library.addUser(new User("Alice", "U1"));
		library.addUser(new User("Bob", "U2"));

		// Command: Borrow books
		try {
			library.borrowBook("12345", "U1");
			library.borrowBook("67890", "U2");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		// Command: Return books
		try {
			library.returnBook("12345", "U1");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		// Command: Search book
		Book foundBook = library.searchBook("Python Basics");
		System.out.println("SEARCH RESULT: " + (foundBook != null ? foundBook : "Book not found"));

		// Command: Save library data
		try {
			library.saveLibraryData("library.dat");
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		// Shutdown executor
		library.shutdown();
	}
}
