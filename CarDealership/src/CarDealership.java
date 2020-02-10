import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * The code that handles the sorting, filtering, purchasing and returning of the
 * inventory Date: April 12, 2019
 * 
 * @author Stephen Berry (500886661)
 */
public class CarDealership {
	// Initializing variables
	ArrayList<Car> cars;
	SalesTeam st = new SalesTeam();
	AccountingSystem as = new AccountingSystem();
	double minPrice;
	double maxPrice;
	boolean FEL = false;
	boolean AWD = false;
	boolean FPR = false;
	Car found;
	Car lastCar = null;

	public CarDealership() {
		cars = new ArrayList<Car>();
	}

	/**
	 * adds all the cars to an arraylist
	 * 
	 * @param newCars different arraylist containing the entire inventory of cars
	 */
	public void addCars(ArrayList<Car> newCars) {
		cars.addAll(newCars);
	}

	/**
	 * purchase a vehicle from inventory
	 * 
	 * @param index determines which car is purchased
	 * @return information of car purchased
	 */
	public String buyCar(int V) {
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).VIN == V) {
				found = cars.get(i);
				String ref = cars.get(i).toString();
				cars.remove(i);
				String sp = st.getName();
				st.addPoint(sp);
				Calendar date = new GregorianCalendar(2019, getRandomMonth(), getRandomDay(), 13, 13, 13);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
				String formatDate = sdf.format(date.getTime());
				// add from accounting systems
				return as.add(date, found, sp, "BUY", found.price);
			}
		}
		return null;
	}

	/**
	 * Selects a random number from 0-11 (Jan-Dec)
	 * 
	 * @return an int from 0-11 to represent a random month
	 */
	public int getRandomMonth() {
		return (int) (Math.random() * 11) + 1;
	}

	/**
	 * Selects a random number from 1-25
	 * 
	 * @return an int from 1-25 to represent day of month
	 */
	public int getRandomDay() {
		return (int) (Math.random() * 25) + 1;
	}

	/**
	 * Creates a transaction for returning and adds it to the accounting system
	 * 
	 * @param transaction the ID of the last purchased car
	 */
	public void returnCar(int transaction) {
		if (found != null) {
			Transaction returnTransaction = as.getLastTransaction();
			GregorianCalendar newDate = new GregorianCalendar(2019, getRandomMonth(), getRandomDay());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
			if (returnTransaction.getMonth() == 11) {// December
				newDate = new GregorianCalendar(2019, 11, returnTransaction.getDay() + 2);// Increase date
			} else {
				while (newDate.get(Calendar.MONTH) < returnTransaction.getMonth()) {// Return month is before purchase
																					// month
					newDate.set(2019, getRandomMonth(), getRandomDay());

				}
			}
			String formatDate = sdf.format(newDate.getTime());
			String sp = st.getName();
			cars.add(found);
			System.out.println(as.add(newDate, found, sp, "RET", found.price));
			found = null;
		}
	}

	/**
	 * prints out the available and sorted inventory by whichever means of sorting
	 */
	public void displayInventory() {

		for (int i = 0; i < cars.size(); i++) {

			if (FEL && AWD && FPR) {
				if (cars.get(i).isElec && cars.get(i).isAWD && cars.get(i).isInRange) {// Electric, AWD and price
					System.out.println(cars.get(i).display());
				}
			}

			else if (FEL && AWD && !FPR) {
				if (cars.get(i).isElec && cars.get(i).isAWD && !cars.get(i).isInRange) {// Electric and AWD
					System.out.println(cars.get(i).display());
				}
			}

			else if (FEL && !AWD && FPR) {
				if (cars.get(i).isElec && !cars.get(i).isAWD && cars.get(i).isInRange) {// Electric and price
					System.out.println(cars.get(i).display());
				}
			}

			else if (!FEL && AWD && FPR) {
				if (!cars.get(i).isElec && cars.get(i).isAWD && cars.get(i).isInRange) {// AWD and price
					System.out.println(cars.get(i).display());
				}
			}

			else if (FEL && !AWD && !FPR) {
				if (cars.get(i).isElec && !cars.get(i).isAWD && !cars.get(i).isInRange) {// Electric
					System.out.println(cars.get(i).display());
				}
			}

			else if (!FEL && AWD && !FPR) {
				if (!cars.get(i).isElec && cars.get(i).isAWD && !cars.get(i).isInRange) {// AWD
					System.out.println(cars.get(i).display());
				}
			}

			else if (!FEL && !AWD && FPR) {
				if (!cars.get(i).isElec && !cars.get(i).isAWD && cars.get(i).isInRange) {// Price
					System.out.println(cars.get(i).display());
				}
			}

			else {// No filters
				System.out.println(cars.get(i).display());
			}

		}
	}

	/**
	 * finds all electric cars
	 */
	public void filterByElectric() {
		FEL = true;
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).power == cars.get(i).ELECTRIC_MOTOR) {// if electric
				cars.get(i).isElec = true;// categorized in electric
			}
		}
	}

	/**
	 * finds all AWD cars
	 */
	public void filterByAWD() {
		AWD = true;
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).AWD) {// if AWD
				cars.get(i).isAWD = true;// categorized in AWD
			}
		}
	}

	/**
	 * finds all cars within price range
	 * 
	 * @param minPrice minimum price in range
	 * @param maxPrice maximum price in range
	 */
	public void filterByPrice(double minPrice, double maxPrice) {
		FPR = true;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).isInRange = false;
			if (cars.get(i).price >= minPrice && cars.get(i).price <= maxPrice) {// if in price range
				cars.get(i).isInRange = true;// categorized in price range
			}
		}
	}

	/**
	 * clears all filters
	 */
	public void filtersClear() {
		FPR = false;
		AWD = false;
		FEL = false;
		for (int i = 0; i < cars.size(); i++) {// resets the info on each car
			cars.get(i).isElec = false;
			cars.get(i).isAWD = false;
			cars.get(i).isInRange = false;
		}
	}

	/**
	 * sorts the cars arraylist by the cheapest to most expensive cars
	 */
	public void sortByPrice() {
		Collections.sort(cars);
	}

	/**
	 * sorts arraylist of cars by safety rating using comparator interface
	 */
	public void sortBySafetyRating() {
		class sortSR implements Comparator<Car> {// used to compare safety ratings between cars
			public int compare(Car a, Car b) {
				if (a.safetyRating < b.safetyRating) {
					return 1;
				} else if (a.safetyRating > b.safetyRating) {
					return -1;
				} else {
					return 0;
				}
			}
		}
		Collections.sort(cars, new sortSR());
	}

	/**
	 * sorts arraylist of cars by max range using comparator interface
	 */
	public void sortByMaxRange() {
		class sortMR implements Comparator<Car> {// used to compare maxRange between cars
			public int compare(Car a, Car b) {
				if (a.maxRange < b.maxRange) {
					return 1;
				} else if (a.maxRange > b.maxRange) {
					return -1;
				} else {
					return 0;
				}
			}
		}
		Collections.sort(cars, new sortMR());
	}
}