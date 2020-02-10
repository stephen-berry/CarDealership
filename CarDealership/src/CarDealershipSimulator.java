import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class runs the main menu and handles the user inputs Also where the cars for
 * sale are created 
 * Date: April 12, 2019
 * @author Stephen Berry (500886661)
 *
 */
public class CarDealershipSimulator {

	public static void main(String[] args) {
		// Create a CarDealership object
		CarDealership CD = new CarDealership();
		// Then create an (initially empty) array list of type Car
		ArrayList<Car> carAL = new ArrayList<Car>();

		// Then create some new car objects of different types
		// See the cars file for car object details
		// Add the car objects to the array list
		// The ADD command should hand this array list to CarDealership object via the

		// Create a scanner object
		Scanner scan = new Scanner(System.in);
		Car lastCar = null;
		File file = new File("cars.txt");

		// while the scanner has another line

		while (scan.hasNextLine()) {
			try {// checks if user entered appropriate text
					// read the input line
				String s = scan.nextLine();
				// create another scanner object (call it "commandLine" or something) using the
				// input line instead of System.in
				Scanner commandLine = new Scanner(s).useDelimiter("\\s* \\s*");
				// read the next word from the commandLine scanner
				// check if the word (i.e. string) is equal to one of the commands and if so,
				// call the appropriate method via the CarDealership object
				if (s.equals("L")) {// Displays inventory
					CD.displayInventory();
				} else if (s.equals("Q")) {// Quits application
					System.exit(0);
				} else if (s.equals("ADD")) {// Adds all cars to inventory
					Scanner fileScan = new Scanner(file);
					while (fileScan.hasNextLine()) {//Reading file
						Scanner fileScanWord = new Scanner(fileScan.nextLine());
						while (fileScanWord.hasNext()) {
							String mfr = fileScanWord.next();
							String color = fileScanWord.next();
							String model = fileScanWord.next();
							String power = fileScanWord.next();
							if (power.equals("GAS_ENGINE")) {
								power = "0";
							} else {
								power = "1";
							}
							double safetyRating = fileScanWord.nextDouble();
							int maxRange = fileScanWord.nextInt();
							String AWD = fileScanWord.next();
							double price = fileScanWord.nextDouble();
							boolean AllWheelDrive = false;
							boolean ElectricCar = false;
							int numWheels = 4;
							int rechargeTime = 0;
							String batteryType = "";
							if (AWD.equals("2WD")) {
								// 2WD
								AllWheelDrive = false;
							} else {
								// AWD
								AllWheelDrive = true;
							}
							if (fileScanWord.hasNextInt()) {
								ElectricCar = true;
								rechargeTime = fileScanWord.nextInt();
							}
							if (fileScanWord.hasNext()) {
								batteryType = fileScanWord.next();
							}
							if (ElectricCar) {
								// Electric Car
								carAL.add(new ElectricCar(mfr, color, Integer.parseInt(power), numWheels, model,
										maxRange, safetyRating, AllWheelDrive, price, rechargeTime, batteryType));
							} else {
								// Car
								carAL.add(new Car(mfr, color, Integer.parseInt(power), numWheels, model, maxRange,
										safetyRating, AllWheelDrive, price));
							}
							ElectricCar = false;

						}
					}
					CD.addCars(carAL);
				} else if (s.equals("SPR")) {// Sort cars by price
					CD.sortByPrice();
				} else if (s.equals("SMR")) {// Sorts cars by max range
					CD.sortByMaxRange();
				} else if (s.equals("SSR")) {// Sorts cars by safety rating
					CD.sortBySafetyRating();
				} else if (s.equals("FEL")) {// Filters electric cars
					CD.filterByElectric();
				} else if (s.equals("FCL")) {// Clears filters
					CD.filtersClear();
				} else if (s.equals("FAW")) {// Filters AWD cars
					CD.filterByAWD();
				} else if (s.equals("RET")) {
					if (lastCar != null) {
						System.out.println("You have returned: ");
						CD.returnCar(CD.as.getLastTransaction().ID);
						carAL.add(lastCar);
						lastCar = null;
					} else {
						System.out.println("You have no car to return.");
					}
				}else if (commandLine.hasNext()) {
					String action = commandLine.next();
					if (action.equals("BUY")) {// Purchases car selected
						if (commandLine.hasNextInt()) {
							int ID = commandLine.nextInt();
							for (int i = 0; i < carAL.size(); i++) {
								if (carAL.get(i).VIN == ID) {
									lastCar = carAL.get(i);
									carAL.remove(i);
									System.out.println("You have purchased: \n" + CD.buyCar(ID));
								}
							}
						} else {
							Invalid();
						}
						commandLine.close();
					} else if (action.equals("FPR")) {// Filters by price
						double minPrice = commandLine.nextDouble();
						double maxPrice = commandLine.nextDouble();
						commandLine.close();
						CD.filterByPrice(minPrice, maxPrice);
					} else if (action.equals("SALES")) {// All new sales options

						if (commandLine.hasNext()) {
							String nextAction = commandLine.next();

							if (nextAction.equals("TEAM")) {
								System.out.println("Jython Recruits: \n" + CD.st.displayNames());
							} else if (nextAction.equals("TOPSP")) {
								CD.st.getTopSP(CD.st.mostPoints());
							} else if (nextAction.equals("STATS")) {
								CD.as.createMonthMap();
								System.out.println(
										"Total Sales: $" + CD.as.totalSales() + " Total Sold: " + CD.as.totalBuy()
												+ " Average Sales: $" + (int) CD.as.totalSales() / 12
												+ " Total Returned: " + CD.as.totalReturn() + " Best Month: "
												+ CD.as.bestMonths());
							} else {// month
								int month = Integer.parseInt(nextAction); // change if needed back to string
								if (month <= 11 && month >= 0) {
									for (int i = 0; i < CD.as.transaction.size(); i++) {
										if (CD.as.transaction.get(i).getMonth() == month) {
											System.out.println(CD.as.transaction.get(i).display());
										}
									}
								} else {// Not a valid option
									Invalid();
								}
							}
						} else {// Just "SALES"
							CD.as.displaySales();
						}
					}else {
						Invalid();
					}
				} else {
					Invalid();
				}
				commandLine.close();
				//Catch Statements
			} catch (NoSuchElementException nsee) {
				Invalid();
			} catch (NumberFormatException nfe) {
				Invalid();
			} catch (FileNotFoundException fnf) {
				Invalid();
			} catch (NullPointerException npe) {
				Invalid();
			}

		}
	}

	//Prints when a user types something that is not part of the functionality of the program
	public static void Invalid() {
		System.out.println("Invalid option. Please try again.");
	}
}