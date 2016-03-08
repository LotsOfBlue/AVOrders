import java.util.ArrayList;
import java.util.List;

public abstract class OrderIO {

    private static List<Order> orderList = new ArrayList<>();

    /**
     * Adds an order to the list of orders.
     * @param order Order to add
     */
    public static void addOrder(Order order) {
        orderList.add(order);
    }

    /**
     * Get the list of orders
     * @return The list of all orders
     */
    public static List<Order> getOrders() {
        return orderList;
    }

    /**
     * todo
     */
    public static void loadFromFile() {
        //TODO
    }

    /**
     * todo
     */
    public static void saveToFile() {
        //TODO
    }
}
