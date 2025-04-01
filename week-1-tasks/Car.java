public class DisplayingValues {
public final static String ENGINE_STARTED=" ENGINE HAS STARTED"; // defining constant 
}
//Parent class Car
class Car {
	private String make;
	private String model;
	private int year;

	// Constructor
	public Car(String make, String model, int year) {
		this.make = make;
		this.model = model;
		this.year = year;
	}

	// Getter and Setter for make
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	// Getter and Setter for model
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	// Getter and Setter for year
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// Method to start the engine
	public void startEngine() {
		System.out.println(DisplayingValues.ENGINE_STARTED);
	}
}
