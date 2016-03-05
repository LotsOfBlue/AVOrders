import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller {
	public Button newButton;
	public Button editButton;
	public Button deleteButton;
	public VBox listPane;
	public AnchorPane infoPane;

	@FXML
	private void initialize() {
		//deleteButton.setDisable(true);
		//editButton.setDisable(true);
		System.out.println("Startar Controller...");
	}

	public void newOrder(ActionEvent event) {
		System.out.println("Ny beställning!");
	}

	public void editOrder(ActionEvent event) {
		System.out.println("Redigerar beställning!");
	}

	public void deleteOrder(ActionEvent event) {
		System.out.println("Tar bort beställning!");
	}
}