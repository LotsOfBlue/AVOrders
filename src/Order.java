import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents an order in the program.
 * Contains all pertinent info such as customer, item, price, etc.
 * @author Johan Blomberg
 */
public class Order implements Serializable{

	private final int id;
	private String name;
	private String phoneNo;
	private String item;
	private String itemNo;
	private String price;
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
	Order(String name, String phoneNo, String item, String itemNo, String price, String comment) {
		//Assign ID to the order, and increment the ID for the next order
		this.id = OrderUtils.getLatestOrder();
		OrderUtils.incrementOrder();

		//Set parameters from user input
		this.name = name;
		this.phoneNo = phoneNo;
		this.item = item;
		this.itemNo = itemNo;
		this.price = price;
		this.comment = comment;

		//Set creation date based on the current date
		this.creationDate = LocalDate.now();

		//Add this order to the list of all orders
		OrderUtils.addOrder(this);
	}

	Integer getId() {
		return id;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getPhoneNo() {
		return phoneNo;
	}

	void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	String getItem() {
		return item;
	}

	void setItem(String item) {
		this.item = item;
	}

	String getItemNo() {
		return itemNo;
	}

	void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	String getPrice() {
		return price;
	}

	void setPrice(String price) {
		this.price = price;
	}

	String getComment() {
		return comment;
	}

	void setComment(String comment) {
		this.comment = comment;
	}

	LocalDate getCreationDate() {
		return creationDate;
	}

	LocalDate getOrderedDate() {
		return orderedDate;
	}

	void setOrderedDate(LocalDate orderedDate) {
		this.orderedDate = orderedDate;
	}

	LocalDate getContactedDate() {
		return contactedDate;
	}

	void setContactedDate(LocalDate contactedDate) {
		this.contactedDate = contactedDate;
	}

	LocalDate getDeliveredDate() {
		return deliveredDate;
	}

	void setDeliveredDate(LocalDate deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	@Override
	public String toString() {
		return "#" + id + "\t\t" + creationDate + "\n" + name + "\n" + item;
	}
}