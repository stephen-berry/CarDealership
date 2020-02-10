/**
 * Adds onto car. Used for any electric vehicles
 * Date: April 12, 2019
 * @author Stephen Berry (500886661)
 *
 */
class ElectricCar extends Car {
	// Initializing variables
	int rechargeTime;
	String batteryType;

	public ElectricCar(String mfr, String color, int power, int numWheels, String model, int maxRange,
			double safetyRating, boolean AWD, double price, int rechargeTime, String batteryType) {
		super(mfr, color, power, numWheels, model, maxRange, safetyRating, AWD, price);
		this.rechargeTime = rechargeTime;
		this.batteryType = batteryType;
	}

	/**
	 * Gets the recharge time of electric cars
	 * 
	 * @return rechargeTime the time to recharge
	 */
	public int getRechargeTime() {
		return rechargeTime;
	}

	/**
	 * Sets the recharge time of electric car
	 * 
	 * @param rechargeTime new time it takes to recharge
	 */
	public void setRechargeTime(int rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	/**
	 * gets battery type of car
	 * 
	 * @return batteryType type of battery
	 */
	public String getBatteryType() {
		return batteryType;
	}

	/**
	 * changes the type of battery of car
	 * 
	 * @param batteryType type of battery
	 */
	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	/**
	 * Prints out all information of electric car when completed
	 * 
	 * @return string of all information
	 */
	public String display() {
		return super.display() + " EL, BAT: " + batteryType + " RCH: " + rechargeTime;
	}

}