import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods for handling Orders and the editing of such, as well as related I/O.
 * @author Johan Blomberg
 */
abstract class OrderUtils {

    //The file to load from (initialized later)
    private static File savedOrders;

    private static Integer nextOrder = 1;
    private static List<Order> orderList = new ArrayList<>();

	/**
     * Get the ID number of the next order.
     * @return The ID the next order will have
     */
    static Integer getNextOrderID() {
        return nextOrder;
    }

	/**
     * Increment the ID number.
     */
    static void incrementOrderID() {
        nextOrder++;
    }

    /**
     * Adds an order to the list of orders.
     * @param order Order to add
     */
    static void addOrder(Order order) {
        orderList.add(order);
    }

    /**
     * Get the list of orders.
     * @return The list of all orders
     */
    static List<Order> getOrders() {
        return orderList;
    }

	/**
     * Checks if a mandatory text field contains any text, and
     * displays an error message in the field if it's empty or just contains whitespace.
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
     * Get the path of the file used to store the saved Orders.
     */
    private static void initializeFile() {
        File file = null;

        try {
            file = new File("./SavedOrders");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        savedOrders = file;
    }

    /**
     * Load the ID number and all orders from the file.
     */
    static void loadFromFile() {
        initializeFile();

        try (FileInputStream fileIn = new FileInputStream(savedOrders);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            //Read the file and retrieve the Orders and the ID of the next order from it
            nextOrder = (Integer)objectIn.readObject();
            //Read Orders from the file perpetually until EOF is reached
            while (true) {
                addOrder((Order) objectIn.readObject());
            }
        } catch (EOFException e) {
            System.out.println("Laddat klart.");
        }
        catch (IOException e) {
            System.out.println("Fel med IO i inputstream!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the ID number and all orders to the file.
     */
    static void saveToFile() {
        //Serializes all orders to the file
        try (FileOutputStream fileOut = new FileOutputStream(savedOrders);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(nextOrder);
            for (Order o : orderList) {
                objectOut.writeObject(o);
            }
        } catch (IOException e) {
            System.out.println("Fel med IO i outputstream.");
            e.printStackTrace();
        }
    }
}