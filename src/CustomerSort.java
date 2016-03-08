import java.util.Comparator;

public abstract class CustomerSort implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
	}
}