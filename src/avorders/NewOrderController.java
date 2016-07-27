package avorders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for newOrder.fxml
 * Lets the user create a new Order.
 * @author Johan Blomberg
 */
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

	/**
	 * Display the ID the order will be given once created
	 */
	@FXML
	private void initialize() {
		idLabel.setText(idLabel.getText() + OrderUtils.getNextOrderID());
	}

	/**
	 * Called when the user clicks the "create" button.
	 * Checks if the textfields contain legal values,
	 * and displays error messages if they don't.
	 * If there are no errors, creates a new Order object.
	 * @param event
	 */
	public void finishCreate(ActionEvent event) {
		//Gather all text fields for easy access
		TextField[] allFields = {nameField, phoneNoField, itemField, itemNoField, priceField};

		//Clear all prompts from the text fields
		for (TextField field : allFields) {
			field.setPromptText("");
		}

		Boolean clearToContinue = true;

		//Get the customer name from the text field
		String name = OrderUtils.checkMandatoryField(nameField, "Du måste ange ett namn.");

		//Get the item name from the text field
		String item = OrderUtils.checkMandatoryField(itemField, "Du måste ange en vara.");

		//If any field contains prompt text, the order can't be created
		for (int i = 0; i < allFields.length - 1; i++) {
			if (!allFields[i].getPromptText().equals("")) {
				clearToContinue = false;
			}
		}

		//If everything's alright, create the order and exit the pane
		if(clearToContinue) {
			new Order(name,
					phoneNoField.getText().trim(),
					item,
					itemNoField.getText().trim(),
					priceField.getText().trim(),
					commentField.getText().trim());

			Main.mainWindowController.exitNewOrder();
		}
	}

	/**
	 * Called when the user clicks the "cancel" button.
	 * Exits the pane.
	 * @param event
	 */
	public void cancel(ActionEvent event) {
		Main.mainWindowController.exitNewOrder();
	}
}