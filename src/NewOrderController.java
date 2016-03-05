import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewOrderController {
	public TextField nameField;
	public TextField phoneNoField;
	public TextField itemField;
	public TextField itemNoField;
	public TextField priceField;
	public TextArea commentField;

	public Button createButton;
	public Button cancelButton;

	public void finishCreate(ActionEvent event) {
		System.out.println("Klar med nya ordern!");
	}

	public void cancel(ActionEvent event) {
		System.out.println("Avbröt...");
	}
}
