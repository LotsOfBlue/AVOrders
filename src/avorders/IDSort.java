package avorders;

import java.util.Comparator;

/**
 * Compare two Orders by their ID numbers in descending order.
 * @author Johan Blomberg
 */
class IDSort implements Comparator<Order> {

	/**
	 * Sort two orders by their ID number.
	 * @return A positive integer if the first Order has a greater ID than the second,
	 * a negative integer if the second is greater.
	 */
	@Override
	public int compare(Order o1, Order o2) {
		return o1.getId() - o2.getId();
	}
}