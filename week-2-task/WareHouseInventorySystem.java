import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// Custom Exceptions
class OutOfStockException extends Exception {
	public OutOfStockException(String message) {
		super(message);
	}
}

class ProductNotFoundException extends Exception {
	public ProductNotFoundException(String message) {
		super(message);
	}
}

class UserNotFoundException extends Exception {
	public UserNotFoundException(String message) {
		super(message);
	}
}

// Product Class
class Product implements Serializable {
	private String productID;
	private String name;
	private int quantity;
	private Location location;

	public Product(String productID, String name, int quantity, Location location) {
		this.productID = productID;
		this.name = name;
		this.quantity = quantity;
		this.location = location;
	}

	// Getter and Setter Methods
	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return productID + " - " + name + " (Qty: " + quantity + ") at " + location;
	}
}

// Location Class
class Location implements Serializable {
	private int aisle, shelf, bin;

	public Location(int aisle, int shelf, int bin) {
		this.aisle = aisle;
		this.shelf = shelf;
		this.bin = bin;
	}

	// Getter and Setter Methods
	public int getAisle() {
		return aisle;
	}

	public void setAisle(int aisle) {
		this.aisle = aisle;
	}

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}

	public int getBin() {
		return bin;
	}

	public void setBin(int bin) {
		this.bin = bin;
	}

	@Override
	public String toString() {
		return "Aisle " + aisle + ", Shelf " + shelf + ", Bin " + bin;
	}
}

// Order Class
class Order implements Serializable {
	private String orderID;
	private List<String> productIDs;
	private Priority priority;

	public enum Priority {
		STANDARD, EXPEDITED
	}

	public Order(String orderID, List<String> productIDs, Priority priority) {
		this.orderID = orderID;
		this.productIDs = productIDs;
		this.priority = priority;
	}

	// Getter and Setter Methods
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public List<String> getProductIDs() {
		return productIDs;
	}

	public void setProductIDs(List<String> productIDs) {
		this.productIDs = productIDs;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Order ID: " + orderID + " | Priority: " + priority;
	}
}

// Inventory Manager Class
class InventoryManager {
	private ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();
	private PriorityQueue<Order> orderQueue = new PriorityQueue<>(Comparator.comparing(Order::getPriority));
	private ExecutorService executor = Executors.newFixedThreadPool(3); // Multithreading

	// Command: Add product to inventory
	public synchronized void addProduct(Product product) {
		products.put(product.getProductID(), product);
	}

	// Command: Add an order to queue
	public synchronized void addOrder(Order order) {
		orderQueue.add(order);
	}

	// Command: Process orders using multithreading
	public void processOrders() {
		while (!orderQueue.isEmpty()) {
			Order order = orderQueue.poll();
			executor.execute(() -> fulfillOrder(order));
		}
	}

	// Command: Fulfill an order
	private void fulfillOrder(Order order) {
		System.out.println("Processing " + order);
		for (String productID : order.getProductIDs()) {
			Product product = products.get(productID);
			if (product == null) {
				System.out.println(
						"Order Failed: " + order.getOrderID() + " | Reason: Product " + productID + " not found.");
				return;
			}
			if (product.getQuantity() <= 0) {
				System.out.println("Order Failed: " + order.getOrderID() + " | Reason: Product " + productID
						+ " is out of stock.");
				return;
			}
			product.setQuantity(product.getQuantity() - 1);
			System.out.println("Fulfilled: " + productID);
		}
		System.out.println("Order Completed: " + order.getOrderID());
	}

	// Command: Print the final inventory state
	public void printInventory() {
		System.out.println("\nFinal Inventory State:");
		products.values().forEach(System.out::println);
	}

	// Command: Save inventory to file
	public void saveInventory(String filename) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			out.writeObject(products);
		}
	}

	// Command: Load inventory from file
	@SuppressWarnings("unchecked")
	public void loadInventory(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			products = (ConcurrentHashMap<String, Product>) in.readObject();
		}
	}
}

// Main Class
public class WarehouseInventorySystem {
	public static void main(String[] args) {
		InventoryManager inventory = new InventoryManager();

		// Command: Add products to inventory
		inventory.addProduct(new Product("P1", "Laptop", 5, new Location(1, 2, 3)));
		inventory.addProduct(new Product("P2", "Mouse", 10, new Location(1, 1, 1)));

		// Command: Create and add orders
		inventory.addOrder(new Order("O1", Arrays.asList("P1", "P2"), Order.Priority.EXPEDITED));
		inventory.addOrder(new Order("O2", Arrays.asList("P1"), Order.Priority.STANDARD));

		// Command: Process orders
		inventory.processOrders();

		// Command: Save inventory to file
		try {
			inventory.saveInventory("inventory.dat");
		} catch (IOException e) {
			System.out.println("Error saving inventory: " + e.getMessage());
		}

		// Command: Print final inventory state
		inventory.printInventory();
	}
}
