import javafx.scene.control.TextField;

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
     * Checks if a mandatory text field contains any text.
     * Displays an error message in the field if it's empty or just contains whitespace
     * @param field The textfield to check
     * @param errorMsg The message to display if the field is empty
     * @return The trimmed contents of the field
     */
    public static String checkMandatoryField(TextField field, String errorMsg) {
        //Get the customer name from the text field
        String contents = field.getText().trim();
        if(contents.equals("")) {
            field.clear();
            field.setPromptText(errorMsg);
        }
        return contents;
    }

    /**
     * Checks if the textfield contains a number,
     * and displays an error message in it if it doesn't.
     * @param field The textfield to check
     * @return The trimmed contents of the field
     */
    public static String checkNumberField(TextField field) {
        String contents = field.getText().trim();
        for (char c : contents.toCharArray()) {
            if (!Character.isDigit(c)) {
                field.clear();
                field.setPromptText("Endast siffror (0-9) tillåtna.");
            }
        }
        return contents;
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
