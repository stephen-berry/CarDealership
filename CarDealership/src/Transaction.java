import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The class that forms a transaction to a 'receipt' and collects all information involving a transaction
 * Date: April 12, 2019
 * @author Stephen Berry (500886661)
 */
public class Transaction {
	
	int ID;
	Car car;
	Calendar date;
	String salesPerson;
	String transactionType;
	double salesPrice;
	
	public Transaction(int ID, Calendar date, Car car, String salesPerson, String transactionType, double salesPrice) {
		this.ID = ID;
		this.car = car;
		this.salesPerson = salesPerson;
		this.transactionType = transactionType;
		this.salesPrice = salesPrice;
		this.date = date;
	}
	
	/**
	 * Prints out receipt of transaction
	 * @return String of all information in receipt
	 */
	public String display() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		String formatted = sdf.format(date.getTime());
		return "ID: " + Integer.toString(ID) + " " + formatted + " " + transactionType + " SalesPerson: " + salesPerson + " " + car.getClass().getName() + ": " + car.display();
		
	}
	
	/**
	 * Gets the month of the current transactions date
	 * @return month as an int
	 */
	public int getMonth() {
		return date.get(Calendar.MONTH);
	}
	
	/**
	 * Gets the day of the current transaction date
	 * @return day as an int
	 */
	public int getDay() {
		return date.get(Calendar.DAY_OF_MONTH);
	}
	
}
