package avorders.utils;

import avorders.Order;

import javax.print.*;

/**
 * Contains methods used for printing orders.
 * @author Johan Blomberg
 */
public abstract class PrintUtils {

	/**
	 * Print out the given order using the system's default printer.
	 * @param order The order to print
	 */
	public static void printOrder(Order order) {
		String output = buildOrderString(order);

		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		Doc orderDoc = new SimpleDoc(output.getBytes(), flavor, null);

		PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();
		if (defaultPrinter != null) {
			DocPrintJob job = defaultPrinter.createPrintJob();
			try {
				job.print(orderDoc, null);
			} catch (PrintException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Assemble a string with all relevant information from the given order.
	 * @param order The order that the String will be based on
	 * @return A String containing the order's customer name, phone number,
	 * item, item number, price and seller name
	 */
	private static String buildOrderString(Order order) {
		String orderString;

		orderString = "\nNamn:\t" + order.getName() +
				"\nTelefon:\t" + order.getPhoneNo() +
				"\nVara:\t" + order.getItem() +
				"\nArtikelnummer:\t" + order.getItemNo() +
				"\nPris:\t" + order.getPrice() +
				"\nSäljare:\t" + order.getSeller();

		System.out.println(orderString);
		return orderString;
	}
}
