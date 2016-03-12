import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditOrderController {

	public TextField nameField;
	public TextField phoneNoField;
	public TextField itemField;
	public TextField itemNoField;
	public TextField priceField;
	public TextArea commentField;

	private Order order;

	public void populateFields(Order order) {
		this.order = order;
		nameField.setText(order.getName());
		phoneNoField.setText(order.getPhoneNo());
		itemField.setText(order.getItem());
		itemNoField.setText(order.getItemNo());
		priceField.setText(order.getPrice());
		commentField.setText(order.getComment());
	}

	public void finishEdit(ActionEvent event) {
		order.setName(nameField.getText());
		order.setPhoneNo(phoneNoField.getText());
		order.setItem(itemField.getText());
		order.setItemNo(itemNoField.getText());
		order.setPrice(priceField.getText());
		order.setComment(commentField.getText());

		Main.mainWindowController.exitEditOrder();
	}

	public void cancel(ActionEvent event) {
		//TODO
		System.out.println("Avbröt...");

		Main.mainWindowController.exitEditOrder();
	}
}
