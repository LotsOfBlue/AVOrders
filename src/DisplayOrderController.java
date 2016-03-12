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

	public void populateLabels(Order order) {
		if(order.getName() != null) {
			nameLabel.setText(order.getName());
		}
		if(order.getPhoneNo() != null) {
			phoneNoLabel.setText("" + order.getPhoneNo());
		}
		if(order.getItem() != null) {
			itemLabel.setText(order.getItem());
		}
		if(order.getItemNo() != null) {
			itemNoLabel.setText("" + order.getItemNo());
		}
		if(order.getPrice() != null) {
			priceLabel.setText("" + order.getPrice());
		}
		if(order.getComment() != null) {
			commentLabel.setText(order.getComment());
		}
		if(order.getOrderedDate() != null) {
			orderedLabel.setText("" + order.getOrderedDate());
		}
		if(order.getReceivedDate() != null) {
			contactedLabel.setText("" + order.getReceivedDate());
		}
		if(order.getDeliveredDate() != null) {
			deliveredLabel.setText("" + order.getDeliveredDate());
		}
	}
}
