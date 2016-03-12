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

	public void populateFields(Order order) {
		nameField.setText(order.getName());
		phoneNoField.setText(order.getPhoneNo());
		itemField.setText(order.getItem());
		itemNoField.setText(order.getItemNo());
		priceField.setText(order.getPrice());
		commentField.setText(order.getComment());
	}

	public void finishCreate(ActionEvent event) {

	}

	public void cancel(ActionEvent event) {

	}
}
