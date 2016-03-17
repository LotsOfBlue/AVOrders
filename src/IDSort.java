import java.util.Comparator;

class IDSort implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getId() - o2.getId();
	}
}