package avorders.comparators;

import avorders.Order;

import java.util.Comparator;

/**
 * Compare two Orders by their ID numbers in ascending order.
 * @author Johan Blomberg
 */
public class ReverseIDSort implements Comparator<Order> {

	/**
	 * Sort two orders by their ID number.
	 * @return A positive integer if the second Order has a greater ID than the first,
	 * a negative integer if the first is greater.
	 */
	@Override
	public int compare(Order o1, Order o2) {
		return o2.getId() - o1.getId();
	}
}