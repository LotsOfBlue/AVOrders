import java.util.Comparator;

public class CustomerSort implements Comparator<Order> {

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