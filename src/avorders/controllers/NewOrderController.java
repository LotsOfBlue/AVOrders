package avorders.controllers;

import avorders.Main;
import avorders.Order;
import avorders.OrderUtils;
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
	@FXML private Label idLabel;

	@FXML private TextField nameField;
	@FXML private TextField phoneNoField;
	@FXML private TextField itemField;
	@FXML private TextField itemNoField;
	@FXML private TextField priceField;
	@FXML private TextField sellerField;
	@FXML private TextArea commentField;

	@FXML private Button createButton;

	/**
	 * Display the ID the order will be given once created
	 */
	@FXML
	private void initialize() {
		idLabel.setText(idLabel.getText() + OrderUtils.getNextOrderID());
	}
	
	TextField getNameField() {
		return nameField;
	}

	/**
	 * Called when the user clicks the "create" button.
	 * Checks if the textfields contain legal values,
	 * and displays error messages if they don't.
	 * If there are no errors, creates a new Order object.
	 */
	@FXML
	private void finishCreate(ActionEvent event) {
		//Gather all text fields for easy access
		TextField[] allFields = {nameField, phoneNoField, itemField, itemNoField, priceField, sellerField};

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
					sellerField.getText().trim(),
					commentField.getText().trim());

			Main.getMainWindowController().exitNewOrder();
		}
	}

	/**
	 * Called when the user clicks the "cancel" button.
	 * Exits the pane.
	 */
	@FXML
	private void cancel(ActionEvent event) {
		Main.getMainWindowController().exitNewOrder();
	}
}