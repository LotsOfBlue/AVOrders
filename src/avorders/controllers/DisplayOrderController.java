package avorders.controllers;

import avorders.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for displayOrder.fxml
 * Gets info from an Order and changes all labels accordingly.
 * @author Johan Blomberg
 */
public class DisplayOrderController {
	
	@FXML private Label nameLabel;
	@FXML private Label phoneNoLabel;
	@FXML private Label itemLabel;
	@FXML private Label itemNoLabel;
	@FXML private Label priceLabel;
	@FXML private Label commentLabel;
	@FXML private Label orderedLabel;
	@FXML private Label contactedLabel;
	@FXML private Label deliveredLabel;
	@FXML private Label idLabel;

	/**
	 * Fills all empty labels with the appropriate data from the Order parameter.
	 * @param order The order to get the data from
	 */
	void populateLabels(Order order) {
		idLabel.setText("Beställning nr. " + order.getId() + " (" + order.getCreationDate() + ")");

		nameLabel.setText(order.getName());
		phoneNoLabel.setText(order.getPhoneNo());
		itemLabel.setText(order.getItem());
		itemNoLabel.setText(order.getItemNo());
		priceLabel.setText(order.getPrice());
		commentLabel.setText(order.getComment());
		if(order.getOrderedDate() != null) {
			orderedLabel.setText(order.getOrderedDate().toString());
		}
		if(order.getContactedDate() != null) {
			contactedLabel.setText(order.getContactedDate().toString());
		}
		if(order.getDeliveredDate() != null) {
			deliveredLabel.setText(order.getDeliveredDate().toString());
		}
	}
}
