import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	private static List<Order> allOrders = new ArrayList<>();

	public Order(int id, String name, String phoneNo, String item, String itemNo, String price, String comment) {
		//TODO
		this.id = id;
		//Set parameters from user input
		this.name = name;
		this.phoneNo = phoneNo;
		this.item = item;
		this.itemNo = itemNo;
		this.price = price;
		this.comment = comment;
		//Set creation date
		this.creationDate = LocalDate.now();

		//allOrders.add(this);
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
		return "#" + id + "\t\t\t\t\t       " + creationDate + "\n" + name + "\n" + item;
	}
}
