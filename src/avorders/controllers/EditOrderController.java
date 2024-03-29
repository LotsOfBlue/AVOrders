package avorders.controllers;

import avorders.Main;
import avorders.Order;
import avorders.utils.OrderUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Controller for editOrder.fxml.
 * Lets the user modify an Orders's data.
 * @author Johan Blomberg
 */
public class EditOrderController {
	
	@FXML private TextField nameField;
	@FXML private TextField phoneNoField;
	@FXML private TextField itemField;
	@FXML private TextField itemNoField;
	@FXML private TextField priceField;
	@FXML private TextField sellerField;
	@FXML private TextArea commentField;
	@FXML private CheckBox orderedBox;
	@FXML private CheckBox contactedBox;
	@FXML private CheckBox deliveredBox;
	@FXML private Label idLabel;

	private Order order;

	/**
	 * Fills the textfields with the data the Order contains.
	 * @param order The order to get data from.
	 */
	void populateFields(Order order) {
		this.order = order;

		idLabel.setText("Beställning nr. " + order.getId() + " (" + order.getCreationDate() + ")");

		nameField.setText(order.getName());
		phoneNoField.setText(order.getPhoneNo());
		itemField.setText(order.getItem());
		itemNoField.setText(order.getItemNo());
		priceField.setText(order.getPrice());
		sellerField.setText(order.getSeller());
		commentField.setText(order.getComment());

		if(order.getOrderedDate() != null) {
			orderedBox.setText(order.getOrderedDate().toString());
			orderedBox.setSelected(true);
		}

		if(order.getContactedDate() != null) {
			contactedBox.setText(order.getContactedDate().toString());
			contactedBox.setSelected(true);
		}

		if(order.getDeliveredDate() != null) {
			deliveredBox.setText(order.getDeliveredDate().toString());
			deliveredBox.setSelected(true);
		}
	}

	/**
	 * Called when the user clicks the "save" button.
	 * Checks if all text fields contain legal values,
	 * and displays error messages if they don't.
	 */
	@FXML
	private void finishEdit(ActionEvent event) {
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

		//If any field contains prompt text, the edit can't be completed
		for (int i = 0; i < allFields.length - 1; i++) {
			if (!allFields[i].getPromptText().equals("")) {
				clearToContinue = false;
			}
		}

		//If everything's alright, finish the edit and exit the pane
		if(clearToContinue) {
			order.setName(name);
			order.setPhoneNo(phoneNoField.getText().trim());
			order.setItem(item);
			order.setItemNo(itemNoField.getText().trim());
			order.setPrice(priceField.getText().trim());
			order.setSeller(sellerField.getText().trim());
			order.setComment(commentField.getText().trim());

			if(!orderedBox.getText().equals("")) {
				order.setOrderedDate(LocalDate.parse(orderedBox.getText()));
			}
			else {
				order.setOrderedDate(null);
			}

			if(!contactedBox.getText().equals("")) {
				order.setContactedDate(LocalDate.parse(contactedBox.getText()));
			}
			else {
				order.setContactedDate(null);
			}

			if(!deliveredBox.getText().equals("")) {
				order.setDeliveredDate(LocalDate.parse(deliveredBox.getText()));
			}
			else {
				order.setDeliveredDate(null);
			}

			Main.getMainWindowController().exitEditOrder();
		}
	}

	/**
	 * Called when the user clicks one of the checkboxes.
	 * Adds the current date to the box's label,
	 * or removes it if there was already one there.
	 */
	@FXML
	private void addCurrentDate(ActionEvent event) {
		CheckBox box = (CheckBox)event.getSource();
		if(box.getText().equals("")) {
			box.setText(LocalDate.now().toString());
		}
		else {
			box.setText("");
		}
	}

	/**
	 * Called when the user clicks the "cancel" button.
	 * Discards all changes and exits the pane.
	 */
	@FXML
	private void cancel(ActionEvent event) {
		Main.getMainWindowController().exitEditOrder();
	}
}
