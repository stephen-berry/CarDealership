/**
 * Car class which deals with the construction of a car and what attributes it has
 * Date: April 12, 2019
 * @author Stephen Berry (500886661)
 *
 */
public class Car extends Vehicle implements Comparable<Vehicle> {
	// Initializing variables
	int maxRange;
	double safetyRating;
	boolean AWD;
	double price;
	private Model model;
	private String modelString;

	public enum Model {
		SEDAN, SUV, SPORTS, MINIVAN;
	}

	public Car(String mfr, String color, int power, int numWheels, String model, int maxRange, double safetyRating,
			boolean AWD, double price) {
		super(mfr, color, power, numWheels);
		modelString = model;
		this.model = this.model.valueOf(model);
		this.maxRange = maxRange;
		this.safetyRating = safetyRating;
		this.AWD = AWD;
		this.price = price;
	}

	/**
	 * Prints out all information a car has upon creation
	 * 
	 * @return string of information
	 */
	public String display() {
		return super.display() + " " + model + " Max Range: " + maxRange + " SR: " + safetyRating + " AWD: " + AWD + " Price: $" + price;
	}

	/**
	 * Checks if the cars are equal in AWD and model
	 * 
	 * @param other the car we are comparing to
	 * @return true/false depending on if they are equal or not
	 */
	public boolean equals(Car other) {
		Car c = (Car) other;
		if (super.equals(c)) { // then check if model and AWD are equal
			if (this.AWD == c.AWD && this.model == c.model) { // then check if model and AWD are equal ]
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Compares the price of two objects
	 * 
	 * @param other the object we are comparing to
	 * @return 1/0/-1 whether or not the price is greater, equal or less than the
	 *         price we are comparing
	 */
	public int compareTo(Vehicle other) {
		Car c = (Car) other;
		if (this.price > c.price) {
			return 1;
		} else if (this.price == c.price) {
			return 0;
		} else {
			return -1;
		}
	}
	
	public String getPrice() {
		return Double.toString(this.price);
	}

}