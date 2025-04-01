import java.util.*;

public class User {
	private String name;
	private int age;

	// All-arguments constructor
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// Overridden toString method to return a string representation of the object
	@Override
	public String toString() {
		return "User{name='" + name + "', age=" + age + "}";
	}

	// Overridden hashCode method to generate a hash code based on name and age
	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}

	// Overridden equals method to compare two User objects based on name and age
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		User user = (User) obj;
		return age == user.age && Objects.equals(name, user.name);
	}
}
