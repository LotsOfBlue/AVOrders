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
		//TODO
		System.out.println("Klar med nya ordern!");

		//Gather all text fields for easy access
		TextField[] allFields = {nameField, phoneNoField, itemField, itemNoField, priceField};

		//Clear all prompts from the text fields
		for (int i = 0; i < allFields.length - 1; i++) {
			allFields[i].setPromptText("");
		}

		Boolean clearToContinue = true;

		//Get the customer name from the text field
		String name = nameField.getText().trim();
		if(name.equals("")) {
			nameField.clear();
			nameField.setPromptText("Du måste ange ett namn.");
			clearToContinue = false;
		}

		//Get the phone number from the text field
		String phoneNo = checkNumberField(phoneNoField);

		//Get the item name from the text field
		String item = itemField.getText().trim();
		if(item.equals("")) {
			itemField.clear();
			itemField.setPromptText("Du måste ange en vara.");
			clearToContinue = false;
		}

		//Get the item's number from the text field
		String itemNo = checkNumberField(itemNoField);

		//Get the item's price from the text field
		String price = checkNumberField(priceField);

		//If any field contains prompt text, the order can't be created
		for (int i = 0; i < allFields.length - 1; i++) {
			if (!allFields[i].getPromptText().equals("")) {
				clearToContinue = false;
			}
		}

		//If everything's alright, create the order and exit the pane
		if(clearToContinue) {
			new Order(0, name, phoneNo, item, itemNo, price, commentField.getText().trim());
			//TODO
			//System.out.println(OrderIO.getOrders());

			Main.mainWindowController.exitNewOrder();
		}
	}

	/**
	 * Checks if the textfield contains a number,
	 * and displays a prompt in it if it doesn't.
	 * @param field The textfield to check
	 * @return The trimmed contents of the field
	 */
	private String checkNumberField(TextField field) {
		String contents = field.getText().trim();
		for (char c : contents.toCharArray()) {
			if (!Character.isDigit(c)) {
				field.clear();
				field.setPromptText("Endast siffror (0-9) tillåtna.");
			}
		}
		return contents;
	}

	public void cancel(ActionEvent event) {
		//TODO
		System.out.println("Avbröt...");

		Main.mainWindowController.exitNewOrder();
	}
}
