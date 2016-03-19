import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewOrderController {
	public Label idLabel;

	public TextField nameField;
	public TextField phoneNoField;
	public TextField itemField;
	public TextField itemNoField;
	public TextField priceField;
	public TextArea commentField;

	public Button createButton;
	public Button cancelButton;

	@FXML
	/**
	 * Display the ID the order will be given once created
	 */
	private void initialize() {
		idLabel.setText(idLabel.getText() + OrderUtils.getLatestOrder());
	}

	/**
	 * todo
	 * @param event
	 */
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
		String name = OrderUtils.checkMandatoryField(nameField, "Du måste ange ett namn.");

		//Get the phone number from the text field
		String phoneNo = OrderUtils.checkNumberField(phoneNoField);

		//Get the item name from the text field
		String item = OrderUtils.checkMandatoryField(itemField, "Du måste ange en vara.");

		//Get the item's price from the text field
		String price = OrderUtils.checkNumberField(priceField);

		//If any field contains prompt text, the order can't be created
		for (int i = 0; i < allFields.length - 1; i++) {
			if (!allFields[i].getPromptText().equals("")) {
				clearToContinue = false;
			}
		}

		//If everything's alright, create the order and exit the pane
		if(clearToContinue) {
			new Order(name, phoneNo, item, itemNoField.getText().trim(), price, commentField.getText().trim());

			Main.mainWindowController.exitNewOrder();
		}
	}

	public void cancel(ActionEvent event) {
		//TODO
		System.out.println("Avbröt...");

		Main.mainWindowController.exitNewOrder();
	}
}