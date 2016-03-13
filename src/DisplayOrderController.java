import javafx.scene.control.Label;

public class DisplayOrderController {

	public Label nameLabel;
	public Label phoneNoLabel;
	public Label itemLabel;
	public Label itemNoLabel;
	public Label priceLabel;
	public Label commentLabel;
	public Label orderedLabel;
	public Label contactedLabel;
	public Label deliveredLabel;

	/**
	 * todo
	 * @param order
	 */
	public void populateLabels(Order order) {
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
