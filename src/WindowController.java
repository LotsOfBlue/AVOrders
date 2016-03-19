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
	public ChoiceBox<String> sortModeBox;
	public ListView listView;

	private Comparator[] comparators;

	private Order lastSelected;

	@FXML
	/**
	 * Set the window to its initial state
	 */
	private void initialize() {
		deleteButton.setDisable(true);
		editButton.setDisable(true);

		//Add strings representing the sorting modes to the choice box
		sortModeBox.getItems().addAll("ID (stigande)", "ID (fallande)", "Kund");
		sortModeBox.setValue(sortModeBox.getItems().get(0));

		//Call refreshList() when a new sorting mode is selected
		sortModeBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			refreshList();
		});

		//Store all available comparators for easy access
		comparators = new Comparator[sortModeBox.getItems().size()];
		comparators[0] = new IDSort();
		comparators[1] = new ReverseIDSort();
		comparators[2] = new CustomerSort();

		refreshList();
	}

	/**
	 * Empties the listview and adds all orders anew, while
	 * rearranging the list as necessary and saving all orders.
	 */
	private void refreshList() {
		//Sorts in the regular way before saving
		OrderUtils.getOrders().sort(new IDSort());
		OrderUtils.saveToFile();

		//Empty the pane
		listView.getItems().removeAll(OrderUtils.getOrders());

		//Rearrange the list of orders if the sorting mode is not the default
		int i = sortModeBox.getSelectionModel().getSelectedIndex();
		OrderUtils.getOrders().sort(comparators[i]);

		//Fill the pane with the new orders
		listView.getItems().addAll(OrderUtils.getOrders());
	}

	/**
	 * Store the Order that was last clicked, and display it
	 */
	public void getSelectedOrder() {
		lastSelected = (Order) listView.getSelectionModel().getSelectedItem();
		displayOrder();
	}

	/**
	 * Prepare the window for displaying the last selected order
	 */
	private void displayOrder() {
		//Remove anything currently in infoPane
		while (infoPane.getChildren().size() > 0) {
			infoPane.getChildren().remove(0);
		}

		//Enable the edit and delete buttons
		editButton.setDisable(false);
		deleteButton.setDisable(false);

		if (lastSelected != null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				AnchorPane displayOrderPane = loader.load(getClass().getResource("displayOrder.fxml").openStream());
				displayOrderPane.setPrefWidth(infoPane.getWidth());
				displayOrderPane.setPrefHeight(infoPane.getHeight());
				infoPane.getChildren().add(displayOrderPane);

				//Display the order's info
				DisplayOrderController controller = loader.getController();
				controller.populateLabels(lastSelected);

			}
			catch (IOException e) {
				System.out.println("Kunde inte ladda displayOrder.fxml");
			}
		}
	}

	/**
	 * Prepare the window for creating a new order
	 * @param event
	 */
	public void newOrder(ActionEvent event) {
		//Remove anything currently in infoPane
		while (infoPane.getChildren().size() > 0) {
			infoPane.getChildren().remove(0);
		}

		//Disable the buttons
		newButton.setDisable(true);
		editButton.setDisable(true);
		deleteButton.setDisable(true);

		//Disable the listview and choicebox
		listView.setDisable(true);
		sortModeBox.setDisable(true);

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
		//Remove anything currently in infoPane
		while (infoPane.getChildren().size() > 0) {
			infoPane.getChildren().remove(0);
		}

		//Disable buttons
		deleteButton.setDisable(true);
		editButton.setDisable(true);
		newButton.setDisable(true);

		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane editOrderPane = loader.load(getClass().getResource("editOrder.fxml").openStream());
			editOrderPane.setPrefWidth(infoPane.getWidth());
			editOrderPane.setPrefHeight(infoPane.getHeight());
			infoPane.getChildren().add(editOrderPane);

			//Display the order's info
			EditOrderController controller = loader.getController();
			controller.populateFields(lastSelected);
		}
		catch (IOException e) {
			System.out.println("Kunde inte ladda editOrder.fxml");
		}
	}

	/**
	 * TODO
	 * @param event
	 */
	public void deleteOrder(ActionEvent event) {
		OrderUtils.getOrders().remove(lastSelected);
		listView.getItems().remove(lastSelected);

		infoPane.getChildren().remove(0);
		deleteButton.setDisable(true);
		editButton.setDisable(true);
		refreshList();
	}

	/**
	 * Cancel the creation of a new order,
	 * and reset the buttons to their default.
	 */
	void exitNewOrder() {
		infoPane.getChildren().remove(0);
		newButton.setDisable(false);
		listView.setDisable(false);
		sortModeBox.setDisable(false);
		refreshList();
	}

	/**
	 * Cancel/finish editing an order,
	 * reset buttons to default and display the order again.
	 */
	void exitEditOrder() {
		infoPane.getChildren().remove(0);
		newButton.setDisable(false);
		listView.setDisable(false);
		refreshList();
		displayOrder();
	}
}