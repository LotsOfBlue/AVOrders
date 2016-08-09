package avorders;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents an order in the program.
 * Contains all pertinent info such as customer, item, price, etc.
 * @author Johan Blomberg
 */
public class Order implements Serializable{

	private static final long serialVersionUID = -8223321276144713051L;

	private final int id;
	private String name;
	private String phoneNo;
	private String item;
	private String itemNo;
	private String price;
	private String seller;
	private String comment;
	private final LocalDate creationDate;
	private LocalDate orderedDate;
	private LocalDate contactedDate;
	private LocalDate deliveredDate;

	/**
	 * Create a new order, and add it to the list of all Orders
	 * @param name The customer's name
	 * @param phoneNo The customer's phone number
	 * @param item The item(s) to be ordered
	 * @param itemNo The item number of the item(s)
	 * @param price Item's price
	 * @param comment Any extra info the user wants to add
	 */
	public Order(String name, String phoneNo, String item, String itemNo, String price, String seller, String comment) {
		//Assign ID to the order, and increment the ID for the next order
		this.id = OrderUtils.getNextOrderID();
		OrderUtils.incrementOrderID();

		//Set parameters from user input
		this.name = name;
		this.phoneNo = phoneNo;
		this.item = item;
		this.itemNo = itemNo;
		this.price = price;
		this.seller = seller;
		this.comment = comment;

		//Set creation date based on the current date
		this.creationDate = LocalDate.now();

		//Add this order to the list of all orders
		OrderUtils.addOrder(this);
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public LocalDate getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDate orderedDate) {
		this.orderedDate = orderedDate;
	}

	public LocalDate getContactedDate() {
		return contactedDate;
	}

	public void setContactedDate(LocalDate contactedDate) {
		this.contactedDate = contactedDate;
	}

	public LocalDate getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(LocalDate deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	@Override
	public String toString() {
		return "#" + id + "\t\t" + creationDate + "\n" + name + "\n" + item;
	}
}