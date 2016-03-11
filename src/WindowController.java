import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Comparator;

public class WindowController {
	public Button newButton;
	public Button editButton;
	public Button deleteButton;
	public AnchorPane infoPane;
	public ChoiceBox sortModeBox;
	public ListView listView;

	private Comparator[] comparators;

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

		//Store all available comparators for easy access
		comparators = new Comparator[sortModeBox.getItems().size()];
		comparators[0] = new IDSort();
		comparators[1] = new ReverseIDSort();
		comparators[2] = new CustomerSort();

		/*for (int i = 0; i < 100; i++) {
			new Order(i, "Kundnamn", 123456789, "Vara", 1234, 100, "");
		}*/

		refreshList();
	}

	/**
	 * Empties the listview and adds all orders anew, while
	 * rearranging the list as necessary and saving all orders.
	 */
	private void refreshList() {
		//Sorts in the regular way before saving
		OrderIO.getOrders().sort(new IDSort());
		OrderIO.saveToFile();

		//Empty the pane
		listView.getItems().removeAll(OrderIO.getOrders());

		//Rearrange the list of orders if the sorting mode is not the default
		int i = sortModeBox.getSelectionModel().getSelectedIndex();
		OrderIO.getOrders().sort(comparators[i]);

		//Fill the pane with the new orders
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
			FXMLLoader loader = new FXMLLoader();
			AnchorPane newOrderPane = loader.load(getClass().getResource("newOrder.fxml").openStream());
			newOrderPane.setPrefWidth(infoPane.getWidth());
			newOrderPane.setPrefHeight(infoPane.getHeight());
			infoPane.getChildren().add(newOrderPane);

			//Gives input focus to the name field
			NewOrderController controller = loader.getController();
			controller.nameField.requestFocus();
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