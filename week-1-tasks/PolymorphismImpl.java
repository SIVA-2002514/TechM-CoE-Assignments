//Main class to test the program
public class PolymorphismImpl {
	public static void main(String[] args) {
		// Create an array of Car objects that contains both Car and ElectricCar
		Car[] cars = new Car[3];

		// Initialize the objects
		cars[0] = new Car("Toyota", "Corolla", 2020);
		cars[1] = new ElectricCar("Tesla", "Model S", 2023, 350);
		cars[2] = new Car("Honda", "Civic", 2019);

		// Loop through the array and call startEngine() for each car
		for (Car car : cars) {
			car.startEngine();

			// If the car is an ElectricCar, call the chargeBattery() method
			if (car instanceof ElectricCar) {
				ElectricCar electricCar = (ElectricCar) car;
				electricCar.chargeBattery();
			}
		}
	}
}
