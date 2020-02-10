import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * The class that deals with adding transactions and organizing sales team,
 * stats and other transactions Date: April 12, 2019
 * 
 * @author Stephen Berry (500886661)
 */
public class AccountingSystem {
	ArrayList<Transaction> transaction = new ArrayList<Transaction>();
	HashMap<Integer, Integer> months = new HashMap<Integer, Integer>();

	/**
	 * Adds the information taken to a new transaction
	 * 
	 * @param date        date of transaction
	 * @param car         car sold/returned
	 * @param salesPerson sales person that sold or returned
	 * @param type        type of transaction (buy or return)
	 * @param salesPrice  price of car
	 * @return String of new transaction
	 */
	public String add(Calendar date, Car car, String salesPerson, String type, double salesPrice) {

		int ID = (int) (Math.random() * 100 + 1);
		Transaction newTransaction = new Transaction(ID, date, car, salesPerson, type, salesPrice);
		transaction.add(newTransaction);
		return newTransaction.display();
	}

	/**
	 * Gets the last transaction in the database
	 * 
	 * @return the transaction object at end of list of transactions
	 */
	public Transaction getLastTransaction() {
		return transaction.get(transaction.size() - 1);
	}

	// ADD OTHER METHODS TO SUPPORT "SALES" MENU OPTIONS

	/**
	 * Displays all sales when called
	 */
	public void displaySales() {
		for (int i = 0; i < transaction.size(); i++) {
			System.out.println(transaction.get(i).display());
		}
	}

	/**
	 * Gets the month of specified transaction
	 * 
	 * @param m specified transaction index
	 * @return the month of said transaction
	 */
	public int getMonth(int m) {
		return transaction.get(m).getMonth();
	}


	/**
	 * Adds all sales together
	 * 
	 * @return sum of all sales
	 */
	public double totalSales() {
		double sales = 0;
		for (int i = 0; i < transaction.size(); i++) {
			if (transaction.get(i).transactionType.equals("BUY")) {
				sales += transaction.get(i).salesPrice;
			}
		}
		return sales;
	}

	/**
	 * Adds up the amount of cars bought
	 * 
	 * @return number of cars bought
	 */
	public int totalBuy() {
		int buys = 0;
		for (int i = 0; i < transaction.size(); i++) {
			if (transaction.get(i).transactionType.equals("BUY")) {
				buys++;
			}
		}
		return buys;
	}

	/**
	 * Adds up the amount of cars returned
	 * 
	 * @return number of cars returned
	 */
	public int totalReturn() {
		int returns = 0;
		for (int i = 0; i < transaction.size(); i++) {
			if (transaction.get(i).transactionType.equals("RET")) {
				returns++;
			}
		}
		return returns;
	}

	/**
	 * Uses a hashmap for months with their corresponding number of transactions to
	 * find the best month
	 * 
	 * @return the month(s) with the most sales and the amount of sales all as a string
	 */
	public String bestMonths() {
		String bestMonths = "";
		int maxValue = Collections.max(months.values());// Gets highest value element
		int bestMonth = 0;
		for (Entry<Integer, Integer> entry : months.entrySet()) {
			if (entry.getValue() == maxValue) {// month with most sales
				bestMonth = entry.getKey();
				bestMonths += new DateFormatSymbols().getMonths()[bestMonth] + " ";
			}
		}
		return bestMonths + " - cars sold - " + maxValue;

	}
	
	/**
	 * Creates a hashmap of the months and assigns a value based on the amount of sales in that month
	 */
	public void createMonthMap() {
		months.clear();
		for (int i = 0; i < transaction.size(); i++) {
			if (transaction.get(i).transactionType.equals("BUY")) {
				if (months.containsKey(transaction.get(i).getMonth())) {// add count
					months.replace(transaction.get(i).getMonth(), months.get(transaction.get(i).getMonth()) + 1);
				} else {// add to hashmap
					months.put(transaction.get(i).getMonth(), 1);
				}												
			}
		}
	}

	/**
	 * Calculates the cars sold in a certain month
	 * 
	 * @param month the month to use for calculations
	 * @return number of cars sold that month
	 */
	public int carsSoldInMonth(int month) {
		int counter = 0;
		for (int i = 0; i < transaction.size(); i++) {
			if (transaction.get(i).transactionType.equals("BUY")) {
				if (transaction.get(i).getMonth() == month) {
					counter++;
				}
			}
		}
		return counter;
	}
}
