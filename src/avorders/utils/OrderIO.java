package avorders.utils;

import avorders.Order;

import java.io.*;

/**
 * Contains methods related to handling of Order I/O.
 * @author Johan Blomberg
 */
public abstract class OrderIO {
	//The file to load from (initialized later)
	private static File savedOrders;
	
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
    public static void loadFromFile() {
        initializeFile();

        try (FileInputStream fileIn = new FileInputStream(savedOrders);
			 ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            //Read the file and retrieve the Orders and the ID of the next order from it
			OrderUtils.setNextOrderID((Integer)objectIn.readObject());
            //Read Orders from the file perpetually until EOF is reached
            while (true) {
                OrderUtils.addOrder((Order) objectIn.readObject());
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
    public static void saveToFile() {
        //Serializes all orders to the file
        try (FileOutputStream fileOut = new FileOutputStream(savedOrders);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(OrderUtils.getNextOrderID());
            for (Order o : OrderUtils.getOrders()) {
                objectOut.writeObject(o);
            }
        } catch (IOException e) {
            System.out.println("Fel med IO i outputstream.");
            e.printStackTrace();
        }
    }
}
