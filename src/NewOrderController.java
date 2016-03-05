import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewOrderController {
	public TextField nameField;
	public TextField phoneNoField;
	public TextField itemField;
	public TextField itemNoField;
	public TextField priceField;
	public TextArea commentField;

	public Button createButton;
	public Button cancelButton;

	public void finishCreate(ActionEvent event) {
		System.out.println("Klar med nya ordern!");

		Boolean clearToContinue = true;

		//Get the customer name from the text field
		String name = new String();
		if(nameField.getText().trim().equals("")) {
			nameField.setPromptText("Du måste ange ett namn.");
			clearToContinue = false;
		}
		else {
			name = nameField.getText().trim();
		}

		//Get the phone number from the text field
		Integer phoneNo;
		if(phoneNoField.getText().trim().equals("")) {
			phoneNo = null;
		}
		else {
			phoneNo = Integer.parseInt(phoneNoField.getText().trim());
		}

		//Get the item name from the text field
		String item = new String();
		if(itemField.getText().trim().equals("")) {
			itemField.setPromptText("Du måste ange ett telefonnummer.");
			clearToContinue = false;
		}
		else {
			item = itemField.getText().trim();
		}

		//Get the item's number from the text field
		Integer itemNo;
		if(itemNoField.getText().trim().equals("")) {
			itemNo = null;
		}
		else {
			itemNo = Integer.parseInt(itemNoField.getText().trim());
		}

		//Get the item's price from the text field
		Integer price;
		if(priceField.getText().trim().equals("")) {
			price = null;
		}
		else {
			price = Integer.parseInt((priceField.getText().trim()));
		}

		if(clearToContinue) {
			createOrder(
					name,
					phoneNo,
					item,
					itemNo,
					price,
					commentField.getText().trim());
		}
	}

	private void createOrder(
			String name,
			Integer phoneNo,
			String item,
			Integer itemNo,
			Integer price,
			String comment) {
		new Order(0, name, phoneNo, item, itemNo, price, comment);
		System.out.println(Order.getOrders());
	}

	public void cancel(ActionEvent event) {
		System.out.println("Avbröt...");

		Main.mainWindowController.cancelNewOrder();
	}
}
