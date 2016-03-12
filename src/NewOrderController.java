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
			nameField.setPromptText("Du måste ange ett namn.");
			clearToContinue = false;
		}

		//Get the phone number from the text field
		Integer phoneNo = checkNumberField(phoneNoField);

		//Get the item name from the text field
		String item = itemField.getText().trim();
		if(item.equals("")) {
			itemField.setPromptText("Du måste ange en vara.");
			clearToContinue = false;
		}

		//Get the item's number from the text field
		Integer itemNo = checkNumberField(itemNoField);

		//Get the item's price from the text field
		Integer price = checkNumberField(priceField);

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
	 * @param field The textfiled to check
	 * @return null if the field is empty or contains letters,
	 * or an integer if it contains a number
	 */
	private Integer checkNumberField(TextField field) {
		Integer result = null;
		if(!field.getText().trim().equals(""))
			try {
				result = Integer.parseInt(field.getText().trim());
			}
			catch (NumberFormatException e) {
				field.clear();
				field.setPromptText("Endast siffror (0-9) tillåtna.");
			}

		return result;
	}

	public void cancel(ActionEvent event) {
		//TODO
		System.out.println("Avbröt...");

		Main.mainWindowController.exitNewOrder();
	}
}
