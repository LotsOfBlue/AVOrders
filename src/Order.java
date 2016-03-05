public class Order {

	private final int id;
	private String name;
	private int phoneNo;
	private String item;
	private int itemNo;
	private int price;
	private String comment;

	public Order(int id, String name, int phoneNo, String item, int itemNo, int price, String comment) {
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.item = item;
		this.itemNo = itemNo;
		this.price = price;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneNo() {
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

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public int getPrice() {
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
}
