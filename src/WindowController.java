import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WindowController {
	public Button newButton;
	public Button editButton;
	public Button deleteButton;
	public VBox listPane;
	public AnchorPane infoPane;

	@FXML
	private void initialize() {
		deleteButton.setDisable(true);
		editButton.setDisable(true);
		System.out.println("Startar WindowController...");
	}

	public void newOrder(ActionEvent event) {
		System.out.println("Ny beställning!");

		//Disable the buttons
		newButton.setDisable(true);
		editButton.setDisable(true);
		deleteButton.setDisable(true);

		//Place the correct pane in infoPane
		try {
			AnchorPane newPane = FXMLLoader.load(getClass().getResource("newOrder.fxml"));
			newPane.setPrefWidth(infoPane.getWidth());
			newPane.setPrefHeight(infoPane.getHeight());
			infoPane.getChildren().add(newPane);
		}
		catch (IOException e) {
			System.out.println("Kunde inte ladda newOrder.fxml");
		}

	}

	public void editOrder(ActionEvent event) {
		System.out.println("Redigerar beställning!");
	}

	public void deleteOrder(ActionEvent event) {
		System.out.println("Tar bort beställning!");
	}
}