import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract class OrderUtils {

    private static File savedOrders = new File("src/SavedOrders");

    private static Integer latestOrder = 1;
    private static List<Order> orderList = new ArrayList<>();

	/**
     * Get the ID number of the next order.
     * @return The ID the next order will have
     */
    static Integer getLatestOrder() {
        return latestOrder;
    }

	/**
     * Increment the ID number.
     */
    static void incrementOrder() {
        latestOrder++;
    }

    /**
     * Adds an order to the list of orders.
     * @param order Order to add
     */
    static void addOrder(Order order) {
        orderList.add(order);
    }

    /**
     * Get the list of orders
     * @return The list of all orders
     */
    static List<Order> getOrders() {
        return orderList;
    }

	/**
     * Checks if a mandatory text field contains any text.
     * Displays an error message in the field if it's empty or just contains whitespace
     * @param field The textfield to check
     * @param errorMsg The message to display if the field is empty
     * @return The trimmed contents of the field
     */
    static String checkMandatoryField(TextField field, String errorMsg) {
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
    static String checkNumberField(TextField field) {
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
     * Load the ID number and list of orders from the file.
     */
    static void loadFromFile() {
        //TODO
        try {
            //Create the file if it doesn't exist
            savedOrders = new File(OrderUtils.class.getResource("SavedOrders").toURI());
            savedOrders.createNewFile();

            //Read the file and retrieve the Orders and ID of the latest order from it
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(savedOrders));
            orderList = (ArrayList<Order>)objectIn.readObject();
            latestOrder = (Integer)objectIn.readObject();
            objectIn.close();
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
     * Save the ID number and list of orders to the file.
     */
    static void saveToFile() {
        //TODO
        //Serializes the list of all orders to file
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(savedOrders));
            objectOut.writeObject(orderList);
            objectOut.writeObject(latestOrder);
            objectOut.close();
        } catch (IOException e) {
            System.out.println("Fel med IO i outputstream.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}