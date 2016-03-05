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
	/**
	 * Set the window to its initial state
	 */
	private void initialize() {
		deleteButton.setDisable(true);
		editButton.setDisable(true);
		System.out.println("Startar WindowController...");
	}

	/**
	 * Prepare the window for creating a new order
	 * @param event
	 */
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

	/**
	 * Prepare the window for editing an order
	 * @param event
	 */
	public void editOrder(ActionEvent event) {
		System.out.println("Redigerar beställning!");
		//TODO
	}

	/**
	 * Delete the current order
	 * @param event
	 */
	public void deleteOrder(ActionEvent event) {
		System.out.println("Tar bort beställning!");
		//TODO
	}

	/**
	 * Cancel the creation of a new order,
	 * and reset the buttons to their default
	 */
	public void exitNewOrder() {
		infoPane.getChildren().remove(0);
		newButton.setDisable(false);
	}
}