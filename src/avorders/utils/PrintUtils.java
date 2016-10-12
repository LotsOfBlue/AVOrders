package avorders.utils;

import avorders.Order;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

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
		Doc orderDoc = prepareDoc(order);

		PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();
		if (defaultPrinter != null && orderDoc != null) {
			DocPrintJob job = defaultPrinter.createPrintJob();
			try {
				job.print(orderDoc, null);
			} catch (PrintException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Assemble a Doc containing a String representation of the given Order.
	 * @param order The order to use as a base for the String
	 * @return A SimpleDoc containing the Order to be printed
	 */
	private static Doc prepareDoc(Order order) {
		Doc doc;
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

		//Prepare the text to be printed
		String output = buildOrderString(order);
		byte[]outputBytes = null;
		InputStream in = null;

		//Break the string down into bytes
		outputBytes = output.getBytes(Charset.defaultCharset());

		//Make an input stream from the bytes
		if (outputBytes != null) {
			in = new ByteArrayInputStream(outputBytes);
		}

		//If everything works, create a document
		if (in != null) {
			doc = new SimpleDoc(in, flavor, null);
		}
		else {
			doc = null;
		}

		return doc;
	}

	/**
	 * Assemble a string with all relevant information from the given order.
	 * @param order The order that the String will be based on
	 * @return A String containing the order's customer name, phone number,
	 * item, item number, price and seller name
	 */
	private static String buildOrderString(Order order) {
		String orderString;

		String name = order.getName();
		String phoneNo = order.getPhoneNo();
		String item = order.getItem();
		String itemNo = order.getItemNo();
		String price = order.getPrice();
		String seller = order.getSeller();

		orderString = "Namn:   " + name;

		if (phoneNo != null && phoneNo.trim().length() > 0) {
			orderString += "\r\nTelefon:   " + phoneNo;
		}

		orderString += "\r\nVara:   " + item;

		if (itemNo != null && itemNo.trim().length() > 0) {
			orderString += "\r\nArtikelnr:   " + itemNo;
		}

		if (price != null && price.trim().length() > 0) {
			orderString += "\r\nPris:   " + price;
		}

		if (seller != null && seller.trim().length() > 0) {
			orderString += "\r\nSäljare:   " + seller;
		}

		return orderString;
	}
}
