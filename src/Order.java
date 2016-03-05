import java.util.ArrayList;
import java.util.List;

public class Order {

	private final int id;
	private String name;
	private Integer phoneNo;
	private String item;
	private Integer itemNo;
	private Integer price;
	private String comment;

	private static List<Order> allOrders = new ArrayList<>();

	public Order(int id, String name, Integer phoneNo, String item, Integer itemNo, Integer price, String comment) {
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.item = item;
		this.itemNo = itemNo;
		this.price = price;
		this.comment = comment;

		allOrders.add(this);
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

	public static List<Order> getOrders() {
		return allOrders;
	}
}
