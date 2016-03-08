import java.util.Comparator;

public abstract class ReverseIDSort implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		return o2.getId() - o1.getId();
	}
}