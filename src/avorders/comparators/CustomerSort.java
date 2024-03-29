package avorders.comparators;

import avorders.Order;

import java.util.Comparator;

/**
 * Compares two Orders by sorting them alphabetically after their customer names.
 * @author Johan Blomberg
 */
public class CustomerSort implements Comparator<Order> {

	/**
	 * Compares two orders by comparing the strings of their customer names.
	 * If equal, compare them by their ID instead.
	 * @return The result of a compare() on the two orders' customer names.
	 * If these are equal, return the results of an IDSort instead.
	 */
	@Override
	public int compare(Order o1, Order o2) {
		int n = o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
		//If the names are equal, compare IDs instead
		if(n == 0) {
			return (new IDSort()).compare(o1, o2);
		}
		else {
			return n;
		}
	}
}