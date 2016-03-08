import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{

	private final int id;
	private String name;
	private Integer phoneNo;
	private String item;
	private Integer itemNo;
	private Integer price;
	private String comment;
	private final LocalDate creationDate;
	private LocalDate orderedDate;
	private LocalDate receivedDate;
	private LocalDate deliveredDate;

	private static List<Order> allOrders = new ArrayList<>();

	public Order(int id, String name, Integer phoneNo, String item, Integer itemNo, Integer price, String comment) {
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

		allOrders.add(this);
	}

	public static List<Order> getOrders() {
		return allOrders;
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

	public Integer getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(int price) {
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

	public LocalDate getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(LocalDate receivedDate) {
		this.receivedDate = receivedDate;
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
