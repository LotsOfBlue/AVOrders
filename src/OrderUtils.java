import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class OrderUtils {

    private static File savedOrders = new File("src/SavedOrders");

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
        try {
            //Create the file if it doesn't exist
            savedOrders = new File(OrderUtils.class.getResource("SavedOrders").toURI());
            savedOrders.createNewFile();

            //Read from the file and retrieve the Orders from it
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(savedOrders));

            orderList = (ArrayList<Order>)objectIn.readObject();
        } catch (EOFException e) {
            System.out.println("Läst färdigt filen.");
        }
        catch (IOException e) {
            System.out.println("Fel med IO i inputstream!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * todo
     */
    public static void saveToFile() {
        //TODO
        //Serializes the list of all orders to file
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(savedOrders));
            objectOut.writeObject(orderList);
            objectOut.close();
        } catch (IOException e) {
            System.out.println("Fel med IO i outputstream.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
