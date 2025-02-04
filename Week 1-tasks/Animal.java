class DisplayingValues {
	// Final static variable for the dog barking message
	public static final String DOG_BARKING_MESSAGE = "The dog is barking!";
}
//Abstract class Animal
abstract class Animal {

	// Abstract method makeSound() that must be overridden by subclasses
	public abstract void makeSound();
}

//Concrete subclass Dog that overrides the makeSound() method
class Dog extends Animal {

	// Overriding the makeSound() method to print a message for barking
	@Override
	public void makeSound() {
		System.out.println(DisplayingValues.DOG_BARKING_MESSAGE);
	}
}
