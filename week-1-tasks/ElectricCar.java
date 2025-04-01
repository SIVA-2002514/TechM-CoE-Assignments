public class DisplayingValues {
	public final static String BATTERY_CHARGING=" THE BATTERY IS BEING CHARGED";
	public final static String OVERRIDE_METHOD=" OVERRIDDEN ENGINE START METHOD";
}
//Subclass ElectricCar extends Car
class ElectricCar extends Car {
	private int batteryRange;

	// Constructor
	public ElectricCar(String make, String model, int year, int batteryRange) {
		super(make, model, year);
		this.batteryRange = batteryRange;
	}

	// Getter and Setter for battery range
	public int getBatteryRange() {
		return batteryRange;
	}

	public void setBatteryRange(int batteryRange) {
		this.batteryRange = batteryRange;
	}

	// Method to charge the battery
	public void chargeBattery() {
		System.out.println(DisplayingValues.BATTERY_CHARGING);
	}

	// Override startEngine method to show different behavior for ElectricCar
	@Override
	public void startEngine() {
		System.out.println(DisplayingValues.OVERRIDE_METHOD);
	}
}
