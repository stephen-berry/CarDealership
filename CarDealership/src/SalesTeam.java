import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * The class that deals with the creation and alteration of the sales team and the data along with them
 * Date: April 12, 2019
 * @author Stephen Berry (500886661)
 */
public class SalesTeam {
	LinkedList<String> ll = new LinkedList<String>();
	Map<String, Integer> data = new HashMap<String, Integer>();
	ListIterator<String> li = ll.listIterator();

	public SalesTeam() {

		ll.add("Noah");
		data.put("Noah", 0);
		ll.add("William");
		data.put("William", 0);
		ll.add("Tiago");
		data.put("Tiago", 0);
		ll.add("Stephen");
		data.put("Stephen", 0);
		ll.add("Elon Musk");
		data.put("Elon Musk", 0);
		ll.add("Big Beard Tolaz");
		data.put("Big Beard Tolaz", 0);

	}

	/**
	 * Gets the name of a random sales person
	 * @return a random Sales person as a string
	 */
	public String getName() {
		int rand = (int) (Math.random() * ll.size());
		return ll.get(rand);
	}

	/**
	 * Displays the names of all sales people
	 * @return String containing all names
	 */
	public String displayNames() {
		ListIterator li = ll.listIterator();
		String name = "";
		String allNames = "";
		while (li.hasNext()) {
			name = (String) li.next();
			allNames += name + "\n";
		}
		return allNames;
	}

	/**
	 * Finds the size of the sales team
	 * @return number of people on team
	 */
	public int numOfSalesPeople() {
		return ll.size();
	}

	/**
	 * Gets a certain sales person depending on parameter
	 * @param n index of sales person to get
	 * @return name as a string of the sales person
	 */
	public String getSalesPerson(int n) {
		return ll.get(n);
	}

	/**
	 * Adds a point to a selected sales person using a hashmap of the name of sales person, with their points
	 * @param salesPerson the sales person to add a point to
	 */
	public void addPoint(String salesPerson) {
		if (data.containsKey(salesPerson)) {
			data.replace(salesPerson, data.get(salesPerson) + 1);
		}
	}

	/**
	 * Prints out all the top sales people/person
	 * and checking their values (points)
	 * @param salesPerson the salesperson to print out
	 */
	public void getTopSP(ArrayList<String> salesPerson) {
		System.out.println("And the least likely to be fired for being awesome award goes to!..");
		for(int i = 0; i < salesPerson.size(); i++) {
			System.out.println(salesPerson.get(i) + " " + data.get(salesPerson.get(i)));
		}
	}

	/**
	 * Searches for the top salesperson by searching through the hashmap by checking their values
	 * @return a list of top sales people
	 */
	public ArrayList<String> mostPoints() {
		int mostPoints = Collections.max(data.values());
		ArrayList<String> winner = new ArrayList<String>();
		for (int i = 0; i < data.size(); i++) {
			if (mostPoints == data.get(ll.get(i))) {
				winner.add(ll.get(i));
			}
		}
		return winner;
	}
}
