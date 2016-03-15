import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class EditOrderController {

	public TextField nameField;
	public TextField phoneNoField;
	public TextField itemField;
	public TextField itemNoField;
	public TextField priceField;
	public TextArea commentField;
	public CheckBox orderedBox;
	public CheckBox contactedBox;
	public CheckBox deliveredBox;
	public Label idLabel;

	private Order order;

	/**
	 * todo
	 * @param order
	 */
	public void populateFields(Order order) {
		this.order = order;

		idLabel.setText("Beställning nr. " + order.getId() + " (" + order.getCreationDate() + ")");

		nameField.setText(order.getName());
		phoneNoField.setText(order.getPhoneNo());
		itemField.setText(order.getItem());
		itemNoField.setText(order.getItemNo());
		priceField.setText(order.getPrice());
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
	 * todo
	 * @param event
	 */
	public void finishEdit(ActionEvent event) {
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

		//If any field contains prompt text, the edit can't be completed
		for (int i = 0; i < allFields.length - 1; i++) {
			if (!allFields[i].getPromptText().equals("")) {
				clearToContinue = false;
			}
		}

		//If everything's alright, finish the edit and exit the pane
		if(clearToContinue) {
			order.setName(name);
			order.setPhoneNo(phoneNo);
			order.setItem(item);
			order.setItemNo(itemNoField.getText().trim());
			order.setPrice(price);
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

			Main.mainWindowController.exitEditOrder();
		}
	}

	/**
	 * todo
	 * @param event
	 */
	public void addCurrentDate(ActionEvent event) {
		CheckBox box = (CheckBox)event.getSource();
		if(box.getText().equals("")) {
			box.setText(LocalDate.now().toString());
		}
		else {
			box.setText("");
		}

	}

	/**
	 * todo
	 * @param event
	 */
	public void cancel(ActionEvent event) {
		//TODO
		System.out.println("Avbröt...");

		Main.mainWindowController.exitEditOrder();
	}
}
