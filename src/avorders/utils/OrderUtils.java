package avorders.utils;

import avorders.Order;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods for handling Orders and the editing of such, as well as related I/O.
 * @author Johan Blomberg
 */
public abstract class OrderUtils {

	private static Integer nextOrderID = 1;
    private static List<Order> orderList = new ArrayList<>();

	/**
     * Get the ID number of the next order.
     * @return The ID the next order will have
     */
    public static Integer getNextOrderID() {
        return nextOrderID;
    }

	/**
	 * Set the ID of the next order.
	 * @param nextOrderID The ID number of the next order
	 */
	static void setNextOrderID(Integer nextOrderID) {
		OrderUtils.nextOrderID = nextOrderID;
	}
	
	/**
     * Increment the ID number.
     */
    public static void incrementOrderID() {
        nextOrderID++;
    }

    /**
     * Adds an order to the list of orders.
     * @param order Order to add
     */
    public static void addOrder(Order order) {
        orderList.add(order);
    }

    /**
     * Get the list of orders.
     * @return The list of all orders
     */
    public static List<Order> getOrders() {
        return orderList;
    }

	/**
     * Checks if a mandatory text field contains any text, and
     * displays an error message in the field if it's empty or just contains whitespace.
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
}