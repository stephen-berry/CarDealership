/**
 * Creates a basic vehicle structure
 * Date: April 12, 2019
 * @author Stephen Berry (500886661)
 *
 */
public class Vehicle {
	// Initializing variables
	String mfr;
	String color;
	int power;
	int numWheels;
	int VIN;
	public final int ELECTRIC_MOTOR = 1;
	public final int GAS_ENGINE = 0;
	boolean isElec = false;
	boolean isAWD = false;
	boolean isInRange = false;

	public Vehicle(String mfr, String color, int power, int numWheels) {
		this.mfr = mfr;
		this.color = color;
		this.power = power;
		this.numWheels = numWheels;
		VIN = (int) ((Math.random() * 399) + 100);
	}

	/**
	 * Gets the string of the Manufacturer of the car
	 * 
	 * @return manufacturer
	 */
	public String getManufacturer() {
		return mfr;
	}

	/**
	 * Sets manufacturer string to parameter string
	 * 
	 * @param mfr
	 */
	public void setManufacturer(String mfr) {
		this.mfr = mfr;
	}

	/**
	 * gets color of the car
	 * 
	 * @return color of car
	 */
	public String getColor() {
		return color;
	}

	/**
	 * sets color of the car
	 * 
	 * @param color of new car
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * gets power variable of car
	 * 
	 * @return power (how the car runs)
	 */
	public int getPower() {
		return power;
	}

	/**
	 * sets the type of power of the car to parameter
	 * 
	 * @param power of car
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * gets number of wheels of car
	 * 
	 * @return numWheels of car
	 */
	public int getWheels() {
		return numWheels;
	}

	/**
	 * Sets the number of wheels of the car
	 * 
	 * @param numWheels number of wheels
	 */
	public void setWheels(int numWheels) {
		this.numWheels = numWheels;
	}

	/**
	 * Checks if the objects are of equal value (manufacturere, power and number of
	 * wheels
	 * 
	 * @param other the object of the car we are comparing to
	 * @return true/false depending on if they are equal or not
	 */
	public boolean equals(Object other) {
		Vehicle v = (Vehicle) other;
		if (this.mfr.equals(v.mfr) && this.power == v.power && this.numWheels == v.numWheels) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Prints manufacturer and color of car
	 * 
	 * @return string of what is being printed
	 */
	public String display() {
		return "VIN: " + VIN + " " + mfr + " " + color;
	}

}