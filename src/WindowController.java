import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class WindowController {
	public Button newButton;
	public Button editButton;
	public Button deleteButton;
	public AnchorPane infoPane;
	public ChoiceBox sortModeBox;
	public ListView listView;

	@FXML
	/**
	 * Set the window to its initial state
	 */
	private void initialize() {
		//TODO
		System.out.println("Startar WindowController...");

		deleteButton.setDisable(true);
		editButton.setDisable(true);

		sortModeBox.getItems().addAll("ID (stigande)", "ID (fallande)", "Kund");
		sortModeBox.setValue(sortModeBox.getItems().get(0));

		for (int i = 0; i < 100; i++) {
			new Order(i, "Kundnamn", 123456789, "Vara", 1234, 100, "");
		}

		refreshList();
	}

	/**
	 * Empties the listview and adds all orders anew.
	 */
	private void refreshList() {
		listView.getItems().removeAll(OrderIO.getOrders());
		//TODO sort
		listView.getItems().addAll(OrderIO.getOrders());
	}

	/**
	 * Prepare the window for creating a new order
	 * @param event
	 */
	public void newOrder(ActionEvent event) {
		//TODO
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
		//TODO
		System.out.println("Redigerar beställning!");
	}

	/**
	 * Delete the current order
	 * @param event
	 */
	public void deleteOrder(ActionEvent event) {
		//TODO
		System.out.println("Tar bort beställning!");
	}

	/**
	 * Cancel the creation of a new order,
	 * and reset the buttons to their default
	 */
	public void exitNewOrder() {
		infoPane.getChildren().remove(0);
		newButton.setDisable(false);
		refreshList();
	}
}