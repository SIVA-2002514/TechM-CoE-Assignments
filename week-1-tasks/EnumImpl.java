public class WeekdayHolidayDemo {

	public static void main(String[] args) {
		// Sample day to check
		DaysOfWeek day = DaysOfWeek.SUNDAY;

		// Check if it's a holiday or not
		checkHoliday(day);
	}

	// Method to check if the given day is a holiday
	public static void checkHoliday(DaysOfWeek day) {
		// If it's Saturday or Sunday, it's a holiday
		if (day == DaysOfWeek.SATURDAY || day == DaysOfWeek.SUNDAY) {
			System.out.println(day + ": Holiday");
		} else {
			System.out.println(day + ": Not Holiday");
		}
	}
}
